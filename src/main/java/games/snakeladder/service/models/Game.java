package games.snakeladder.service.models;

import java.util.List;

public class Game {
    int size;
    List<Player> players;
    Dice dice;
    boolean isOver;
    Player winner;

    public Game(int size, List<Player> players) {
        this.size = size;
        this.players = players;
        this.dice = new Dice(6);
    }

    Player getWinner(){
        return isOver?winner:null;
    }
}
