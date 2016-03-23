package com.fredrouvier.poc.kafka

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by frouvier on 18/03/2016.
  */
trait Spark extends LazyLogger {

  lazy val sparkConf = new SparkConf().setMaster("local[*]").setAppName("PocKafka")
  lazy val ssc = new StreamingContext(sparkConf, Seconds(2))

  val zkQuorum = "sandbox.hortonworks.com:2181"
  val brokers  = "sandbox.hortonworks.com:6667"

  val registeredMemberTopic = "registeredMemberPoc"
  val assignmentMemberTopic = "assignmentMemberPoc"
}
