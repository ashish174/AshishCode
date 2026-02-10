package algo.practice.a_common_code_functions;

public class MathSample {
    public static void main(String[] args){
        //Accepts double as input
        Math.ceil(12.15);
        Math.floor(12.15);
        //Since 4/5 is int and division will return int value 0, so we have to use double so value would be 0.8
        //And then use ceil
        int ceil = (int) Math.ceil((double)4/5);
    }

}
