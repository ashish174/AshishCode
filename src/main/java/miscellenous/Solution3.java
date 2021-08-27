package miscellenous; /**
 * Problem statement:
 * Given a string and a chunk size, perform deduplication on it. The process of deduplication returns an
 * intermediate string. The intermediate string is then used to perform reduplication. The process of
 * reduplication gives back the original string.
 *
 * Steps to be followed:
 * 1. Break the string into chunks of the given size (Values of chunk size can be 1KB,10KB and so on).
 * 2. Find the unique chunks and make a note of where these chunks occur in the string.
 * 3. The intermediate string should contain the unique strings and their positions.
 * 4. This string alone should be used to perform reduplication, which constructs the original string.
 *
 * Input:
 * abcdexyzvwabcde
 * chunk size: 5 bytes
 *
 * Output after deduplication:
 * abcde-0-2,xyzvw1
 *
 * Output after reduplication:
 * abcdexyzvwabcde
 */

import java.math.BigInteger;
import java.util.HashMap;

public class Solution3 {

    static String dedup(String inputStr, int chunkSize) {
        int chunks = 0;
        StringBuilder dedup = new StringBuilder();
        for(int i = 0; i< inputStr.length();){
            String str = inputStr.substring(i,i+chunkSize);
            if(dedup.indexOf(str) != -1){
                int ind = dedup.indexOf(str);
                dedup.insert(dedup.indexOf(",", ind), "-"+chunks);
            }
            else{
                dedup.append(str+"-"+chunks+",");
            }
            chunks++;
            i+=chunkSize;
        }

        return dedup.toString();
    }

    static String redup(String deduplicatedStr, int chunkSize) {
        String str = "";
        for(int i = 0; i< deduplicatedStr.length();i++) {
            String str1 = deduplicatedStr.substring(i, i + chunkSize);
            int chunkIndex = deduplicatedStr.indexOf(",",i+chunkSize);
            String chunk = deduplicatedStr.substring(i+chunkSize, chunkIndex);
            System.out.println(chunk);
        }

        return "";
    }

    public static void main(String[] args) {
        solution(978);
        //redup(dedupStr, 5);
    }

    public static int solution(int N) {
        // write your code in Java SE 8
      if(N<=1)
          return N;
      BigInteger N0 = BigInteger.valueOf(0);
      BigInteger N1 = BigInteger.valueOf(1);
      BigInteger N2 = BigInteger.valueOf(0);
      for (int itr = 2; itr <=N; itr++){
          N2 = N0.add(N1);
          N0 = N1;
          N1 = N2;
      }
      return  N2.mod(BigInteger.valueOf(1000000)).intValue();

    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        if(A.length==0)
            return 0;
        int length = 1;
        int currIndx = 0;
        int oldIndx;
        while(A[currIndx] != -1 && A[currIndx] != -2){
            oldIndx = currIndx;
            currIndx = A[currIndx];
            A[oldIndx] = -2;
            length++;
        }
        return length;

    }


}
