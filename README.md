## Kafka-Poster

This is a simple [microService](https://martinfowler.com/articles/microservices.html) to demonstrate how to use spring-boot-kafka to produce and publish messages to a kafka broker.

### Setup 
* Download maven and add it to your path.
* Grab the Apache Kafka distribution - https://kafka.apache.org/downloads and extract it somewhere, preferably in a path containing no spaces in the directory name.

#### Setup and run Apache Kafka Single Broker (A cluster of size one)
(If you are on MacOS or Linux, use the sh scripts instead)

Open a command window and Start Zookeeper:
`C:\Softwares\kafka_2.12-0.10.2.1> .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties`

Open a new command window and Start kafka
`C:\kafka_2.12-0.10.2.1>.\bin\windows\kafka-server-start.bat .\config\server.properties`

Create a topic, let's just call it myTopic
`.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic myTopic`

Subscribe to this topic on a command line. We will publish to this topic from our microService
`.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic myTopic --from-beginning`

#### Run our microService
Clone the repo and run:

`mvn spring-boot:run`

If everything goes well, you will see this at the end:
```
2018-05-11 09:56:49.256  INFO 2136 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2018-05-11 09:56:49.266  INFO 2136 --- [           main] o.s.c.support.DefaultLifecycleProcessor  : Starting beans in phase 2147483647
2018-05-11 09:56:49.366  INFO 2136 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2018-05-11 09:56:49.373  INFO 2136 --- [           main] c.m.kafkaposter.KafkaPosterApplication   : Started KafkaPosterApplication in 9.806 seconds (JVM running for 18.248)
```

This means spring boot has started up our service on the default port of 8080. Notice that it uses an embedded tomcat to bring up our controller. (Look at MainController.java)

Now, we are ready to test our setup!

Post a message to our microService at this endoint: http://localhost:8080/post_kafka
```
{
	"messageToPost":"Hello!",
	"topicName": "myTopic"
}
```
You can use [POSTMAN](http://getpostman.com/), or if you have access to command line 'curl', you can simply do:

`$ curl -X POST -H "Content-Type: application/json" -d '{"messageToPost": "Hello from the other side", "topicName": "myTopic"}' http://localhost:8080/ssot/v1/post-kafka`


```2018-05-11 10:00:50.990  INFO 15976 --- [nio-8080-exec-3] o.a.kafka.common.utils.AppInfoParser     : Kafka version : 0.10.2.0
2018-05-11 10:00:50.991  INFO 15976 --- [nio-8080-exec-3] o.a.kafka.common.utils.AppInfoParser     : Kafka commitId : 576d93a8dc0cf421```

