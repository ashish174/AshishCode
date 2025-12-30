package algo.practice.a_common_code_functions;

import java.util.Optional;

public class OptionalSample {

    public static void main(String[] args){
        Optional<String> optional = Optional.of("null");
        System.out.println(optional.isPresent());
        System.out.println(optional.isEmpty());
    }
}
