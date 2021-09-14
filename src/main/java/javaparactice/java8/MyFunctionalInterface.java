package javaparactice.java8;

public interface MyFunctionalInterface {
    int doOperation(int a, int b);
    default void print(){
        System.out.println("This is a deafault function");
    }

    static void main(String[] args) {
        int n1 = 4;
        int n2 = 5;

        MyFunctionalInterface addInstanceUsingAnonymousClass = new MyFunctionalInterface() {
            @Override
            public int doOperation(int a, int b) {
                return a+b;
            }
        };

        MyFunctionalInterface addInstanceUsingLambda = (a,b) -> a+b;

        MyFunctionalInterface multiplyUsingAnonymousClass = new MyFunctionalInterface() {
            @Override
            public int doOperation(int a, int b) {
                return a * b;
            }
        };

        MyFunctionalInterface multiplyUsingLambda = (a,b) -> {
            System.out.println("This is multiplication");
            return a * b;
        };

        System.out.println(addInstanceUsingAnonymousClass.doOperation(n1,n2));
        System.out.println(addInstanceUsingLambda.doOperation(n1,n2));
        System.out.println(multiplyUsingAnonymousClass.doOperation(n1,n2));
        System.out.println(multiplyUsingLambda.doOperation(n1,n2));


    }

}


