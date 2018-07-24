package com.github.dakatsuka.es.infrastructure.message

import java.util.concurrent.LinkedBlockingQueue

import com.github.dakatsuka.es.lib.message.{Message, MessageQueue, MessageQueueFactory}
import com.twitter.util.Future

class MessageQueueInMemoryImpl[M <: Message] extends MessageQueue[M] {
  private val underlying = new LinkedBlockingQueue[M]()

  override def enqueue(msg: M): Future[Unit] = Future.value(underlying.put(msg))
  override def dequeue(): Future[Option[M]] = Future.value(Option(underlying.poll()))
}

object MessageQueueInMemoryImpl extends MessageQueueFactory {
  override def factory[M <: Message](): MessageQueue[M] = new MessageQueueInMemoryImpl[M]
}
