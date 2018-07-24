package com.github.dakatsuka.es.domain.event

import java.util.UUID

case class DomainEventId(value: String)

object DomainEventId {
  def generate(): DomainEventId = DomainEventId(UUID.randomUUID().toString)
}
