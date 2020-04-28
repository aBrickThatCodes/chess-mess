package chess.pieces;

import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece {

    private String pieceIcon = "♞";

    public String getPieceIcon(){
        return this.pieceIcon;
    }

    public synchronized Collection<Spot> getPossibleMoves() {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        Spot upRight1 = new Spot(getX()+1,getY()+3);
        if(upRight1.getPiece().getColor() != this.getColor()) possibleMoves.add(upRight1); //Skok wyższy w prawo góra

        Spot upRight2 = new Spot(getX()+3,getY()+1);
        if(upRight2.getPiece().getColor() != this.getColor()) possibleMoves.add(upRight2); //skok niższy w prawo góra

        Spot upLeft1 = new Spot(getX()-1,getY()+3);
        if(upLeft1.getPiece().getColor() != this.getColor()) possibleMoves.add(upLeft1); //Skok wyższy w lewo góra

        Spot upLeft2 = new Spot(getX()-3,getY()+1);
        if(upLeft2.getPiece().getColor() != this.getColor()) possibleMoves.add(upLeft2); //skok niższy w lewogóra

        Spot downRight1 = new Spot(getX()+3,getY()-1);
        if(downRight1.getPiece().getColor() != this.getColor()) possibleMoves.add(downRight1); //Skok wyższy w prawo dół

        Spot downRight2 = new Spot(getX()+1,getY()-3);
        if(downRight2.getPiece().getColor() != this.getColor()) possibleMoves.add(downRight2); //skok niższy w prawo dół

        Spot downLeft1 = new Spot(getX()-3,getY()-1);
        if(downLeft1.getPiece().getColor() != this.getColor()) possibleMoves.add(downLeft1); //Skok wyższy w prawo dół

        Spot downLeft2 = new Spot(getX()-1,getY()-3);
        if(downLeft2.getPiece().getColor() != this.getColor()) possibleMoves.add(downLeft2); //skok niższy w prawo dół

        return possibleMoves;
    }
}
