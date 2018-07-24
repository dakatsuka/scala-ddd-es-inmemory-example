package com.github.dakatsuka.es.domain.event

import java.time.Instant

case class DomainEventCreatedAt(value: Instant)

object DomainEventCreatedAt {
  def generate(): DomainEventCreatedAt = DomainEventCreatedAt(Instant.now())
}
