package com.github.dakatsuka.es.domain.command.admin

import com.github.dakatsuka.es.domain.command.CommandHandler
import com.twitter.util.Future

class CreateUserHandler extends CommandHandler[CreateUser] {
  override def handleCommand(command: CreateUser): Future[Unit] = {
    println("CreateUser")
    Future.Unit
  }
}
