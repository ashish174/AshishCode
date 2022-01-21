package designpattern.behavioural.visitorpattern.chess;

import designpattern.behavioural.visitorpattern.chess.model.*;

public interface PieceMovingVisitor {
    void visit(Pawn pawn);
    void visit(Knight knight);
    void visit(King king);
    void visit(Queen queen);
    void visit(Rook rook);
    void visit(Bishop bishop);
}
