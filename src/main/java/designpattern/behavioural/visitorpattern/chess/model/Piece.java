package designpattern.behavioural.visitorpattern.chess.model;

import designpattern.behavioural.visitorpattern.chess.PieceMovingVisitor;

public interface Piece {
    void accept(PieceMovingVisitor visitor);
    Coordinates getCoordinates();
    void setCoordinates(Coordinates coord);
}
