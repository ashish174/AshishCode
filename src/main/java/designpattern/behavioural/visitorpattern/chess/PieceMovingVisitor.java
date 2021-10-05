package designpattern.behavioural.visitorpattern.chess;

import designpattern.behavioural.visitorpattern.chess.model.King;
import designpattern.behavioural.visitorpattern.chess.model.Knight;
import designpattern.behavioural.visitorpattern.chess.model.Pawn;
import designpattern.behavioural.visitorpattern.chess.model.Queen;

public interface PieceMovingVisitor {
    void visit(Pawn pawn);
    void visit(Knight knight);
    void visit(King king);
    void visit(Queen queen);
}
