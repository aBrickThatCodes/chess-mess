package chess.pieces;

import java.awt.*;

public abstract class Piece {
    private boolean available; //zmienna blokująca ruch w przypadku szacha lub gdy pion umrze
    private Color color;

    abstract public void move();//metoda do poruszania pionkiem

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
}
