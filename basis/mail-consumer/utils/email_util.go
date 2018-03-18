package utils

import (
	"net/smtp"
	"strings"
	"encoding/json"
	"os"
)
var SEND_MAIL_USER = os.Getenv("SEND_MAIL_USER")
var SEND_MAIL_PASSWORD = os.Getenv("SEND_MAIL_PASSWORD")
type MailInfo struct {
	To       string `json:"to"`
	Subject  string `json:"subject"`
	Body     string `json:"body"`
	BodyType string `json:"body_type"`
	Level    string `json:"level"`
}

func SendToMail(to, subject, body, bodyType string) error {
	user := SEND_MAIL_USER
	password := SEND_MAIL_PASSWORD
	host := "smtp.163.com:25"
	hp := strings.Split(host, ":")
	auth := smtp.PlainAuth("", user, password, hp[0])
	var contentType string
	if bodyType == "html" {
		contentType = "Content-Type: text/" + bodyType + "; charset=UTF-8"
	} else {
		contentType = "Content-Type: text/plain" + "; charset=UTF-8"
	}

	msg := []byte("To: " + to + "\r\nFrom: " + user + ">\r\nSubject: " + subject + "\r\n" + contentType + "\r\n\r\n" + body)
	sendTo := strings.Split(to, ";")
	err := smtp.SendMail(host, auth, user, sendTo, msg)
	return err
}
func SendToMailByMessage(message []byte) error {
	var mailInfo MailInfo
	err := json.Unmarshal(message, &mailInfo)
	if err == nil {

		return SendToMail(mailInfo.To, mailInfo.Subject, mailInfo.Body, mailInfo.BodyType)
	} else {
		return err;
	}

}
