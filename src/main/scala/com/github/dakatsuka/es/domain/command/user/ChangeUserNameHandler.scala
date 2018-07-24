package com.github.dakatsuka.es.domain.command.user

import com.github.dakatsuka.es.domain.command.CommandHandler
import com.twitter.util.Future

class ChangeUserNameHandler extends CommandHandler[ChangeUserName] {
  override def handleCommand(command: ChangeUserName): Future[Unit] = {
    println("ChangeUserName")
    Future.Unit
  }
}
