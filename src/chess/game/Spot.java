package chess.game;

import chess.pieces.Piece;

public class Spot{
    private int posisionX;//położenia
    private int posisionY;
    private Piece piece;
    private boolean available = true; //zmienna określająca dostępność dla pionków
    private Color spotColor;
    private int aditionalStats = 0;


    public Spot(int x, int y, Piece piece, boolean white, boolean available, int aditionalStats) {
        this.setPiece(piece);
        this.setX(x);
        this.set(y);
        this.setColor(white);
        this.setAvailable(available);
        this.setAditionalStats(aditionalStats);
    }

    private void setAvailable(boolean a) {
        available = a;
    }

    private void setColor(Color c) {
        spotColor = c;
    }

    private void setY(int y) {
        posisionY = y;
    }

    private void setX(int x) {
        posisionX = x;
    }

    private void setPiece(Piece p) {
        piece = p;
    }

    private void setAditionalStats(int aS){
        aditionalStats = aS;
    }

    private Piece getPiece() {
        return piece;
    }

    private int getX() {
        return posisionX;
    }

    private int getY() {
        return posisionY;
    }

    private Color getColor() {
        return spotColor;
    }

    private boolean getAvailable() {
        return available;
    }

    private int getAditionalStats(){
        return aditionalStats;
    }
}
