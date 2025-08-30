package inheritance;

public class Level3 extends Level2{
    @Override
    void isApplicable(){
        super.isApplicable();
    }

    public static void main(String[] args){
        Level3 level3 = new Level3();
        level3.isApplicable();
    }
}
