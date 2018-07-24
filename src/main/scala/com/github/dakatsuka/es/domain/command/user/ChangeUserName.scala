package com.github.dakatsuka.es.domain.command.user

import com.github.dakatsuka.es.domain.command.Command
import com.github.dakatsuka.es.domain.model.user.UserId

case class ChangeUserName(userId: UserId, newName: String) extends Command
