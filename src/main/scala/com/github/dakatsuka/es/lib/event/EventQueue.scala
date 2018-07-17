package com.github.dakatsuka.es.lib.event

import com.twitter.util.Future

import scala.util.control.Exception._

trait EventQueue[Ev] {
  def enqueue(events: Ev*): Future[Unit]
  def dequeue(): Future[Option[Ev]]
}

object EventQueue {
  def inMemoryImpl[Ev](): EventQueue[Ev] = new EventQueue[Ev] {
    private val underlying = scala.collection.mutable.Queue[Ev]()

    override def enqueue(events: Ev*): Future[Unit] = Future.value(underlying.enqueue(events: _*))
    override def dequeue(): Future[Option[Ev]] = Future.value(allCatch opt underlying.dequeue())
  }
}
