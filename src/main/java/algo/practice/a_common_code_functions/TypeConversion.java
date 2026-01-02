package algo.practice.a_common_code_functions;

/**
 * Widening : No cast needed for widening
 *      byte/char → short → int → long → float → double
 *      int i = 'A';
 *
 * Narrowing : Explicit cast required, as there is data loss
 *      double -> float -> long -> int -> short -> char/byte
 *      char c = (char) i
 *
 *
 */
public class TypeConversion {
    // int to double
    // string to int
    void sampleFunction(){
        // From String
        String s = "123";
        int i = Integer.parseInt(s);
        long l = Long.parseLong(s);
        double d = Double.parseDouble(s);
        boolean b = Boolean.parseBoolean("false");

        //To String
        //valueOf() can be used for int/long/double/boolean/char anything
        String s1= String.valueOf(123);
        String s2 = Integer.toString(123);
        String s3 = ""+123;



    }
}
