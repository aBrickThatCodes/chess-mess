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

    @Override
    public synchronized Collection<Spot> getPossibleMoves(Spot[][] board) {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        try{
            Spot upRight1 = board[getX()+1][getY()+3];
            if(upRight1.getPiece().getColor() != this.getColor()) possibleMoves.add(upRight1); //Skok wyższy w prawo góra
        } catch (Exception e) {
        }

        try{
            Spot upRight2 = board[getX()+3][getY()+1];
            if(upRight2.getPiece().getColor() != this.getColor()) possibleMoves.add(upRight2); //skok niższy w prawo góra
        } catch (Exception e) {
        }

        try{

            Spot upLeft1 = board[getX()-1][getY()+3];
            if(upLeft1.getPiece().getColor() != this.getColor()) possibleMoves.add(upLeft1); //Skok wyższy w lewo góra
        } catch (Exception e) {
        }

        try{
            Spot upLeft2 = board[getX()-3][getY()+1];
            if(upLeft2.getPiece().getColor() != this.getColor()) possibleMoves.add(upLeft2); //skok niższy w lewogóra
        } catch (Exception e) {
        }

        try{
            Spot downRight1 = board[getX()+3][getY()-1];
            if(downRight1.getPiece().getColor() != this.getColor()) possibleMoves.add(downRight1); //Skok wyższy w prawo dół
        } catch (Exception e) {
        }

        try{
            Spot downRight2 = board[getX()+1][getY()-3];
            if(downRight2.getPiece().getColor() != this.getColor()) possibleMoves.add(downRight2); //skok niższy w prawo dół
        } catch (Exception e) {
        }

        try{
            Spot downLeft1 = board[getX()-3][getY()-1];
            if(downLeft1.getPiece().getColor() != this.getColor()) possibleMoves.add(downLeft1); //Skok wyższy w prawo dół
        } catch (Exception e) {
        }

        try{
            Spot downLeft2 =board[getX()-1][getY()-3];
            if(downLeft2.getPiece().getColor() != this.getColor()) possibleMoves.add(downLeft2); //skok niższy w prawo dół
        } catch (Exception e) {
        }

        return possibleMoves;
    }
}
