package algo.practice.a_common_code_functions;

/**
 * In your code, normally throw runtime exception, as they need not be handled by your code.
 * So need to do catch exception.
 */
public class ExceptionSample {

    boolean incorrectState;
    boolean incorrectArguement;
    boolean runtimeError;

    void checkException(){
        if(incorrectState){
            throw new IllegalStateException();
        }
        if(incorrectArguement){
            throw new IllegalArgumentException();
        }
        if(runtimeError){
            throw new RuntimeException();
        }

    }
    public static void main(String[] args){

    }
}
