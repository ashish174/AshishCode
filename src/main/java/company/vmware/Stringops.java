package company.vmware;

public class Stringops {

    // Str1 -> Str2
    // Add a character, Replace a char, delete a char
    // a , b ->
    // abcdk -> abck

    /**
     * abcd -> abck
     * abcdk -> abck
     * abck -> abck
     * <p>
     * <p>
     * dp[i,j]  = dp[i-1, j-1],  if a[i] = b[j]
     * = 1 + Min(dp[i, j-1], Add    ,  if a[i] = b[j]
     * dp[i-1, j-1], Replace
     * dp[i-1, j]) Delete
     *
     * @param args
     */


    public static void main(String[] args) {

        int minOps = minOpsRequiredToConvert("ab", "bc");
        System.out.println(minOps);


    }

    //ab , "" -> 2 delete
    //"" , ab -> 2 Add
    private static int minOpsRequiredToConvert(String firstString, String secondString) {
        int size1 = firstString.length();
        int size2 = secondString.length();
        Integer[][] dp = new Integer[firstString.length() + 1][firstString.length() + 1];
        for (int i = 0; i <= size1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= size2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= firstString.length(); i++) {
            for (int j = 1; j <= secondString.length(); j++) {
                if (firstString.charAt(i - 1) == secondString.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + findMin(dp[i][j - 1], dp[i - 1][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[firstString.length()][secondString.length()];
    }

    /**
     * dp[m,n] = left, right, diagonal
     *
     * @param integer
     * @param integer1
     * @param integer2
     * @return
     */
    private static int findMin(Integer integer, Integer integer1, Integer integer2) {
        return Math.min(integer, Math.min(integer1, integer2));
    }


}
