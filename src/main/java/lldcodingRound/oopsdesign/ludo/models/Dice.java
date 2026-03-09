package lldcodingRound.oopsdesign.ludo.models;

public class Dice {
    private int max;

    public Dice(int max) {
        this.max = max;
    }

    public int throwDice(){
        return (int) (Math.random()*max);
    }
}
