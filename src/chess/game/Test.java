package chess.game;

import chess.Config;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Test{

    private Spot[][] board = new Spot[3][3];
    private int currentX;
    private int currentY;
    private Piece currentChosenPiece = null;

public class MyMouseListener implements MouseListener {
    private int x;
    private int y;

    public MyMouseListener(int x,int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int previusX = currentX;
        currentX = x;

        int previusY = currentY;
        currentY = y;

        if(currentChosenPiece != null){
            if(currentChosenPiece.move(currentX,currentY,board)){
                board[previusX][previusY].setPiece(null);
                board[currentX][currentY].setPiece(currentChosenPiece);
                currentChosenPiece = null;
                System.out.println("Pionek przestawiono "+ currentX + " "+ currentY);
            }
            else if (currentChosenPiece.move(currentX,currentY,board)){
                System.out.println("Poza możliwościami pionka lub jest tam inny pionek");
                currentChosenPiece = null;
            }
        }else{
            try{
                currentChosenPiece = board[currentX][currentY].getPiece();
                System.out.println("Current spot " + currentX + " " + currentY);
                System.out.println("Previous spot " + previusX + " " + previusY);
                System.out.println("Udało się załadować " + currentChosenPiece.getPieceIcon());

            } catch (Exception e) {
                System.out.println("Brak pionka");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        /*currentX = x;
        currentChosenPiece = spots.get(x).getPiece();*/
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        /*if(currentChosenPiece != null){
            if(currentChosenPiece.move(currentX,0)){
                System.out.println("Pion przestawiono "+ currentX + " "+ 0);
                currentChosenPiece = null;
            }
            else if (currentChosenPiece.move(currentX,0)){
                System.out.println("Poza możliwościami pionka");
            }
        }else{
            currentChosenPiece = spots.get(n).getPiece();
            try{
                System.out.println(currentChosenPiece.getPieceIcon());
            } catch (Exception e) {
                System.out.println("Brak pionka");
            }
        }*/
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}

    public Test(){

        Config.loadSettings();
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(640,640);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(3,3));

        //Stworzenie planszy
        for(int i = 0; i<3;i++){
            for(int j = 0; j<3;j++){
                board[i][j] = new Spot(i,j);
                board[i][j].addMouseListener(new MyMouseListener(i,j));
            }
        }

        //Dodanie kolorów
        for(int i = 0; i<3;i++){
            for(int j = 0; j<3;j++){
                if (j%2 == 0){
                    for(int k = 0; k<3;k++){
                        if (k%2 == 0){
                            board[k][j].setColor(Color.WHITE);
                        }else {
                            board[k][j].setColor(Color.BLACK);
                        }
                    }
                }else {
                    for(int k = 0; k<3;k++){
                        if (k%2 == 0){
                            board[k][j].setColor(Color.BLACK);
                        }else {
                            board[k][j].setColor(Color.WHITE);
                        }
                    }
                }

            }
        }

        ///Dodanie pionka
        Rook rook = new Rook();
        rook.setX(0);
        rook.setY(0);
        board[0][0].setPiece(rook);

        Queen queen = new Queen();
        queen.setLoctaion(0,2);
        queen.setColor(Color.DARK_GRAY);
        board[0][2].setPiece(queen);

        //Dodanie do głównego panelu
        for(int i = 0; i<3;i++){
            for(int j = 0; j<3;j++){//Zamienione indeksy przy dodawaniu żeby zmienić kierunek dodawaia
                frame.add(board[j][i]);
                board[j][i].revalidate();
            }
        }

        frame.revalidate();
    }
    public static void main(String[] args){
        new Test();
    }
}
