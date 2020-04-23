package chess.game;

import chess.Config;
import chess.pieces.Piece;
import java.awt.Color;

public class Spot{

    private Piece piece = null;
    //public boolean available = true;
    private Color spotColor;
    private int x;
    private int y;
    Config config;
    //public Location location;

    /*public static class Location {
        public int x;
        public int y;

        public Location(int x, int y){
            this.setY(y);
            this.setX(x);
        }

        public void setX(int x) {
        this.x = x;
        }
        public int getX(){return this.x;}
        public void setY(int y) {
            this.y = y;
        }
        public int getY(){return this.y;}
        }*/


    /*public Spot(Piece piece, Color spotColor, boolean available, int additionalStats) {
        this.piece=piece;
        this.spotColor=spotColor;
        this.available=available;
        this.additionalStats=additionalStats;
    }*/

    /*public Spot(Piece piece) {
        this.piece=piece;
    }*/

    public Spot(int x, int y) {
        setX(x);
        setY(y);
    }

    /*public Spot(Location location){
        this.location = location;
    }

    public synchronized void setLocation(int x,int y){
        this.location.setX(x);
        this.location.setY(y);
    }*/

    /*public synchronized void setAvailable() {
        if(piece != null) available = false;
    }*/

    public synchronized void setColor(Color c) {
        spotColor = c;
    }

    public synchronized void setPiece(Piece p) {
        piece = p;
    }

    public synchronized Piece getPiece() {
        return piece;
    }

    public synchronized Color getColor() {
        return spotColor;
    }

    /*public synchronized boolean getAvailable() {
        return available;
    }*/

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
}
