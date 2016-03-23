package com.fredrouvier.poc.kafka.streaming

import java.util

import com.fredrouvier.poc.kafka.Spark
import com.fredrouvier.poc.kafka.model.{LayerAssignment, MemberAssignment, Member}
import org.apache.kafka.clients.producer.{ProducerConfig, ProducerRecord}
import org.apache.kafka.clients.producer.KafkaProducer

/**
  * Kafka producer
  * Generates and produces a messages to push to Kafka.
  */
object SamplingMemberProducer extends Spark {

  // Zookeeper connection properties
  lazy val props = new util.HashMap[String, Object]()
  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
    "org.apache.kafka.common.serialization.StringSerializer")
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
    "org.apache.kafka.common.serialization.StringSerializer")

  lazy val producer = new KafkaProducer[String, String](props)

  /**
    * Sends messages for each member collected
    *
    * @param members A list of members
    */
  def sendMessages(members: Array[Member]): Unit = {
    members.foreach { member =>
      val message = new ProducerRecord[String, String](assignmentMemberTopic, null, assign(member).toString)
      producer.send(message)
    }
    Thread.sleep(1000)
  }

  /**
    * Creates a fake member assignment
    *
    * @param member A member
    *
    * @return
    */
  def assign(member: Member): MemberAssignment = {
    MemberAssignment(member, List(LayerAssignment(1L, "A")))
  }

  /**
    * Creates and pushes N fake messages to Kafka
    *
    * @param nbMessages The number of messages to push
    */
  def generateMessage(nbMessages: Int) = {
    try {
      for (id <- Range(0, nbMessages)) {
        val message = new ProducerRecord[String, String](registeredMemberTopic, null, id + " uk fred@fred.fr")
        producer.send(message)
      }
      logger.warn("{} messages push to the topic {}", nbMessages, registeredMemberTopic)
      Thread.sleep(1000)
    } catch {
      case e: Exception => logger.error("{}", e)
    }
  }
}
