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

  def publish(event: Ev): Future[Unit] = queue.enqueue(event)
}
