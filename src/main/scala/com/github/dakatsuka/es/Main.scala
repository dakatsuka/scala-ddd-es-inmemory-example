package com.github.dakatsuka.es

import com.github.dakatsuka.es.application.PhoneNumberUseCase
import com.github.dakatsuka.es.domain.PhoneNumberDB
import com.twitter.util.Await

object Main extends App {
  val useCase = new PhoneNumberUseCase

  Await.result {
    for {
      _ <- useCase.run()
    } yield {
      println(PhoneNumberDB.state)
      println(PhoneNumberDB.message)
    }
  }
}
