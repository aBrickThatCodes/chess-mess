package chess.pieces;

import java.util.Collection;

import chess.game.Spot;

public class Knight extends Piece {

    public Knight(boolean available) {
        this.setAvailable(available);
    }

    @Override
    public Collection<Spot> getPossibleMoves() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean validateMove(Spot destination) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void move(Spot destination) {
        // TODO Auto-generated method stub

    }
    
}
