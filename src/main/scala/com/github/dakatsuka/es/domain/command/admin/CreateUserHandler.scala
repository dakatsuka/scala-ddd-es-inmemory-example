package com.github.dakatsuka.es.domain.command.admin

import com.github.dakatsuka.es.domain.command.CommandHandler
import com.github.dakatsuka.es.domain.event.DomainEventBus
import com.twitter.util.Future

class CreateUserHandler extends CommandHandler[CreateUser] {
  override def handleCommand(command: CreateUser)(implicit bus: DomainEventBus): Future[Unit] = {
    println("CreateUser")
    Future.Unit
  }
}
