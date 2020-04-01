package chess.pieces;

import java.awt.Color;

public abstract class Piece {
    private boolean available; //zmienna blokująca ruch w przypadku szacha lub gdy pion umrze
    private Color color; //zastanawiam się czy tutaj umieścić kolor pionka, więc wstępnie dałem

    abstract public void move();//metoda do poruszania pionkiem

}
