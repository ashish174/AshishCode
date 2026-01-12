package codingRound.games.snakeladder.service.models;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        int gameSize = 50;
        Player player1 = new Player("Ashish", null);
        Player player2 = new Player("Anshu", null);
        Player player3 = new Player("Jhumi", null);

        List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);

        Game game = new Game(gameSize, playerList);
        game.startGame();
    }
}
