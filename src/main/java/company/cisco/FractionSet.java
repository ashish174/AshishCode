package company.cisco;

import java.util.HashSet;
import java.util.Set;

public class FractionSet {
    public static void main(String[] args) {
        System.out.println(countProperFractions(8));
    }



    public static int countProperFractions(int max_d) {

        int fractionCount = 0;
        Set<Float> fractionSet = new HashSet<>();
        for(int denominator = 2; denominator <= max_d; denominator++){
            for(int numerator = 1; numerator < denominator; numerator++){
                if(numerator%denominator!=0){
                    fractionCount++;
                    System.out.print(numerator+"/"+denominator+ " ");
                    fractionSet.add(((float)numerator)/((float)denominator));
                }
            }
        }
        return fractionSet.size();
    }
}
