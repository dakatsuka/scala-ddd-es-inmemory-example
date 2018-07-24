package com.github.dakatsuka.es.domain.event

import com.github.dakatsuka.es.lib.message.MessagePublisher

trait DomainEventBus extends MessagePublisher[DomainEvent]
