package com.github.dakatsuka.es.lib.event

import com.twitter.util.Future

trait EventHandler[Ev] {
  this: EventPublisher[Ev] =>

  def polling(): Future[Unit] = {
    handle().flatMap {
      case Some(_) => polling()
      case None    => Future.Unit
    }
  }

  private def handle(): Future[Option[Unit]] = queue.dequeue().flatMap {
    case Some(event) => Future.collect(subscribers.map(_.receive(self, event)).toSeq).map(_ => Some())
    case None        => Future.None
  }
}
