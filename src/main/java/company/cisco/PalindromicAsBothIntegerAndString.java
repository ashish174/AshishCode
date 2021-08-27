package company.cisco;

public class PalindromicAsBothIntegerAndString {

    public static void main(String[] args) {
        System.out.println(getSumOfDoubleBasePalindromes(5));
    }

    public static int getSumOfDoubleBasePalindromes(int maximum) {
        int doublebasePlaindromeSum = 0;
        for(int num = 1; num <= maximum; num++){
            if(isNumberDoublePalindrome(num)){
                doublebasePlaindromeSum+=num;
            }
        }
        return doublebasePlaindromeSum;

    }

    private static boolean isNumberDoublePalindrome(int num){
        return isNumPalindromeInDecimal(num) && isNumberPalindromeInBinary(num);
    }

    private static boolean isNumPalindromeInDecimal(int num){
        String numInString = String.valueOf(num);
        return checkIfPalindrome(numInString);

    }

    private static boolean isNumberPalindromeInBinary(int num){
        String numInBinaryString = Integer.toBinaryString(num);
        return checkIfPalindrome(numInBinaryString);
    }

    private static boolean checkIfPalindrome(String myString){
        int i = 0;
        int j = myString.length()-1;
        while(i < j){
            if(myString.charAt(i)!=myString.charAt(j)){
                return false;
            }
        }
        return true;
    }
}
