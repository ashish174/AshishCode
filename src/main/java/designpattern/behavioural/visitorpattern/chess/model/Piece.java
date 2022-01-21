package designpattern.behavioural.visitorpattern.chess.model;

import designpattern.behavioural.visitorpattern.chess.PieceMovingVisitor;

public interface Piece {
    void accept(PieceMovingVisitor visitor);
    Coordinates getCoordinates();
    void setCoordinates(Coordinates coord);

    /*
    Every chess piece should have these actions. We can extract these actions to visitor class
    boolean checkMoveValidity(Coordinates coord);
    void performMove(Coordinates coord);
    Piece computeIfKingCheck();
    */
}
