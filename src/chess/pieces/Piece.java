package chess.pieces;

public abstract class Piece {
    private boolean available; //zmienna blokująca ruch w przypadku szacha lub gdy pion umrze
    private int color; //zastanawiam się czy tutaj umieścić kolor pionka, więc wstępnie dałem

    abstract public void move();//metoda do poruszania pionkiem

}
