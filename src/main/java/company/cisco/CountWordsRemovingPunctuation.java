package company.cisco;

/**
 * Remove all punctuation except "-"
 */
public class CountWordsRemovingPunctuation {
    public static void main(String[] args) {
        String sentence = "he is a good programmer, he won 865 competitions, but sometimes he dont. What do you think? All test-cases should pass. Done-done?";
        //System.out.println(howMany(sentence));
        System.out.println(isWordContainAlphabetsOnly("hi-hi"));
    }

    public static int howMany(String sentence) {
        // Write your code here
        System.out.println(sentence);
        sentence = sentence.replaceAll("[.,?!]", " ");
        System.out.println(sentence);

        String[] split = sentence.split("\\s+");

        int wordCount = 0;
        for(String word : split){
            boolean wordContainAlphabetsOnly = isWordContainAlphabetsOnly(word);
            if(wordContainAlphabetsOnly){
                wordCount++;
            }
            System.out.println(word +" "+wordContainAlphabetsOnly);
        }
        return wordCount;

    }

    private static boolean isWordContainAlphabetsOnly(String word){

        return word.matches("[a-zA-Z-]+");
    }
}
