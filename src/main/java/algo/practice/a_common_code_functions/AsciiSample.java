package algo.practice.a_common_code_functions;

import lombok.extern.slf4j.Slf4j;

/**
 * 0-9 : 48-57
 * A-Z : 65-90
 * a-z : 97-122
 *
 */
@Slf4j
public class AsciiSample {
    public static void main(String[] args){
        int posA = 'A';
        log.info("Position of A : {}", posA);
        char charA = (char) posA;
        log.info("Char : {}", charA);
    }
}
