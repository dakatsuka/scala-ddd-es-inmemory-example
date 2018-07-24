package com.github.dakatsuka.es.lib.message

import com.twitter.util.Future

trait MessageQueue[M <: Message] {
  def enqueue(msg: M): Future[Unit]
  def dequeue(): Future[Option[M]]
}
