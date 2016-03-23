# POC Spark Kafka

See documentation
* SBT   http://www.scala-sbt.org/
* Spark http://spark.apache.org/
* Kafka http://kafka.apache.org/

Receives messages on the "registeredMemberPoc" topic.
Produces messages and sends its on the "assignmentMemberPoc" topic.

Run the receiver :
```
> sbt run -Daction=receiver
```

Run the producer :
```
> sbt run -Daction=producer -DnbMessages=10000
```
