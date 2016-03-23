package com.fredrouvier.poc.kafka.streaming

import com.fredrouvier.poc.kafka.Spark
import com.fredrouvier.poc.kafka.model.Member
import org.apache.spark.streaming.kafka._

/**
  * Kafka receiver
  * Receives and parses messages. Call the producer object to push an other message.
  */
object MemberReceiver extends Spark {

  val group = "registeredMemberPocReceiver"
  val numThread = 1

  def start(): Unit = {
    ssc.checkpoint("checkpoint")
    val topicMap = Map((registeredMemberTopic, numThread))

    val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
    val members = lines.map { line =>
      val row = line.split(" ")
      Member(row(0).toLong, row(1), row(2))
    }
    members.print()
    members.foreachRDD( member => SamplingMemberProducer.sendMessages(member.collect()))

    ssc.start()
    ssc.awaitTermination()
  }
}
