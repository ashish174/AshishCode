package algo.practice.dp.typeLIS;

import java.util.Arrays;
import java.util.Comparator;

public class WordChain {
    public static void main(String[] args) {
        //["ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"]
        //ks gr kss gru czvh gruj kssq zczvh ksqsq grukj zcpzvh grukmj ksqvsq ksqvsyq zczpzvh grukkmj zczpzvhx zczpzvdhx zczpzfvdhx
        String[] words = new String[]{
            "ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"

        };
        System.out.println(new WordChain().longestStrChain(words));
    }
    public int longestStrChain(String[] words) {
        if(words.length==0){
            return 0;
        }
        int[] dp = new int[words.length];
        Arrays.fill(dp, 1);
        int maxChainSize=1;
        sortByLength(words);
        for(int i=1; i<words.length;i++){
            String str = words[i];
            for(int j=0; j<i; j++){
                if(isNextInWordChain(words[j], words[i]) && dp[i]<dp[j]+1){
                    dp[i]=dp[j]+1;
                }
            }
            if(maxChainSize<dp[i]){
                maxChainSize = dp[i];
            }
        }
        return maxChainSize;
    }

    private void sortByLength(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });
    }

    boolean isNextInWordChain(String str1, String str2){
        if(str1.length()+1!=str2.length()){
            return false;
        }
        int diff = 0;
        int i=0;
        int j=0;
        while(j<str2.length()){
            if(i<str1.length() && str1.charAt(i)==str2.charAt(j)){
                i++;
            }
            j++;
        }
        if(str1.length()==i && i+1==j){
            return true;
        }
        return false;

    }
}
