package inheritance;

abstract class Level1 {
    void isApplicable(){
        System.out.println("isApplicable: Level1");
        initVerifier();
    }

    void initVerifier() {
        System.out.println("initVerifier: Level1");
    }
}
