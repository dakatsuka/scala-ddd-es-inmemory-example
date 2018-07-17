package com.github.dakatsuka.es.domain

class PhoneNumberStateTracker extends Tracker {
  private val states = scala.collection.mutable.Set[PhoneNumberState]()

  def addState(state: PhoneNumberState): Unit = states += state

  def isComplete: Boolean = states.contains(PhoneNumberState.PreCompleted1) && states.contains(PhoneNumberState.PreCompleted2)
}
