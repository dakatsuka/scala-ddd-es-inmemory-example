package com.github.dakatsuka.es.application

import com.github.dakatsuka.es.domain.{PhoneNumberExecutiveFactory, PhoneNumberState}
import com.twitter.util.Future

class PhoneNumberUseCase {
  def run(): Future[Unit] = {
    val executiveFactory = new PhoneNumberExecutiveFactory
    val executive = executiveFactory.factory()

    executive.publish(PhoneNumberState.Initial)
    executive.run()
  }
}
