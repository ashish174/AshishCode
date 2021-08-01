package company.microsoft;

public class BillBoardBuilding {
    public int solution(String S, String[] L) {
        int[] SLettersCount = new int[26];
        for (char ch : S.toCharArray()) {
            SLettersCount[ch - 65] = SLettersCount[ch - 65] + 1;
        }
        int numberOfWords = 0;
        for (String word : L) {
            int wordMinimum = Integer.MAX_VALUE;
            int[] wordLetters = new int[26];
            for (char wordCh : word.toCharArray()) {
                wordLetters[wordCh - 65] = wordLetters[wordCh - 65] + 1;
            }
            for (int idx = 0; idx < 26; idx++) {
                if (wordLetters[idx] > 0) {
                    wordMinimum = Math.min(wordMinimum, SLettersCount[idx] / wordLetters[idx]);
                }
            }
            //System.out.println("No. of possibility for word: " + word + ", count: " + wordMinimum);
            numberOfWords = Math.max(numberOfWords, wordMinimum);
        }
        //System.out.println("Maximum no of words: " + numberOfWords);
        return numberOfWords;
    }
}
