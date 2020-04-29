package chess.pieces;

import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Queen extends Piece {

    private String pieceIcon = "♛";

    public String getPieceIcon(){
        return this.pieceIcon;
    }

    public synchronized Collection<Spot> getPossibleMoves() {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        int i=1;
        int j=1;
        Spot upRight = new Spot(getX()+i,getY()+j);
        while(upRight.getPiece() == null){
            if(i>config.boardWidth){
                break;
            }
            upRight = new Spot(getX()+i,getY()+j); //Spot do pierwszego dostępnego po przekątnej w prawo
            possibleMoves.add(upRight);
            i++;
            j++;
        }

        i=1;
        j=1;
        Spot upLeft = new Spot(getX()-i,getY());
        while(upLeft.getPiece() == null){
            if(i>config.boardWidth){
                break;
            }
            upLeft = new Spot(getX()-i,getY()); //Spot do pierwszego dostępnego po przekątnej w prawo
            possibleMoves.add(upLeft);
            i++;
            j++;
        }

        i=1;
        j=1;
        Spot downRight = new Spot(getX()+i,getY());
        while(downRight.getPiece() == null){
            if(i>config.boardWidth){
                break;
            }
            downRight = new Spot(getX()+i,getY()); //Spot do pierwszego dostępnego po przekątnej w prawo
            possibleMoves.add(downRight);
            i++;
            j++;
        }

        i=1;
        j=1;
        Spot downLeft = new Spot(getX()-i,getY());
        while(downLeft.getPiece() == null){
            if(i>config.boardWidth){
                break;
            }
            downRight = new Spot(getX()-i,getY()); //Spot do pierwszego dostępnego po przekątnej w prawo
            possibleMoves.add(downLeft);
            i++;
            j++;
        }

        i=1;
        Spot ahead = new Spot(getX(),getY()+i);
        while(ahead.getPiece() == null){
            if(i>config.boardHeight){
                break;
            }
            ahead = new Spot(getX(),getY()+i); //Spot do pierwszego dostępnego przed pionkiem
            possibleMoves.add(ahead);
            i++;
        }

        i=1;
        Spot left = new Spot(getX()-i,getY());
        while(left.getPiece() == null){
            if(i>config.boardWidth){
                break;
            }
            left = new Spot(getX()-i,getY()); //Spot do pierwszego dostępnego na lewo
            possibleMoves.add(left);
            i++;
        }

        i=1;
        Spot right = new Spot(getX()+i,getY());
        while(right.getPiece() == null){
            if(i>config.boardWidth){
                break;
            }
            right = new Spot(getX()+i,getY()); //Spot do pierwszego dostępnego na prawo
            possibleMoves.add(right);
            i++;
        }

        i=1;
        Spot behind  = new Spot(getX(),getY()-i);
        while(behind.getPiece() == null){
            if(i>config.boardHeight){
                break;
            }
            behind = new Spot(getX(),getY()-i); //Spot do pierwszego dostępnego za
            possibleMoves.add(behind);
            i++;
        }

        if(upRight.getPiece() !=null && upRight.getPiece().getColor() != this.getColor()) possibleMoves.add(upRight);
        if(upLeft.getPiece() !=null && upLeft.getPiece().getColor() != this.getColor()) possibleMoves.add(upLeft);
        if(downRight.getPiece() !=null &&downRight.getPiece().getColor() != this.getColor()) possibleMoves.add(downRight);
        if(downLeft.getPiece() !=null && downLeft.getPiece().getColor() != this.getColor()) possibleMoves.add(downLeft);

        if(ahead.getPiece() !=null && ahead.getPiece().getColor() != this.getColor()) possibleMoves.add(ahead);
        if(behind.getPiece() !=null && behind.getPiece().getColor() != this.getColor()) possibleMoves.add(behind);
        if(left.getPiece() !=null && left.getPiece().getColor() != this.getColor()) possibleMoves.add(left);
        if(right.getPiece() !=null && right.getPiece().getColor() != this.getColor()) possibleMoves.add(right);

        return possibleMoves;
    }
}
