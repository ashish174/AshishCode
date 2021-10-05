package designpattern.behavioural.visitorpattern.chess.model;

import designpattern.behavioural.visitorpattern.chess.PieceMovingVisitor;

public class Bishop implements Piece{

    private Coordinates coord;

    public Bishop(Coordinates coord) {
        this.coord = coord;
    }

    @Override
    public void accept(PieceMovingVisitor pieceVisitor) {
        pieceVisitor.visit(this);
    }

    @Override
    public Coordinates getCoordinates() {
        return coord;
    }

    @Override
    public void setCoordinates(Coordinates coordinates) {
        this.coord = coordinates;
    }
}
