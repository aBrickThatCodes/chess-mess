package chess.pieces;

import chess.Config;
import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class King extends Piece {

    private boolean isCheck = false;
    private boolean isMate = false; //zmienna do przekazania błędu który jest matem

    public BufferedImage getPieceIcon(){
        return Config.Instance().pieceImages[5];
    }

    public synchronized Collection<Spot> getPossibleMoves(Spot[][] board) {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        int i=1;
        int j=1;
        try{
            Spot upRight = board[getX()+i][getY()+j];
            if(upRight.getPiece() == null && !upRight.isBlocked) possibleMoves.add(upRight);
            else if(upRight.getPiece().getColor() != this.getColor()) possibleMoves.add(upRight);
        } catch (Exception e) {
        }

        try{
            Spot upLeft = board[getX()+i][getY()-j];
            if(upLeft.getPiece() == null && !upLeft.isBlocked) possibleMoves.add(upLeft);
            else if(upLeft.getPiece().getColor() != this.getColor()) possibleMoves.add(upLeft);
        } catch (Exception e) {
        }

        try{
            Spot downRight = board[getX()-i][getY()+j];
            if(downRight.getPiece() == null && !downRight.isBlocked) possibleMoves.add(downRight);
            else if(downRight.getPiece().getColor() != this.getColor()) possibleMoves.add(downRight);
        } catch (Exception e) {
        }

        try{
            Spot downLeft = board[getX()-i][getY()-j];
            if(downLeft.getPiece() == null && !downLeft.isBlocked) possibleMoves.add(downLeft);
            else if(downLeft.getPiece().getColor() != this.getColor()) possibleMoves.add(downLeft);
        } catch (Exception e) {
        }

        try{
            Spot ahead = board[getX()+i][getY()];
            if(ahead.getPiece() == null && !ahead.isBlocked) possibleMoves.add(ahead);
            else if(ahead.getPiece().getColor() != this.getColor()) possibleMoves.add(ahead);
        } catch (Exception e) {
        }

        try{
            Spot left = board[getX()][getY()-i];
            if(left.getPiece() == null && !left.isBlocked) possibleMoves.add(left);
            else if(left.getPiece().getColor() != this.getColor()) possibleMoves.add(left);
        } catch (Exception e) {
        }

        try{
            Spot right = board[getX()][getY()+i];
            if(right.getPiece() == null && !right.isBlocked) possibleMoves.add(right);
            else if(right.getPiece().getColor() != this.getColor()) possibleMoves.add(right);
        } catch (Exception e) {
        }

        try{
            Spot behind  = board[getX()-i][getY()];
            if(behind.getPiece() == null && !behind.isBlocked) possibleMoves.add(behind);
            else if(behind.getPiece().getColor() != this.getColor()) possibleMoves.add(behind);
        } catch (Exception e) {
        }

        return possibleMoves;
    }

    public synchronized Collection<Spot> getPossibleAttacks(Spot[][] board) {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        int i=1;
        int j=1;
        try{
            Spot upRight = board[getX()+i][getY()+j];
            if(upRight.getPiece() == null && !upRight.isBlocked) possibleMoves.add(upRight);
            else if(upRight.getPiece().getColor() != this.getColor()) possibleMoves.add(upRight);
        } catch (Exception e) {
        }

        try{
            Spot upLeft = board[getX()+i][getY()-j];
            if(upLeft.getPiece() == null && !upLeft.isBlocked) possibleMoves.add(upLeft);
            else if(upLeft.getPiece().getColor() != this.getColor()) possibleMoves.add(upLeft);
        } catch (Exception e) {
        }

        try{
            Spot downRight = board[getX()-i][getY()+j];
            if(downRight.getPiece() == null && !downRight.isBlocked) possibleMoves.add(downRight);
            else if(downRight.getPiece().getColor() != this.getColor()) possibleMoves.add(downRight);
        } catch (Exception e) {
        }

        try{
            Spot downLeft = board[getX()-i][getY()-j];
            if(downLeft.getPiece() == null && !downLeft.isBlocked) possibleMoves.add(downLeft);
            else if(downLeft.getPiece().getColor() != this.getColor()) possibleMoves.add(downLeft);
        } catch (Exception e) {
        }

        try{
            Spot ahead = board[getX()+i][getY()];
            if(ahead.getPiece() == null && !ahead.isBlocked) possibleMoves.add(ahead);
            else if(ahead.getPiece().getColor() != this.getColor()) possibleMoves.add(ahead);
        } catch (Exception e) {
        }

        try{
            Spot left = board[getX()][getY()-i];
            if(left.getPiece() == null && !left.isBlocked) possibleMoves.add(left);
            else if(left.getPiece().getColor() != this.getColor()) possibleMoves.add(left);
        } catch (Exception e) {
        }

        try{
            Spot right = board[getX()][getY()+i];
            if(right.getPiece() == null && !right.isBlocked) possibleMoves.add(right);
            else if(right.getPiece().getColor() != this.getColor()) possibleMoves.add(right);
        } catch (Exception e) {
        }

        try{
            Spot behind  = board[getX()-i][getY()];
            if(behind.getPiece() == null && !behind.isBlocked) possibleMoves.add(behind);
            else if(behind.getPiece().getColor() != this.getColor()) possibleMoves.add(behind);
        } catch (Exception e) {
        }

        return possibleMoves;
    }

    public synchronized Collection<Spot> getPossibleMoves(Spot[][] board,Collection<Spot> impossibleMoves){
        Collection<Spot> possibleMoves = this.getPossibleMoves(board);

        for(Spot ips:impossibleMoves){
            possibleMoves.remove(ips);
        }

        return possibleMoves;
    }

    public synchronized void isChecked(boolean check){
        this.isCheck = check;
    }

    public synchronized boolean getIsChecked(){
        return isCheck;
    }

    public synchronized boolean contains(int x, int y,Spot[][] board,Collection<Spot> impossibleMoves){
        boolean contains = false;
        Collection<Spot> possibleMoves = this.getPossibleMoves(board);

        try {
            for (Spot ps : possibleMoves) {
                for (Spot ips : impossibleMoves) {
                    if (ps.getX() == ips.getX()) {
                        if (ps.getY() == ips.getY()) {
                            possibleMoves.remove(ps);
                        }
                    }
                }
            }
        } catch (Exception ignore) {
        }

        for(Spot s: possibleMoves){
            if(s.getX() == x){
                if(s.getY() == y){
                    contains = true;
                }
            }
        }

        return contains;
    }

    public synchronized boolean validateMove(int x,int y,Spot[][] board,Collection<Spot> impossibleMoves) { //Sprawdzamy czy należy do zbioru
        return this.contains(x,y,board,impossibleMoves);
    }

    public synchronized boolean move(int x,int y,Spot[][] board,Collection<Spot> impossibleMoves){
        if(validateMove(x,y,board,impossibleMoves)){
            setX(x);
            setY(y);
            return true;
        }else {
            return false;
        }
    }

    public synchronized boolean getIsMate(){
        return isMate;
    }

    public synchronized void setIsMate(boolean isMate){
        this.isMate = isMate;
    }
}
