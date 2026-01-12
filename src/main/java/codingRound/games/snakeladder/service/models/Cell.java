package codingRound.games.snakeladder.service.models;

public class Cell implements ICell{
    private int position;
    private boolean occupied;

    @Override
    public int getOwnPosition() {
        return position;
    }

    @Override
    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public int moveForward(int steps) {
        return 0;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
