package com.github.dakatsuka.es.domain.event

import com.github.dakatsuka.es.lib.message.Message

abstract class DomainEvent extends Message {
  def eventId: DomainEventId
  def eventCreatedAt: DomainEventCreatedAt
}
