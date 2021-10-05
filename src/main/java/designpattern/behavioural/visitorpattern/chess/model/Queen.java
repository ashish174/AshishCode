package designpattern.behavioural.visitorpattern.chess.model;

import designpattern.behavioural.visitorpattern.chess.PieceMovingVisitor;

public class Queen implements Piece{

    @Override
    public void accept(PieceMovingVisitor visitor) {

    }

    @Override
    public Coordinates getCoordinates() {
        return null;
    }

    @Override
    public void setCoordinates(Coordinates coord) {

    }
}
