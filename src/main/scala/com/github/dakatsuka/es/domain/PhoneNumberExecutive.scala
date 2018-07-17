package com.github.dakatsuka.es.domain

import com.github.dakatsuka.es.lib.event.{EventPublisher, EventQueue, EventSubscriber}
import com.twitter.util.Future

class PhoneNumberExecutive(
  protected val queue: EventQueue[PhoneNumberState],
  protected val tracker: PhoneNumberStateTracker
) extends Executive[PhoneNumberState] with EventSubscriber[PhoneNumberState] {
  override def receive(publisher: EventPublisher[PhoneNumberState], event: PhoneNumberState): Future[Unit] = event match {
    case PhoneNumberState.PreCompleted1 =>
      tracker.addState(event)
      Future.Unit
    case PhoneNumberState.PreCompleted2 =>
      tracker.addState(event)
      Future.Unit
    case PhoneNumberState.Completed =>
      PhoneNumberDB.state = event
      Future.Unit
    case _ =>
      Future.Unit
  }

  override protected def preComplete(): Future[Unit] = {
    publish(PhoneNumberState.Completed)
  }
}
