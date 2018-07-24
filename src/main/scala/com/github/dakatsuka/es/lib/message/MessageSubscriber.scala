package com.github.dakatsuka.es.lib.message

import com.twitter.util.Future

trait MessageSubscriber[M <: Message] {
  def receive(pub: MessagePublisher[M]): PartialFunction[M, Future[Unit]]
}
