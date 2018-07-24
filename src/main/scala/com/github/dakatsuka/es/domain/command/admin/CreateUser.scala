package com.github.dakatsuka.es.domain.command.admin

import com.github.dakatsuka.es.domain.command.Command

case class CreateUser(name: String) extends Command
