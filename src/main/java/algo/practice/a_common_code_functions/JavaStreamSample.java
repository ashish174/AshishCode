package algo.practice.a_common_code_functions;

public class JavaStreamSample {


    void sampleFunction(){
        /*
         * :: - method reference operator (no need of (), just method name is sufficient).
         * :: - is only for methods and constructors, not variables.
         *
         * If a lambda just calls one method with the same parameters → use ::
         * Ex: (s -> System.out.println(s))      :       (System.out::println)
         * Usecases:
         * 1. ClassName::staticMethod     (MyClass::doStaticfunc)
         * 2. ClassName::instanceMethod
         * 3. object::instanceMethod      (myObject::doFunc)

         */

    }

}
