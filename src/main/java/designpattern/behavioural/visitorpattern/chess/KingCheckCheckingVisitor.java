package designpattern.behavioural.visitorpattern.chess;

import designpattern.behavioural.visitorpattern.chess.model.King;
import designpattern.behavioural.visitorpattern.chess.model.Knight;
import designpattern.behavioural.visitorpattern.chess.model.Pawn;
import designpattern.behavioural.visitorpattern.chess.model.Queen;

public class KingCheckCheckingVisitor implements PieceMovingVisitor {
    @Override
    public void visit(Pawn pawn) {

    }

    @Override
    public void visit(Knight knight) {

    }

    @Override
    public void visit(King king) {

    }

    @Override
    public void visit(Queen queen) {

    }
}
