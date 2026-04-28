package algo.practice.a_common_code_functions;

import java.util.Objects;

public class Validations {
    public static void main(String[] args){
        Objects.requireNonNull("123");
        String a = null;
        Objects.requireNonNull(a);
    }

}
