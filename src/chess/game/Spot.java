package chess.game;

import chess.pieces.Piece;
import java.awt.Color;

public class Spot{
    private Piece piece;
    private boolean available = true; //zmienna określająca dostępność dla pionków
    private Color spotColor;
    private int additionalStats = 0;


    public Spot(Piece piece, Color spotColor, boolean available, int additionalStats) {
        this.piece=piece;
        this.spotColor=spotColor;
        this.available=available;
        this.additionalStats=additionalStats;
    }

    public void SetAvailable(boolean a) {
        available = a;
    }

    public void SetColor(Color c) {
        spotColor = c;
    }

    public void GetPiece(Piece p) {
        piece = p;
    }

    public void GetadditionalStats(int aS){
        additionalStats = aS;
    }

    public Piece GetPiece() {
        return piece;
    }

    public Color GetColor() {
        return spotColor;
    }

    public int GetX() {
        //TODO
        return 0;
    }

    public int GetY() {
        //TODO
        return 0;
    }

    public boolean GetAvailable() {
        return available;
    }

    public int GetadditionalStats(){
        return additionalStats;
    }
}
