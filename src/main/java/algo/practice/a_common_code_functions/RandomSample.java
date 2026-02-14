package algo.practice.a_common_code_functions;

import java.util.Random;

public class RandomSample {
    public static void main(String[] args) {
        //simple and quick.
        //Return a double value: 0.0  <=  value  <  1.0
        //double num = Math.random();
        // does not has a bound arg
        int num = (int)(Math.random() * 10);

        //more flexible and powerful
        Random random = new Random();
        random.nextInt(10); // 0 to 9
        random.nextInt();      // 0 to 1
        random.nextDouble();   // 0.0 to 1.0
        random.nextBoolean();  // true or false
        random.nextFloat();    // float 0.0 to 1.0
        random.nextLong();     // random long

        // Seed is not an upper bound
        // A seed is the starting value used by the random number generator algorithm.
        // It controls the sequence of numbers generated.
        // A seed controls the randomness.
        // If two Random objects use the same seed → they generate the same sequence.
        Random r1 = new Random(100);
        Random r2 = new Random(100);
        boolean equals = r1.nextInt() == r2.nextInt();
        System.out.println("Equal Seed : " +equals );

  }
}
