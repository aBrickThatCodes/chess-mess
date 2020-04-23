package chess.pieces;

import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece {

    boolean isCheck = false;

    /*public King(boolean available,int x, int y){
        this.setAvailable(available);
        this.pieceLocationSpot.setLocation(x,y);
    }*/

    public synchronized Collection<Spot> getPossibleMoves() {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        int i=1;
        int j=1;
        Spot upRight = new Spot(getX()+i,getY()+j);
        if(upRight.getPiece().getColor() != this.getColor()) possibleMoves.add(upRight);

        i=1;
        j=1;
        Spot upLeft = new Spot(getX()-i,getY()+j);
        if(upLeft.getPiece().getColor() != this.getColor()) possibleMoves.add(upLeft);

        i=1;
        j=1;
        Spot downRight = new Spot(getX()+i,getY()-j);
        if(downRight.getPiece().getColor() != this.getColor()) possibleMoves.add(downRight);

        i=1;
        j=1;
        Spot downLeft = new Spot(getX()-i,getY()-j);
        if(downLeft.getPiece().getColor() != this.getColor()) possibleMoves.add(downLeft);

        i=1;
        Spot ahead = new Spot(getX(),getY()+i); if(ahead.getPiece().getColor() != this.getColor()) possibleMoves.add(ahead);

        i=1;
        Spot left = new Spot(getX()-i,getY()); if(left.getPiece().getColor() != this.getColor()) possibleMoves.add(left);

        i=1;
        Spot right = new Spot(getX()+i,getY()); if(right.getPiece().getColor() != this.getColor()) possibleMoves.add(right);

        i=1;
        Spot behind  = new Spot(getX(),getY()-i); if(behind.getPiece().getColor() != this.getColor()) possibleMoves.add(behind);

        return possibleMoves;
    }

    public synchronized void isChecked(boolean check){
        this.isCheck = check;
    }

    public synchronized boolean getIsChecked(){
        return isCheck;
    }

}
