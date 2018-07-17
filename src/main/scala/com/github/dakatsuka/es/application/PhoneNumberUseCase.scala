package com.github.dakatsuka.es.application

import com.github.dakatsuka.es.domain.{PhoneNumberExecutiveFactory, PhoneNumberState}
import com.twitter.util.Future

class PhoneNumberUseCase {
  def run(): Future[Unit] = {
    val executiveFactory = new PhoneNumberExecutiveFactory
    val executive = executiveFactory.factory()

    for {
       _ <- executive.publish(PhoneNumberState.Initial)
       _ <- executive.run()
    } yield ()
  }
}
