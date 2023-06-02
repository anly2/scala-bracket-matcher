package me.aanchev

class SecretHandshakeTest extends org.scalatest.flatspec.AnyFlatSpec {
  "Secret Handshake" should "decode steps for code 9" in {
    assert(SecretHandshake.getHandshakeSteps(9) === List("wink", "jump"))
  }
  "Secret Handshake" should "decode steps for code 26" in {
    assert(SecretHandshake.getHandshakeSteps(26) === List("jump", "double blink"))
  }
}
