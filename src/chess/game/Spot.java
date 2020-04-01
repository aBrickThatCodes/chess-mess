package chess.game;

import chess.pieces.Piece;
import java.awt.Color;

public class Spot{

    private Piece piece;
    public boolean available = true; //zmienna określająca dostępność dla pionków
    private Color spotColor;
    private int additionalStats = 0;
    public Location location;

    public static class Location {
        public int x;
        public int y;

        public Location(int x, int y){
            this.setY(y);
            this.setX(x);
        }

        public synchronized void setX(int x) {
        this.x = x;
        }
        public synchronized int getX(){return this.x;}
        public synchronized void setY(int y) {
            this.y = y;
        }
        public synchronized int getY(){return this.y;}
        }


    public Spot(Piece piece, Color spotColor, boolean available, int additionalStats) {
        this.piece=piece;
        this.spotColor=spotColor;
        this.available=available;
        this.additionalStats=additionalStats;
    }

    public Spot(Piece piece) {
        this.piece=piece;
    }

    public Spot(int x, int y){
        this.setLocation(x,y);
    }

    public Spot(Location location){
        this.location=location;
    }

    public synchronized void setLocation(int x,int y){
        this.location.setX(x);
        this.location.setY(y);
    }

    public synchronized void setAvailable(boolean a) {
        available = a;
    }

    public synchronized void setColor(Color c) {
        spotColor = c;
    }

    public synchronized void setPiece(Piece p) {
        piece = p;
    }

    public synchronized void getAdditionalStats(int aS){
        additionalStats = aS;
    }

    public synchronized Piece getPiece() {
        return piece;
    }

    public synchronized Color getColor() {
        return spotColor;
    }

    public synchronized boolean getAvailable() {
        return available;
    }

    public synchronized int getadditionalStats(){
        return additionalStats;
    }

}
