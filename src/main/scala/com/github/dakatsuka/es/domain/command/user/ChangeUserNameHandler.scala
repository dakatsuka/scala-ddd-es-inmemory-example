package com.github.dakatsuka.es.domain.command.user

import com.github.dakatsuka.es.domain.command.CommandHandler
import com.github.dakatsuka.es.domain.event.DomainEventBus
import com.twitter.util.Future

class ChangeUserNameHandler extends CommandHandler[ChangeUserName] {
  override def handleCommand(command: ChangeUserName)(implicit bus: DomainEventBus): Future[Unit] = {
    println("ChangeUserName")
    Future.Unit
  }
}
