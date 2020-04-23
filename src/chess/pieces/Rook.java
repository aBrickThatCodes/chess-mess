package chess.pieces;

import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece {

    /*public Rook(boolean available,int x, int y){
        this.setAvailable(available);
        this.pieceLocationSpot.setLocation(x,y);
    }*/

    @Override
    public synchronized Collection<Spot> getPossibleMoves() {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        int i=1;
        Spot ahead = new Spot(getX(),getY()+i);
        while(ahead.getPiece() == null){
            ahead = new Spot(getX(),getY()+i); //Spot do pierwszego dostępnego przed pionkiem
            possibleMoves.add(ahead);
            i++;
        }

        i=1;
        Spot left = new Spot(getX()-i, getY());
        while(left.getPiece() == null){
            left = new Spot(getX()-i, getY()); //Spot do pierwszego dostępnego na lewo
            possibleMoves.add(left);
            i++;
        }

        i=1;
        Spot right = new Spot(getX()+i, getY());
        while(right.getPiece() == null){
            right = new Spot(getX()+i, getY()); //Spot do pierwszego dostępnego na prawo
            possibleMoves.add(right);
            i++;
        }

        i=1;
        Spot behind  = new Spot(getX(),getY()-i);
        while(behind.getPiece() == null){
            behind = new Spot(getX(),getY()-i); //Spot do pierwszego dostępnego za
            possibleMoves.add(behind);
            i++;
        }

        if(ahead.getPiece().getColor() != this.getColor()) possibleMoves.add(ahead);
        if(behind.getPiece().getColor() != this.getColor()) possibleMoves.add(behind);
        if(left.getPiece().getColor() != this.getColor()) possibleMoves.add(left);
        if(right.getPiece().getColor() != this.getColor()) possibleMoves.add(right);

        return possibleMoves;
    }
}
