package algo.practice.string;

import java.util.function.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class StringRegexOps {
    private static Logger logger = LoggerFactory.getLogger(StringRegexOps.class);
    public static void main(String[] args) {
        String originalString;
        originalString = "ashi?sh i~s a g!o&o*d b%oy!!";
        logger.info("For String :- [{}]   Punctuation Sanitized String is :- [{}]", originalString, sanitizePunctuatuon(originalString));
        originalString = "ashish      is     hum an";
        logger.info("For String :- [{}]   Space Sanitized String is :- [{}]", originalString, sanitizeSpace(originalString));
        originalString = "ashi837929sh is     h0390uman083439";
        logger.info("For String :- [{}]   Number Sanitized String is :- [{}]", originalString, sanitizedigit(originalString));
    }

    private static String sanitizePunctuatuon(String string) {
        return string.replaceAll("[?!~*&^%]", "");
    }

    private static String sanitizeSpace(String string){
        //  use \s
        return string.replaceAll("\\s", "");
    }

    private static String sanitizedigit(String string){
        //  use \d or, [A-Za-z] or [^0-9]
        return string.replaceAll("\\d", "");
    }

}
