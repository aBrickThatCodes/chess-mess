package chess.pieces;

import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private String pieceIcon = "♟";

    public String getPieceIcon(){
        return this.pieceIcon;
    }

    //Zbiór możliwych ruchów
    public synchronized Collection<Spot> getPossibleMoves(Spot[][] board) {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        try{
            Spot ahead = board[getX()+1][getY()];
            if (ahead.getPiece() == null) possibleMoves.add(ahead); //Sprawdzamy dostępność i dodajemy do listy możliwych
        } catch (Exception e) {
        }

        try{
            Spot aheadLeft = board[getX()-1][getY()+1];
            if (aheadLeft.getPiece().getColor() != this .getColor()) possibleMoves.add(aheadLeft);
        } catch (Exception e) {
        }

        try{
            Spot aheadRight = board[getX()+1][getY()+1];
            if (aheadRight.getPiece().getColor() != this .getColor()) possibleMoves.add(aheadRight);
        } catch (Exception e) {
        }

        return possibleMoves;
    }
}
