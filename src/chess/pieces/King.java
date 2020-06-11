package chess.pieces;

import chess.Config;
import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.awt.image.BufferedImage;

public class King extends Piece {

    boolean isCheck = false;

    public BufferedImage getPieceIcon(){
        return Config.Instance().pieceImages[5];
    }

    public synchronized Collection<Spot> getPossibleMoves(Spot[][] board) {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        int i=1;
        int j=1;
        try{
            Spot upRight = board[getX()+i][getY()+j];
            if(upRight.getPiece() == null) possibleMoves.add(upRight);
            else if(upRight.getPiece().getColor() != this.getColor()) possibleMoves.add(upRight);
        } catch (Exception e) {
        }

        try{
            Spot upLeft = board[getX()+i][getY()-j];
            if(upLeft.getPiece() == null) possibleMoves.add(upLeft);
            else if(upLeft.getPiece().getColor() != this.getColor()) possibleMoves.add(upLeft);
        } catch (Exception e) {
        }

        try{
            Spot downRight = board[getX()-i][getY()+j];
            if(downRight.getPiece() == null) possibleMoves.add(downRight);
            else if(downRight.getPiece().getColor() != this.getColor()) possibleMoves.add(downRight);
        } catch (Exception e) {
        }

        try{
            Spot downLeft = board[getX()-i][getY()-j];
            if(downLeft.getPiece() == null) possibleMoves.add(downLeft);
            else if(downLeft.getPiece().getColor() != this.getColor()) possibleMoves.add(downLeft);
        } catch (Exception e) {
        }

        try{
            Spot ahead = board[getX()+i][getY()];
            if(ahead.getPiece() == null) possibleMoves.add(ahead);
            else if(ahead.getPiece().getColor() != this.getColor()) possibleMoves.add(ahead);
        } catch (Exception e) {
        }

        try{
            Spot left = board[getX()][getY()-i];
            if(left.getPiece() == null) possibleMoves.add(left);
            else if(left.getPiece().getColor() != this.getColor()) possibleMoves.add(left);
        } catch (Exception e) {
        }

        try{
            Spot right = board[getX()][getY()+i];
            if(right.getPiece() == null) possibleMoves.add(right);
            else if(right.getPiece().getColor() != this.getColor()) possibleMoves.add(right);
        } catch (Exception e) {
        }

        try{
            Spot behind  = board[getX()-i][getY()];
            if(behind.getPiece() == null) possibleMoves.add(behind);
            else if(behind.getPiece().getColor() != this.getColor()) possibleMoves.add(behind);
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
