package com.github.dakatsuka.es.domain

import com.github.dakatsuka.es.lib.event.EventQueue

class PhoneNumberExecutiveFactory {
  def factory(): PhoneNumberExecutive = {
    val instance = new PhoneNumberExecutive(
      queue = EventQueue.inMemoryImpl[PhoneNumberState](),
      tracker = new PhoneNumberStateTracker
    )

    val phoneNumberProcessSubscriber = new PhoneNumberProcessSubscriber
    val phoneNumberPreCompleteOneSubscriber = new PhoneNumberPreCompleteOneSubscriber
    val phoneNumberPreCompleteTwoSubscriber = new PhoneNumberPreCompleteTwoSubscriber

    instance
      .subscribe(instance)
      .subscribe(phoneNumberProcessSubscriber)
      .subscribe(phoneNumberPreCompleteOneSubscriber)
      .subscribe(phoneNumberPreCompleteTwoSubscriber)
  }
}
