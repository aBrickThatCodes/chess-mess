package chess.pieces;

import chess.game.Spot;

import java.awt.*;
import java.util.Collection;

public abstract class Piece {

    public Spot pieceLocationSpot;
    private boolean available; //zmienna blokująca ruch w przypadku szacha lub gdy pion umrze
    private Color color;

    public abstract Collection<Spot> getPossibleMoves();

    public abstract boolean validateMove(Spot destination);

    public abstract void move(Spot destination);

    public void setAvailable(boolean a) {
        available = a;
    }

    public boolean getAvailable(){
        return available;
    }

    public void setColor(Color c) {
        color = c;
    }

    public Color getColor() {
        return color;
    }

    /*public void setX(int x) {
        this.x = x;
    }

    public int getX(){return this.x;}

    public void setY(int y) {
        this.y = y;
    }

    public int getY(){return this.y;}*/ // na zaś gdyby trzeba był wrócić do zwykłych współżednych


}
