package inheritance;

abstract class Level2 extends Level1{

    @Override
    void initVerifier() {
        System.out.println("initVerifier: Level2");
    }
}
