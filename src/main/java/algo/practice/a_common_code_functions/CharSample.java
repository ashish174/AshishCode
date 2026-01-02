package algo.practice.a_common_code_functions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CharSample {

    String sentence1 = "kalpokram";
    void sampleFunction() {

        // to char array
        char[] charArray = sentence1.toCharArray();


        char ch = '5';
        int num1 = Character.getNumericValue(ch);
        int num2 = Integer.parseInt(Character.toString(ch));
        int num3 = Integer.parseInt(String.valueOf(ch));


        // Character class features
        Character.isDigit('2');
        Character.isAlphabetic('b');
        Character.isLetterOrDigit('2');
        Character.toLowerCase('A');
        Character.toUpperCase('a');


    }

    public static void main(String[] args){
        CharSample sample = new CharSample();
        log.info("{}", sample.isPalindrome("Was it a car or a cat I saw?"));
    }

    public boolean isPalindrome(String s) {
        StringBuilder builder = new StringBuilder();
        for(char ch : s.toCharArray()){
            if((ch >= 65 && ch <=90) || (ch >=97 && ch <=122) || (ch >=48 && ch <=57) ) {
                builder.append(ch);
            }
        }
        String str = builder.toString();
        String reverseStr = builder.reverse().toString();
        return str.equalsIgnoreCase(reverseStr);
    }
}
