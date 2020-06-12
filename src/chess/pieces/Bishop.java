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

    public synchronized Collection<Spot> getPossibleMoves(Spot[][] board) {

        List<Spot> possibleMoves = new ArrayList<Spot>();
        int i=1;
        int j=1;

        Spot upRight;
        try{
            while((upRight = board[getX()+i][getY()+j]).getPiece() == null){
                possibleMoves.add(upRight);
                i++;
                j++;
                if(i == 12){
                    break;
                }
            }
            if(upRight != null && upRight.getPiece()!=null && upRight.getPiece().getColor() != getColor()){
                possibleMoves.add(upRight);
            }
        } catch (Exception e) {
        }

        i=1;
        j=1;
        Spot upLeft;
        try{
            while((upLeft = board[getX()+i][getY()-j]).getPiece() == null){
                possibleMoves.add(upLeft);
                i++;
                j++;
                if(i == 12){
                    break;
                }
            }
            if(upLeft != null && upLeft.getPiece()!=null && upLeft.getPiece().getColor() != getColor()){
                possibleMoves.add(upLeft);
            }
        } catch (Exception e) {
        }

        i=1;
        j=1;
        Spot downRight;
        try{
            while((downRight = board[getX()-i][getY()+j]).getPiece() == null){
                possibleMoves.add(downRight);
                i++;
                j++;
                if(i == 12){
                    break;
                }
            }
            if(downRight != null && downRight.getPiece()!=null && downRight.getPiece().getColor() != getColor()){
                possibleMoves.add(downRight);
            }
        } catch (Exception e) {
        }

        i=1;
        j=1;
        Spot downLeft;
        try{
            while((downLeft = board[getX()-i][getY()-j]).getPiece() == null){
                possibleMoves.add(downLeft);
                i++;
                j++;
                if(i == 12){
                    break;
                }
            }
            if(downLeft != null && downLeft.getPiece()!=null && downLeft.getPiece().getColor() != getColor()){
                possibleMoves.add(downLeft);
            }
        } catch (Exception e) {
        }

        return possibleMoves;
    }
}
