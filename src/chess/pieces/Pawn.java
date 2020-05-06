package chess.pieces;

import chess.game.Player;
import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private String pieceIcon = "♟";
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

    public synchronized String getPieceIcon(){
        return this.pieceIcon;
    }

    //Zbiór możliwych ruchów
    public synchronized Collection<Spot> getPossibleMoves(Spot[][] board) {

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
        }

        List<Spot> possibleMoves = new ArrayList<Spot>();

        try{
            Spot ahead = board[getX()+x][getY()+y];
            if (ahead.getPiece() == null) possibleMoves.add(ahead); //Sprawdzamy dostępność i dodajemy do listy możliwych
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

        return possibleMoves;
    }
}
