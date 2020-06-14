package chess.pieces;

import chess.Config;
import chess.game.Player;
import chess.game.Spot;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("serial")
public class Pawn extends Piece {
    private boolean wasMoved = false;
    private Player.AttackDirection attackDirection = Player.AttackDirection.LEFT;

    public Pawn(Player.AttackDirection attackDirection){
        this.attackDirection = attackDirection;
    }

    public synchronized Player.AttackDirection getAttackDirection(){
        return this.attackDirection;
    }

    public synchronized void setAttackDirection(Player.AttackDirection attackDirection){
        this.attackDirection = attackDirection;
    }

    public synchronized BufferedImage getPieceIcon(){
        return Config.Instance().pieceImages[0];
    }

    //Zbiór możliwych ruchów
    public synchronized Collection<Spot> getPossibleMoves(Spot[][] board) {

        List<Spot> possibleMoves = new ArrayList<>();

        int x = 0;
        int y =0;
        int z =0;

        switch (attackDirection){
            case LEFT:
                x=1;
                y=0;
                z =1;

                if(!wasMoved){
                    try{
                        Spot ahead = board[getX()+2*x][getY()+2*y];
                        if (ahead.getPiece() == null && !ahead.isBlocked) possibleMoves.add(ahead); //Sprawdzamy dostępność i dodajemy do listy możliwych
                    } catch (Exception e) {
                    }
                }

                try{
                    Spot ahead = board[getX()+x][getY()+y];
                    if (ahead.getPiece() == null && !ahead.isBlocked) possibleMoves.add(ahead); //Sprawdzamy dostępność i dodajemy do listy możliwych
                } catch (Exception e) {
                }

                try{
                    Spot aheadLeft = board[getX()+x][getY()-z];
                    if (aheadLeft.getPiece().getColor() != this .getColor()) possibleMoves.add(aheadLeft);
                } catch (Exception e) {
                }

                try{
                    Spot aheadRight = board[getX()+x][getY()+z];
                    if (aheadRight.getPiece().getColor() != this .getColor()) possibleMoves.add(aheadRight);
                } catch (Exception e) {
                }
                break;
            case RIGHT:
                x=-1;
                y=0;
                z = 1;

                if(!wasMoved){
                    try{
                        Spot ahead = board[getX()+2*x][getY()+2*y];
                        if (ahead.getPiece() == null && !ahead.isBlocked) possibleMoves.add(ahead); //Sprawdzamy dostępność i dodajemy do listy możliwych
                    } catch (Exception e) {
                    }
                }

                try{
                    Spot ahead = board[getX()+x][getY()+y];
                    if (ahead.getPiece() == null && !ahead.isBlocked) possibleMoves.add(ahead); //Sprawdzamy dostępność i dodajemy do listy możliwych
                } catch (Exception e) {
                }

                try{
                    Spot aheadLeft = board[getX()+x][getY()-z];
                    if (aheadLeft.getPiece().getColor() != this .getColor()) possibleMoves.add(aheadLeft);
                } catch (Exception e) {
                }

                try{
                    Spot aheadRight = board[getX()+x][getY()+z];
                    if (aheadRight.getPiece().getColor() != this .getColor()) possibleMoves.add(aheadRight);
                } catch (Exception e) {
                }
                break;
            case DOWN:
                x=0;
                y=1;
                z = 1;

                if(!wasMoved){
                    try{
                        Spot ahead = board[getX()+2*x][getY()+2*y];
                        if (ahead.getPiece() == null && !ahead.isBlocked) possibleMoves.add(ahead); //Sprawdzamy dostępność i dodajemy do listy możliwych
                    } catch (Exception e) {
                    }
                }

                try{
                    Spot ahead = board[getX()+x][getY()+y];
                    if (ahead.getPiece() == null && !ahead.isBlocked) possibleMoves.add(ahead); //Sprawdzamy dostępność i dodajemy do listy możliwych
                } catch (Exception e) {
                }

                try{
                    Spot aheadLeft = board[getX()-z][getY()+y];
                    if (aheadLeft.getPiece().getColor() != this .getColor()) possibleMoves.add(aheadLeft);
                } catch (Exception e) {
                }

                try{
                    Spot aheadRight = board[getX()+z][getY()+y];
                    if (aheadRight.getPiece().getColor() != this .getColor()) possibleMoves.add(aheadRight);
                } catch (Exception e) {
                }
                break;
            case UP:
                x=0;
                y=-1;
                z = 1;
                if(!wasMoved){
                    try{
                        Spot ahead = board[getX()+2*x][getY()+2*y];
                        if (ahead.getPiece() == null && !ahead.isBlocked) possibleMoves.add(ahead); //Sprawdzamy dostępność i dodajemy do listy możliwych
                    } catch (Exception e) {
                    }
                }
                try{
                    Spot ahead = board[getX()+x][getY()+y];
                    if (ahead.getPiece() == null && !ahead.isBlocked) possibleMoves.add(ahead); //Sprawdzamy dostępność i dodajemy do listy możliwych
                } catch (Exception e) {
                }

                try{
                    Spot aheadLeft = board[getX()-z][getY()+y];
                    if (aheadLeft.getPiece().getColor() != this .getColor()) possibleMoves.add(aheadLeft);
                } catch (Exception e) {
                }

                try{
                    Spot aheadRight = board[getX()+z][getY()+y];
                    if (aheadRight.getPiece().getColor() != this .getColor()) possibleMoves.add(aheadRight);
                } catch (Exception e) {
                }
                break;
        }

        return possibleMoves;
    }

    public synchronized Collection<Spot> getPossibleAttacks(Spot[][] board) {

        int x = 0;
        int y =0;
        int z =0;

        switch (attackDirection){
            case LEFT:
                x=1;
                y=0;
                z =1;
                break;
            case RIGHT:
                x=-1;
                y=0;
                z = 1;
                break;
            case DOWN:
                break;
            case UP:
                break;
        }

        List<Spot> possibleMoves = new ArrayList<>();

        try{
            Spot aheadLeft = board[getX()+x][getY()-z];
            if (aheadLeft.getPiece() == null && !aheadLeft.isBlocked) possibleMoves.add(aheadLeft);
        } catch (Exception e) {
        }

        try{
            Spot aheadRight = board[getX()+x][getY()+z];
            if (aheadRight.getPiece() == null && !aheadRight.isBlocked) possibleMoves.add(aheadRight);
        } catch (Exception e) {
        }

        return possibleMoves;
    }

    public synchronized boolean move(int x,int y,Spot[][] board){
        if(validateMove(x,y,board)){
            setX(x);
            setY(y);
            wasMoved = true;
            return true;
        }else {
            return false;
        }
    }
}
