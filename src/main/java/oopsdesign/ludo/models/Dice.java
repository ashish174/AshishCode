package oopsdesign.ludo.models;

import java.util.Random;

public class Dice {
    private int max;

    public Dice(int max) {
        this.max = max;
    }

    public int throwDice(){
        return (int) (Math.random()*max);
    }
}
