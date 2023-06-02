import scala.collection.mutable
import scala.language.postfixOps
import scala.util.matching.Regex

object BracketMatcher {
  private val BRACKETS_PATTERN =
    "(\\()|(\\))|" +
    "(\\[)|(\\])|" +
    "(\\{)|(\\})"r


  def validateBrackets(input: String): Boolean = {
    val tokens = getBracketTokens(input)

    if (!tokens.hasNext) {
      // Strings without brackets are valid
      return true
    }

    while (tokens.hasNext) {
      findNextMatchingClosingBracket(tokens) match {
        case None => return false
        case _ => {}
      }
    }
    true
  }

  def findNextMatchingClosingBracket(tokens: Iterator[BracketToken]): Option[BracketToken] = {
    val open = new mutable.Stack[BracketToken]
    if (!tokens.hasNext) return None

    val opening = tokens.next()
    if (opening.isClosing) {
      println("Expected an opening bracket but found a closing one at " + opening.position + ": " + opening.value);
      return None
    }
    open.push(opening);

    for (token <- tokens) {
      if (!token.isClosing) {
        open.push(token)
      }
      else { // scala does not have continue?!
        val lastOpen = open.pop()
        if (lastOpen.bracketType != token.bracketType) {
          println("Expected a matching closing bracket for " + lastOpen.value + " (at " + lastOpen.position + ")" +
            " but found a different type of bracket at " + token.position + ": " + token.value);
          return None;
        }
        if (open.isEmpty) {
          return Some(token)
        }
      }
    }
    println("Ran out of bracket tokens" +
      " while looking for matching closing bracket of " + opening.value + "(at " + opening.position + ")." +
      " (Found " + open.size + " open brackets)")
    None
  }


  def getBracketTokens(input: String): Iterator[BracketToken] = {
    BRACKETS_PATTERN.findAllMatchIn(input).map(m => {
      val i = firstNonEmptyGroupNum(m)
      BracketToken(
        m.start,
        m.matched,
        (i - 1) / 2,
        i % 2 == 0
      )
    })
  }

  def firstNonEmptyGroupNum(m: Regex.Match): Int = {
    (1 to m.groupCount).find(i => m.group(i) != null).get
  }

  case class BracketToken(
    position: Int,
    value: String,
    bracketType: Int,
    isClosing: Boolean
  )
}