package chess.pieces;

import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece {

    private String pieceIcon = "♜";

    public String getPieceIcon(){
        return this.pieceIcon;
    }

    @Override
    public synchronized Collection<Spot> getPossibleMoves() {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        int i=1;
        Spot ahead = new Spot(getX()+i,getY());
        while(ahead.getPiece() == null){

            if(i>10){
                break;
            }
            ahead = new Spot(getX(),getY()+i); //Spot do pierwszego dostępnego przed pionkiem
            possibleMoves.add(ahead);
            i++;
        }

        i=1;
        Spot left = new Spot(getX(), getY()-i);
        while(left.getPiece() == null){
            if(i>10){
                break;
            }
            left = new Spot(getX()-i, getY()); //Spot do pierwszego dostępnego na lewo
            possibleMoves.add(left);
            i++;
        }

        i=1;
        Spot right = new Spot(getX(), getY()+i);
        while(right.getPiece() == null){

            if(i>10){
                break;
            }

            right = new Spot(getX()+i, getY()); //Spot do pierwszego dostępnego na prawo
            possibleMoves.add(right);
            i++;
        }

        i=1;
        Spot behind  = new Spot(getX()-i,getY());
        while(behind.getPiece() == null){
            if(i>10){
                break;
            }
            behind = new Spot(getX(),getY()-i); //Spot do pierwszego dostępnego za
            possibleMoves.add(behind);
            i++;
        }

        if(ahead.getPiece() !=null && ahead.getPiece().getColor() != this.getColor()) possibleMoves.add(ahead);
        if(behind.getPiece() !=null && behind.getPiece().getColor() != this.getColor()) possibleMoves.add(behind);
        if(left.getPiece() !=null && left.getPiece().getColor() != this.getColor()) possibleMoves.add(left);
        if(right.getPiece() !=null && right.getPiece().getColor() != this.getColor()) possibleMoves.add(right);

        return possibleMoves;
    }
}
