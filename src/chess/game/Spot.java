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

        public void setX(int x) {
        this.x = x;
        }
        public int getX(){return this.x;}
        public void setY(int y) {
            this.y = y;
        }
        public int getY(){return this.y;}
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

    public Spot(Location location){
        this.location = location;
    }

    public void setLocation(int x,int y){
        this.location.setX(x);
        this.location.setY(y);
    }

    public void setAvailable(boolean a) {
        available = a;
    }

    public void setColor(Color c) {
        spotColor = c;
    }

    public void setPiece(Piece p) {
        piece = p;
    }

    public void getAdditionalStats(int aS){
        additionalStats = aS;
    }

    public Piece getPiece() {
        return piece;
    }

    public Color getColor() {
        return spotColor;
    }

    public boolean getAvailable() {
        return available;
    }

    public int getadditionalStats(){
        return additionalStats;
    }

}
