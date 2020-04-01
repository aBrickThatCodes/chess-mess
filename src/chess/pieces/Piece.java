package chess.pieces;

<<<<<<< HEAD
import java.awt.*;
=======
import java.awt.Color;
>>>>>>> fbdc5763f37a39ecb504b51994bd1b2add01578a

public abstract class Piece {
    private int x,y;
    private boolean available; //zmienna blokująca ruch w przypadku szacha lub gdy pion umrze
<<<<<<< HEAD
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
=======
    private Color color; //zastanawiam się czy tutaj umieścić kolor pionka, więc wstępnie dałem
>>>>>>> fbdc5763f37a39ecb504b51994bd1b2add01578a

    public int getY(){return this.y;}

    public abstract void move(int move, int direction);
}
