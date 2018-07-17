package com.github.dakatsuka.es.domain

import com.github.dakatsuka.es.lib.event.{EventPublisher, EventSubscriber}
import com.twitter.util.Future

class PhoneNumberPreCompleteTwoSubscriber extends EventSubscriber[PhoneNumberState] {
  override def receive(publisher: EventPublisher[PhoneNumberState], event: PhoneNumberState): Future[Unit] = event match {
    case PhoneNumberState.Processed =>
      publisher.publish(PhoneNumberState.PreCompleted2)
      Future.Unit
    case _ =>
      Future.Unit
  }
}
