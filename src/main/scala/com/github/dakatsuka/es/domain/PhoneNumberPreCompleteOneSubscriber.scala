package com.github.dakatsuka.es.domain

import com.github.dakatsuka.es.lib.event.{EventPublisher, EventSubscriber}
import com.twitter.util.Future

class PhoneNumberPreCompleteOneSubscriber extends EventSubscriber[PhoneNumberState] {
  override def receive(publisher: EventPublisher[PhoneNumberState], event: PhoneNumberState): Future[Unit] = event match {
    case PhoneNumberState.Processed =>
      publisher.publish(PhoneNumberState.PreCompleted1)
    case _ =>
      Future.Unit
  }
}
