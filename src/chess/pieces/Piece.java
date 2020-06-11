package chess.pieces;

import chess.Config;
import chess.game.Spot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;

public abstract class Piece {

    public Piece(){
        super();
    }

    private int x;
    private int y;
    private Color color;
    protected Config config;

    public abstract BufferedImage getPieceIcon();

    public abstract Collection<Spot> getPossibleMoves(Spot[][] board);

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

    public synchronized boolean contains(int x, int y,Spot[][] board){
        boolean contains = false;
        for(Spot s: this.getPossibleMoves(board)){
            if(s.getX() == x){
                if(s.getY() == y){
                    contains = true;
                }
            }
        }
        return contains;
    }

    public synchronized boolean validateMove(int x,int y,Spot[][] board) { //Sprawdzamy czy należy do zbioru
        return this.contains(x,y,board);
    }

    public synchronized boolean move(int x,int y,Spot[][] board){
        if(validateMove(x,y,board)){
            setX(x);
            setY(y);
            return true;
        }else {
            return false;
        }
    }

}
