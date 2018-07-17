package com.github.dakatsuka.es.lib.event

import com.twitter.util.Future

trait EventSubscriber[Ev] {
  def receive(publisher: EventPublisher[Ev], event: Ev): Future[Unit]
}
