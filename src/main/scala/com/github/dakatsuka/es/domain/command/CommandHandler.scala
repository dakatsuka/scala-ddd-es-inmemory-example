package com.github.dakatsuka.es.domain.command

import com.github.dakatsuka.es.domain.event.DomainEventBus
import com.twitter.util.Future

trait CommandHandler[CMD <: Command] {
  def handleCommand(command: CMD)(implicit bus: DomainEventBus): Future[Unit]
}
