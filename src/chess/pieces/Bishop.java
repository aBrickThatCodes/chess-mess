package chess.pieces;

import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bishop extends Piece {

    private String pieceIcon = "♝";

    public String getPieceIcon(){
        return this.pieceIcon;
    }

    public synchronized Collection<Spot> getPossibleMoves() {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        int i=1;
        int j=1;
        Spot upRight = new Spot(getX()+i,getY()+j);
        while(upRight.getPiece() == null){
            upRight = new Spot(getX()+i,getY()+i); //Spot do pierwszego dostępnego po przekątnej w prawo
            possibleMoves.add(upRight);
            i++;
            j++;
        }

        i=1;
        j=1;
        Spot upLeft = new Spot(getX()-i,getY()+j);
        while(upLeft.getPiece() == null){
            upLeft = new Spot(getX()-i,getY()+j); //Spot do pierwszego dostępnego po przekątnej w prawo
            possibleMoves.add(upLeft);
            i++;
            j++;
        }

        i=1;
        j=1;
        Spot downRight = new Spot(getX()+i,getY()-j);
        while(downRight.getPiece() == null){
            downRight = new Spot(getX()+i,getY()-j); //Spot do pierwszego dostępnego po przekątnej w prawo
            possibleMoves.add(downRight);
            i++;
            j++;
        }

        i=1;
        j=1;
        Spot downLeft = new Spot(getX()-i,getY()-j);
        while(downLeft.getPiece() == null){
            downLeft = new Spot(getX()-i,getY()+i); //Spot do pierwszego dostępnego po przekątnej w prawo
            possibleMoves.add(downRight);
            i++;
            j++;
        }

        if(upRight.getPiece().getColor() != this.getColor()) possibleMoves.add(upRight);
        if(upLeft.getPiece().getColor() != this.getColor()) possibleMoves.add(upLeft);
        if(downRight.getPiece().getColor() != this.getColor()) possibleMoves.add(downRight);
        if(downLeft.getPiece().getColor() != this.getColor()) possibleMoves.add(downLeft);

        return possibleMoves;
    }
}
