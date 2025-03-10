package algo.practice.string;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class JavaRegex {

  /**
   * Expression
   * [abc]	Find one character from the options between the brackets
   * [^abc]	Find one character NOT between the brackets
   * [0-9]	Find one character from the range 0 to 9
   *
   * MetaCharacter
   * |	Find a match for any one of the patterns separated by | as in: cat|dog|fish
   * .	Find just one instance of any character
   * ^	Finds a match as the beginning of a string as in: ^Hello
   * $	Finds a match at the end of the string as in: World$
   * \d	Find a digit
   * \s	Find a whitespace character
   * \b	Find a match at the beginning of a word like this: \bWORD, or at the end of a word like this: WORD\b
   *
   * Quantifiers
   * n+	Matches any string that contains at least one n
   * n*	Matches any string that contains zero or more occurrences of n
   * n?	Matches any string that contains zero or one occurrences of n
   * n{x}	Matches any string that contains a sequence of X n's
   * n{x,y}	Matches any string that contains a sequence of X to Y n's
   * n{x,}	Matches any string that contains a sequence of at least X n's
   *
   */
  public static void main(String[] args) {
        String sentence = "Visit W3Schools!";
        String regexOrPattern = "w3schools";
        if (isMatchingPatternFound(sentence, regexOrPattern)) {
            log.info("Match found for {} in sentence : {}", regexOrPattern, sentence);
        } else {
            log.info("Match Not found for {} in sentence : {}", regexOrPattern, sentence);
        }
    }

    public static boolean isMatchingPatternFound(String sentence, String regexOrPattern) {
        Pattern pattern = Pattern.compile(regexOrPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sentence);
        return matcher.find();
    }
}
