package chess.pieces;

import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece {

    boolean isCheck = false;

    private String pieceIcon = "♚";

    public String getPieceIcon(){
        return this.pieceIcon;
    }

    public synchronized Collection<Spot> getPossibleMoves(Spot[][] board) {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        int i=1;
        int j=1;
        try{
            Spot upRight = board[getX()+i][getY()+j];
            if(upRight.getPiece().getColor() != this.getColor()) possibleMoves.add(upRight);
        } catch (Exception e) {
        }


        i=1;
        j=1;
        try{
            Spot upLeft = board[getX()+i][getY()-j];
            if(upLeft.getPiece().getColor() != this.getColor()) possibleMoves.add(upLeft);
        } catch (Exception e) {
        }


        i=1;
        j=1;
        try{
            Spot downRight = board[getX()-i][getY()+j];
            if(downRight.getPiece().getColor() != this.getColor()) possibleMoves.add(downRight);
        } catch (Exception e) {
        }

        i=1;
        j=1;
        try{
            Spot downLeft = board[getX()-i][getY()-j];
            if(downLeft.getPiece().getColor() != this.getColor()) possibleMoves.add(downLeft);
        } catch (Exception e) {
        }

        i=1;
        try{
            Spot ahead = board[getX()+i][getY()]; if(ahead.getPiece().getColor() != this.getColor()) possibleMoves.add(ahead);
        } catch (Exception e) {
        }

        i=1;
        try{
            Spot left = board[getX()][getY()-i]; if(left.getPiece().getColor() != this.getColor()) possibleMoves.add(left);
        } catch (Exception e) {
        }

        i=1;
        try{
            Spot right = board[getX()][getY()+i]; if(right.getPiece().getColor() != this.getColor()) possibleMoves.add(right);
        } catch (Exception e) {
        }

        i=1;
        try{
            Spot behind  = board[getX()-i][getY()]; if(behind.getPiece().getColor() != this.getColor()) possibleMoves.add(behind);
        } catch (Exception e) {
        }

        return possibleMoves;
    }

    public synchronized void isChecked(boolean check){
        this.isCheck = check;
    }

    public synchronized boolean getIsChecked(){
        return isCheck;
    }

}
