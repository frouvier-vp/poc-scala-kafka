package com.fredrouvier.poc.kafka

import org.slf4j.{Logger, LoggerFactory}

/**
  * Logger trait
  *
  * Example use: logger.debug("My message")
  */
trait LazyLogger {

  /**
    * The application logger
    */
  protected lazy val logger: Logger = LoggerFactory.getLogger(getClass.getName)
}
