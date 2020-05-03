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
    public synchronized Collection<Spot> getPossibleMoves(Spot[][] board) {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        Spot ahead;
        int i = 1;

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
               // System.out.println("Dodałem pionek");
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
