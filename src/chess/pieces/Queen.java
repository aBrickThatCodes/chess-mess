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

    @Override
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

        Spot ahead;
        i = 1;

        try{
            while((ahead = board[getX()+i][getY()]).getPiece() == null){
                possibleMoves.add(ahead);
                i++;
                if(i == 12){
                    break;
                }

            }
            if(ahead != null && ahead.getPiece()!=null && ahead.getPiece().getColor() != getColor()){
                possibleMoves.add(ahead);
                            }
        } catch (Exception e) {
        }

        Spot behind;
        i = 1;
        try{
            while((behind = board[getX()-i][getY()]).getPiece() == null){
                possibleMoves.add(behind);
                i++;
                if(i == 12){
                    break;
                }

            }
            if(behind != null && behind.getPiece()!=null && behind.getPiece().getColor() != getColor()){
                possibleMoves.add(behind);
                            }
        } catch (Exception e) {
        }

        Spot left;
        i = 1;
        try{
            while((left = board[getX()][getY()-i]).getPiece() == null){
                possibleMoves.add(left);
                i++;

                if(i == 12){
                    break;
                }
                           }
            if(left != null && left.getPiece()!=null && left.getPiece().getColor() != getColor()){
                possibleMoves.add(left);
                            }
        } catch (Exception e) {

        }

        Spot right;
        i = 1;
        try{
            while((right = board[getX()][getY()+i]).getPiece() == null){
                possibleMoves.add(right);
                i++;

                if(i == 12){
                    break;
                }
                            }
            if(right != null && right.getPiece()!=null && right.getPiece().getColor() != getColor()){
                possibleMoves.add(right);

            }
        } catch (Exception e) {

        }

        return possibleMoves;
    }
}
