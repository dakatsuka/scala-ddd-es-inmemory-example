package com.github.dakatsuka.es.lib.event

import scala.util.control.Exception._

trait EventQueue[Ev] {
  def enqueue(events: Ev*): Unit
  def dequeue(): Option[Ev]
}

object EventQueue {
  def inMemoryImpl[Ev](): EventQueue[Ev] = new EventQueue[Ev] {
    private val underlying = scala.collection.mutable.Queue[Ev]()

    override def enqueue(events: Ev*): Unit = underlying.enqueue(events: _*)
    override def dequeue(): Option[Ev] = allCatch opt underlying.dequeue()
  }
}
