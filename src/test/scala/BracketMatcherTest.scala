class BracketMatcherTest extends org.scalatest.flatspec.AnyFlatSpec {
  "Bracket Matcher" should "accept strings with no brackets at all" in {
    assert(BracketMatcher.validateBrackets("hello world") === true)
  }
  "Bracket Matcher" should "accept strings with single brackets pair" in {
    assert(BracketMatcher.validateBrackets("hello (good) world") === true)
  }
  "Bracket Matcher" should "accept strings with different bracket pairs outside each other" in {
    assert(BracketMatcher.validateBrackets("Lorem (ipsum) (dolor) {sit} [amet], {consectetur} (adipiscing) elit") === true)
  }
  "Bracket Matcher" should "accept strings with bracket pairs nested" in {
    assert(BracketMatcher.validateBrackets("Lorem (ipsum (dolor)) ({sit} [amet]), {consectetur (adipiscing) elit}") === true)
  }

  "Bracket Matcher" should "reject strings with unclosed bracket pairs of the same type" in {
    assert(BracketMatcher.validateBrackets("hello (good world") === false)
  }
  "Bracket Matcher" should "reject strings with unbalanced bracket pairs of the same type" in {
    assert(BracketMatcher.validateBrackets("hello )good( world") === false)
  }
  "Bracket Matcher" should "reject strings with unbalanced bracket pairs of different type" in {
    assert(BracketMatcher.validateBrackets("hello ({good) world") === false)
  }
}
