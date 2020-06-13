package chess.pieces;

import chess.Config;
import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.awt.image.BufferedImage;

public class Rook extends Piece {

    public BufferedImage getPieceIcon(){
        return Config.Instance().pieceImages[1];
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

            }
            if(right != null && right.getPiece()!=null && right.getPiece().getColor() != getColor()){
                possibleMoves.add(right);
            }
        } catch (Exception e) {
        }

        return possibleMoves;
    }

    public synchronized Collection<Spot> getPossibleAttacks(Spot[][] board) {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        Spot ahead;
        int i = 1;
        try {
            while ((ahead = board[getX() + i][getY()]).getPiece() == null) {
                possibleMoves.add(ahead);
                i++;

            }
            if (ahead != null && ahead.getPiece() != null && ahead.getPiece().getColor() != getColor()) {
                possibleMoves.add(ahead);
            }
            if (ahead != null && ahead.getPiece() instanceof King && (ahead = board[getX() + i + 1][getY()]).getPiece() == null && ahead != null) {
                possibleMoves.add(ahead);
            }
        } catch (Exception ignored) {
        }

        Spot behind;
        i = 1;
        try {
            while ((behind = board[getX() - i][getY()]).getPiece() == null) {
                possibleMoves.add(behind);
                i++;

            }
            if (behind != null && behind.getPiece() != null && behind.getPiece().getColor() != getColor()) {
                possibleMoves.add(behind);
            }
            if (behind != null && behind.getPiece() instanceof King && (behind = board[getX() - i - 1][getY()]).getPiece() == null && behind != null) {
                possibleMoves.add(behind);
            }
        } catch (Exception ignored) {
        }

        Spot left;
        i = 1;
        try {
            while ((left = board[getX()][getY() - i]).getPiece() == null) {
                possibleMoves.add(left);
                i++;

            }
            if (left != null && left.getPiece() != null && left.getPiece().getColor() != getColor()) {
                possibleMoves.add(left);
            }
            if (left != null && left.getPiece() instanceof King && (left = board[getX()][getY() - i - 1]).getPiece() == null && left != null) {
                possibleMoves.add(left);
            }
        } catch (Exception ignored) {

        }

        Spot right;
        i = 1;
        try {
            while ((right = board[getX()][getY() + i]).getPiece() == null) {
                possibleMoves.add(right);
                i++;

            }
            if (right != null && right.getPiece() != null && right.getPiece().getColor() != getColor()) {
                possibleMoves.add(right);

            }
            if (right != null && right.getPiece() instanceof King && (right = board[getX()][getY() + i + 1]).getPiece() == null && right != null) {
                possibleMoves.add(right);
            }
        } catch (Exception ignored) {

        }
        return possibleMoves;
    }
}
