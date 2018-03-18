package main

import (
	"github.com/Shopify/sarama"
	"log"
	"fmt"
	"os"
	"./utils"
	"sync"
	"github.com/bsm/sarama-cluster"
	"os/signal"
)

var Address = []string{os.Getenv("KAFKA_URL")}

func main() {
	topic := []string{"basis-mail"}
	var wg = &sync.WaitGroup{}
	wg.Add(1)
	//广播式消费
	go clusterConsumer(wg, Address, topic, "default")

	wg.Wait()
}
// 支持brokers cluster的消费者
func clusterConsumer(wg *sync.WaitGroup,brokers, topics []string, groupId string)  {
	defer wg.Done()
	config := cluster.NewConfig()
	config.Consumer.Return.Errors = true
	config.Group.Return.Notifications = true
	config.Consumer.Offsets.Initial = sarama.OffsetNewest

	// init consumer
	consumer, err := cluster.NewConsumer(brokers, groupId, topics, config)
	if err != nil {
		log.Printf("%s: sarama.NewSyncProducer err, message=%s \n", groupId, err)
		return
	}
	defer consumer.Close()

	// trap SIGINT to trigger a shutdown
	signals := make(chan os.Signal, 1)
	signal.Notify(signals, os.Interrupt)

	// consume errors
	go func() {
		for err := range consumer.Errors() {
			log.Printf("%s:Error: %s\n", groupId, err.Error())
		}
	}()

	// consume notifications
	go func() {
		for ntf := range consumer.Notifications() {
			log.Printf("%s:Rebalanced: %+v \n", groupId, ntf)
		}
	}()

	// consume messages, watch signals
	var successes int
Loop:
	for {
		select {
		case msg, ok := <-consumer.Messages():
			if ok {
				err := utils.SendToMailByMessage(msg.Value)
				if err == nil {
					log.Println("发送成功:")
				} else {
					log.Println("发送失败:", err)
				}
				consumer.MarkOffset(msg, "")  // 将消息标记为已经处理
				successes++
			}
		case <-signals:
			break Loop
		}
	}
	fmt.Fprintf(os.Stdout, "%s consume %d messages \n", groupId, successes)
}