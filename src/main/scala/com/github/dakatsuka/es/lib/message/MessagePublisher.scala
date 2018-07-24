package com.github.dakatsuka.es.lib.message

import com.twitter.util.Future

trait MessagePublisher[M <: Message] {
  def publish(msg: M): Future[Unit]
  def subscribe(sub: MessageSubscriber[M]): this.type
  def unsubscribe(sub: MessageSubscriber[M]): this.type
}
