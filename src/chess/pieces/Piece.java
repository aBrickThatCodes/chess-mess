package chess.pieces;

public abstract class Piece {

    private int posisionX;//położenia
    private int posisionY;
    private boolean available; //zmienna blokująca ruch w przypadku szacha
    private int color; //zastanawiam się czy tutaj umieścić kolor pionka, więc wstępnie dałem

    abstract public void starticPosision();//jak nazwa wskauje metoda ustawiająca położenie początkowe
    abstract public void move();//metoda do poruszania pionkiem

}
