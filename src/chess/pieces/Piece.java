package chess.pieces;

import chess.game.Spot;

import java.awt.*;
import java.util.Collection;

public abstract class Piece {

    public Piece(){
        super();
    }

    private int x;
    private int y;
    private Color color;

    public abstract Collection<Spot> getPossibleMoves();

    public synchronized void setColor(Color c) {
        color = c;
    }

    public synchronized Color getColor() {
        return color;
    }

    public  synchronized void setLoctaion(int x, int y){
        setX(x);
        setY(y);
    };

    public synchronized void setX(int x) {
        this.x = x;
    }

    public synchronized int getX(){return this.x;}

    public synchronized void setY(int y) {
        this.y = y;
    }

    public synchronized int getY(){return this.y;}

    public synchronized boolean validateMove(Spot destination) { //Sprawdzamy czy należy do zbioru
        return this.getPossibleMoves().contains(destination);
    }

    public synchronized boolean move(int x,int y){
        if(validateMove(new Spot(x,y))){
            setX(x);
            setY(y);
            return true;
        }else {
            return false;
        }
    }


}
