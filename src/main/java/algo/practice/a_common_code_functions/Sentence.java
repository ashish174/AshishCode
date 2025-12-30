package algo.practice.a_common_code_functions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sentence {

    String sentence1 = "Ram is a good boy";

    void sample(){

        log.info("Original string : {}", sentence1);

        // For string reversal use, stringBuilder.reverse()

        //reverse position
        String[] splits = sentence1.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = splits.length-1; i >= 0; i--) {
            stringBuilder.append(splits[i]).append(" ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        String finalReversedString = stringBuilder.toString();
        log.info("Position reversal : {}", finalReversedString);


        // reverse word by word
        String[] splits2 = sentence1.split(" ");
        StringBuilder stringBuilder2 = new StringBuilder();
        for(int i = 0 ; i < splits2.length; i++) {
            StringBuilder builder = new StringBuilder(splits2[i]);
            String reverseString2 = builder.reverse().toString();
            stringBuilder2.append(reverseString2).append(" ");
        }
        stringBuilder2.deleteCharAt(stringBuilder2.length()-1);
        String finalReversedString2 = stringBuilder2.toString();
        log.info("Word by Word reversal : {}", finalReversedString2);
    }

    public static void main(String[] args){
        Sentence sentence = new Sentence();
        sentence.sample();

    }

}
