package com.github.dakatsuka.es.domain.event.user

import com.github.dakatsuka.es.domain.event.{DomainEvent, DomainEventCreatedAt, DomainEventId}

case class UserNameChanged(
  eventId: DomainEventId,
  eventCreatedAt: DomainEventCreatedAt
) extends DomainEvent
