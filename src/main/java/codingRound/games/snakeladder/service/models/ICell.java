package codingRound.games.snakeladder.service.models;

public interface ICell {
    int getOwnPosition();
    boolean isOccupied();
    int moveForward(int steps);
}
