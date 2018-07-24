package com.github.dakatsuka.es.lib.message

trait MessageQueueFactory {
  def factory[M <: Message](): MessageQueue[M]
}
