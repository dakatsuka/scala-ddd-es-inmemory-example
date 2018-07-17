package com.github.dakatsuka.es.domain

sealed trait PhoneNumberState extends DomainEvent

object PhoneNumberState {
  case object Initial extends PhoneNumberState
  case object Processed extends PhoneNumberState
  case object PreCompleted1 extends PhoneNumberState
  case object PreCompleted2 extends PhoneNumberState
  case class Completed(message: String) extends PhoneNumberState
}
