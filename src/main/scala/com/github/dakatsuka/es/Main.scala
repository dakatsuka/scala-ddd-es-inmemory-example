package com.github.dakatsuka.es

import com.github.dakatsuka.es.domain.command.CommandBus
import com.github.dakatsuka.es.domain.command.admin.{CreateUser, CreateUserHandler}
import com.github.dakatsuka.es.domain.command.user.{ChangeUserName, ChangeUserNameHandler}
import com.github.dakatsuka.es.domain.model.user.UserId
import com.twitter.util.Await

object Main extends App {
  val createUserHandler = new CreateUserHandler
  val changeUserNameHandler = new ChangeUserNameHandler

  val commandBus = new CommandBus
  commandBus
    .register(createUserHandler)
    .register(changeUserNameHandler)

  Await.result {
    for {
      _ <- commandBus.send(ChangeUserName(UserId(1), "bob"))
      _ <- commandBus.send(CreateUser("job"))
    } yield ()
  }
}
