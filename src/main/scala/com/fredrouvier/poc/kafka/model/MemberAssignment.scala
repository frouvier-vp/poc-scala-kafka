package com.fredrouvier.poc.kafka.model

/**
  * Created by frouvier on 18/03/2016.
  */
case class MemberAssignment (
  member: Member,
  assignments: List[LayerAssignment]
)
