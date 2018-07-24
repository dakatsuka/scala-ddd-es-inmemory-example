package com.github.dakatsuka.es.domain.command

import com.twitter.util.Future

trait CommandHandler[CMD <: Command] {
  def handleCommand(command: CMD): Future[Unit]
}
