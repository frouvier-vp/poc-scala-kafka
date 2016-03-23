package com.fredrouvier.poc.kafka

import com.fredrouvier.poc.kafka.streaming.{SamplingMemberProducer, MemberReceiver}

/**
  * Bootstrap the poc application
  */
object Boot extends App {
  val action = sys.props.getOrElse("action", "receiver")
  val nbMessages = sys.props.getOrElse("nbMessages", 1000)
  if (action.equals("producer")) {
    SamplingMemberProducer.generateMessage(nbMessages.toString.toInt)
  } else {
    MemberReceiver.start()
  }
}
