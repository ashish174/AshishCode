package interestcalculator;

public class test {


    public static void main(String[] args) {
        int[] numOfFactor = new int[10000000];

        System.out.println(findGoodNumber(9, numOfFactor));

    }

    public static int findGoodNumber(int k, int[] numOfFactor){
        int goodNumber = 0;
        int factorSum = 0;
        for(int i = 1; i <=k ; i++){
            if(numOfFactor[i] == 0){
                numOfFactor[i] = findNumOfFactor(i);
            }
            factorSum += numOfFactor[i];
            if(factorSum >= k){
                goodNumber = i;
                break;
            }
        }
        return goodNumber;
    }

    public static int findNumOfFactor(int num){
        int count = 0;
        int loopLimit = (int) Math.sqrt(num);
        for(int i = 1; i <= loopLimit; i++){
            if(num%i==0){
                count++;
            }
        }
        return count*2;
    }
}
