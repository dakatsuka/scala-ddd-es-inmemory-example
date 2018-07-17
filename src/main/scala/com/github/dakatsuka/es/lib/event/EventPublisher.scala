package com.github.dakatsuka.es.lib.event

import com.twitter.util.Future

trait EventPublisher[Ev] {
  protected val queue: EventQueue[Ev]
  protected val self: EventPublisher[Ev] = this.asInstanceOf[EventPublisher[Ev]]

  private[event] val subscribers = scala.collection.mutable.Set[EventSubscriber[Ev]]()

  def subscribe[S <: EventSubscriber[Ev]](subs: S*): this.type = {
    subscribers ++= subs
    this
  }

  def unsubscribe[S <: EventSubscriber[Ev]](sub: S): this.type = {
    subscribers -= sub
    this
  }

  def publish(event: Ev): Unit = queue.enqueue(event)

  def polling(): Future[Unit] = {
    handle().flatMap {
      case Some(_) => polling()
      case None    => Future.Unit
    }
  }

  private def handle(): Future[Option[Unit]] = queue.dequeue() match {
    case Some(event) => Future.collect(subscribers.map(_.receive(self, event)).toSeq).map(_ => Some())
    case None        => Future.None
  }
}
