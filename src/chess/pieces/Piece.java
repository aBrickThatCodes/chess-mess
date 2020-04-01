package chess.pieces;
import java.awt.Color;

public abstract class Piece {
    private int x,y;
    private boolean available; //zmienna blokująca ruch w przypadku szacha lub gdy pion umrze
    private Color color;

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

    public void setX(int x) {
        this.x = x;
    }

    public int getX(){return this.x;}

    public void setY(int y) {
        this.y = y;
    }
    public int getY(){return this.y;}

    public abstract void move(int move, int direction);
}
