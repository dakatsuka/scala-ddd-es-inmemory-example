package com.github.dakatsuka.es

import com.github.dakatsuka.es.domain.command.CommandBus
import com.github.dakatsuka.es.domain.command.admin.{CreateUser, CreateUserHandler}
import com.github.dakatsuka.es.domain.command.user.{ChangeUserName, ChangeUserNameHandler}
import com.github.dakatsuka.es.domain.event.{DomainEvent, DomainEventBus}
import com.github.dakatsuka.es.domain.model.user.UserId
import com.github.dakatsuka.es.lib.message.MessageSubscriber
import com.twitter.util.{Await, Future}

object Main extends App {
  val createUserHandler = new CreateUserHandler
  val changeUserNameHandler = new ChangeUserNameHandler

  implicit val domainEventBus: DomainEventBus = new DomainEventBus {
    override def subscribe(sub: MessageSubscriber[DomainEvent]): this.type = ???
    override def unsubscribe(sub: MessageSubscriber[DomainEvent]): this.type = ???
    override def publish(msg: DomainEvent): Future[Unit] = ???
  }

  val commandBus = new CommandBus
  commandBus
    .register(createUserHandler)
    .register(changeUserNameHandler)

  Await.result {
    for {
      _ <- commandBus.send(CreateUser("job"))
      _ <- commandBus.send(ChangeUserName(UserId(1), "bob"))
    } yield ()
  }
}
