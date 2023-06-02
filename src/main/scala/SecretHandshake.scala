package me.aanchev

import java.util


object SecretHandshake {
  val FLAGS = Map(
    1 -> "wink",
    (1 << 1) -> "double blink",
    (1 << 2) -> "close your eyes",
    (1 << 3) -> "jump",
  )
  val FLAG_REVERSE = 1 << 4

  def getHandshakeSteps(code: Int) = {
    var steps = List[String]()
    for ((mask, step) <- FLAGS) {
      if ((code & mask) > 0) {
        steps = steps.appended(step)
      }
    }
    if ((code & FLAG_REVERSE) > 0) {
      steps = steps.reverse
    }
    steps
  }
}