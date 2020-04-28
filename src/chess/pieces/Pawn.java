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
    public synchronized Collection<Spot> getPossibleMoves() {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        Spot ahead = new Spot(getX(),getY()+1); //Spot przeciwko
        if (ahead.getPiece() == null) possibleMoves.add(ahead); //Sprawdzamy dostępność i dodajemy do listy możliwych

        Spot aheadLeft = new Spot(getX()-1,getY()+1); //Spot po lew
        if (aheadLeft.getPiece().getColor() != this .getColor()) possibleMoves.add(aheadLeft);

        Spot aheadRight = new Spot(getX()+1,getY()+1); //Spot po prawo
        if (aheadRight.getPiece().getColor() != this .getColor()) possibleMoves.add(aheadRight);

        return possibleMoves;
    }
}
