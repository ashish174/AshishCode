package games.snakeladder.service.models;

public class Cell implements ICell{

    @Override
    public int getOwnPosition() {
        return 0;
    }

    @Override
    public boolean isOccupied() {
        return false;
    }

    @Override
    public int moveForward(int steps) {
        return 0;
    }
}
