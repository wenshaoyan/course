module.exports = {
  "appenders": {
    "console":{"type":"console"},
    "kafka":{
      "type": "kafka",
      "socket_config": {"kafkaHost": process.env.KAFKA_URL, "topic": "global-log"}
    }
  },
  "categories": {
    "default": { "appenders": ["console"], "level":"trace" },
    "router":{"appenders":["console","kafka"],"level":"trace"},
    "zookeeper":{"appenders":["console","kafka"],"level":"trace"},
    "thrift":{"appenders":["console","kafka"],"level":"trace"},
    "resSuccess":{"appenders":["console"],"level":"trace"},
    "resFail":{"appenders":["console","kafka"],"level":"trace"},
    "resUnknown":{"appenders":["console","kafka"],"level":"trace"},
    "error": {"appenders":["console","kafka"],"level":"trace"},
    "core": {"appenders":["console","kafka"],"level":"trace"},
    "graphql": {"appenders":["console","kafka"],"level":"trace"}
  }
}