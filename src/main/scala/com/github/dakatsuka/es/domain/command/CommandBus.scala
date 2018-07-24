package com.github.dakatsuka.es.domain.command

import com.twitter.util.Future

import scala.reflect.runtime.{universe => ru}
import ru._

class CommandBus {
  private val handlers = scala.collection.mutable.Map[Type, CommandHandler[_]]()

  /**
   * コマンドハンドラーをバスに登録するメソッド
   */
  def register[CMD <: Command : TypeTag](handler: CommandHandler[CMD]): this.type = {
    val typeKey = typeOf[CMD]

    if (handlers.keys.exists(_ == typeKey)) {
      throw new IllegalArgumentException(s"The handler for this command has already been registered: $typeKey")
    } else {
      handlers ++= Map(typeOf[CMD] -> handler)
      this
    }
  }

  /**
   * コマンドを受け取ってコマンドハンドラーに処理を移譲するメソッド（ユースケースなどアプリケーション層がコマンドを投げてくる想定）
   */
  def send[CMD <: Command : TypeTag](command: CMD): Future[Unit] = {
    val typeKey = typeOf[CMD]

    val handlerOpt = handlers.get(typeKey).map(_.asInstanceOf[CommandHandler[CMD]])

    handlerOpt match {
      case Some(handler) => handler.handleCommand(command)
      case None          => Future.exception(new IllegalArgumentException(s"Unknown command: $command"))
    }
  }
}
