package chess.pieces;

import chess.Config;
import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.awt.image.BufferedImage;

public class Knight extends Piece {

    public BufferedImage getPieceIcon(){
        return Config.Instance().pieceImages[2];
    }

    @Override
    public synchronized Collection<Spot> getPossibleMoves(Spot[][] board) {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        try{
            Spot upRight1 = board[getX()+1][getY()+2];
            if(upRight1.getPiece() == null) possibleMoves.add(upRight1);
            else if(upRight1.getPiece().getColor() != this.getColor()) possibleMoves.add(upRight1); //Skok wyższy w prawo góra
        } catch (Exception e) {
        }

        try{
            Spot upRight2 = board[getX()+2][getY()+1];
            if(upRight2.getPiece() == null) possibleMoves.add(upRight2);
            else if(upRight2.getPiece().getColor() != this.getColor()) possibleMoves.add(upRight2); //Skok niższy w prawo góra
        } catch (Exception e) {
        }

        try{

            Spot upLeft1 = board[getX()-1][getY()+2];
            if(upLeft1.getPiece() == null) possibleMoves.add(upLeft1);
            else if(upLeft1.getPiece().getColor() != this.getColor()) possibleMoves.add(upLeft1); //Skok wyższy w lewo góra
        } catch (Exception e) {
        }

        try{
            Spot upLeft2 = board[getX()-2][getY()+1];
            if(upLeft2.getPiece() == null) possibleMoves.add(upLeft2);
            else if(upLeft2.getPiece().getColor() != this.getColor()) possibleMoves.add(upLeft2); //Skok niższy w lewo góra
        } catch (Exception e) {
        }

        try{
            Spot downRight1 = board[getX()+2][getY()-1];
            if(downRight1.getPiece() == null) possibleMoves.add(downRight1);
            else if(downRight1.getPiece().getColor() != this.getColor()) possibleMoves.add(downRight1);
        } catch (Exception e) {
        }

        try{
            Spot downRight2 = board[getX()+1][getY()-2];
            if(downRight2.getPiece() == null) possibleMoves.add(downRight2);
            else if(downRight2.getPiece().getColor() != this.getColor()) possibleMoves.add(downRight2);
        } catch (Exception e) {
        }

        try{
            Spot downLeft1 = board[getX()-2][getY()-1];
            if(downLeft1.getPiece() == null) possibleMoves.add(downLeft1);
            else if(downLeft1.getPiece().getColor() != this.getColor()) possibleMoves.add(downLeft1);
        } catch (Exception e) {
        }

        try{
            Spot downLeft2 =board[getX()-1][getY()-2];
            if(downLeft2.getPiece() == null) possibleMoves.add(downLeft2);
            else if(downLeft2.getPiece().getColor() != this.getColor()) possibleMoves.add(downLeft2);
        } catch (Exception e) {
        }

        return possibleMoves;
    }
}
