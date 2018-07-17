package com.github.dakatsuka.es.domain

import com.github.dakatsuka.es.lib.event.{EventPublisher, EventSubscriber}
import com.twitter.util.Future

trait Executive[Ev] extends EventPublisher[Ev] with EventSubscriber[Ev] {
  protected def tracker: Tracker

  protected def preComplete(): Future[Unit] = Future.Unit
  protected def postComplete(): Future[Unit] = Future.Unit

  def run(): Future[Unit] = {
    for {
      _ <- polling()
      _ <- checkCompletedPolling()
      _ <- preComplete()
      _ <- polling()
      _ <- postComplete()
    } yield ()
  }

  private def checkCompletedPolling(): Future[Unit] =
    if (tracker.isComplete) Future.Unit
    else Future.exception(new RuntimeException)
}
