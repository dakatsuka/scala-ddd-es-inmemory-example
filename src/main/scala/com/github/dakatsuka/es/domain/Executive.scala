package com.github.dakatsuka.es.domain

import com.github.dakatsuka.es.lib.event.{EventHandler, EventPublisher}
import com.twitter.util.Future

trait Executive[Ev] extends EventPublisher[Ev] with EventHandler[Ev] {
  protected def tracker: Tracker

  protected def preComplete(): Future[Unit] = Future.Unit

  /**
   * Publish to the queue is ignored.
   */
  protected def postComplete(): Future[Unit] = Future.Unit

  def run(): Future[Unit] = {
    for {
      _ <- polling()
      _ <- checkTracking()
      _ <- preComplete()
      _ <- polling()
      _ <- postComplete()
    } yield ()
  }

  private def checkTracking(): Future[Unit] =
    if (tracker.isComplete) Future.Unit
    else Future.exception(new RuntimeException)
}
