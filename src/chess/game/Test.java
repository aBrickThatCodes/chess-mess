package chess.game;

import chess.Config;
import chess.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Test{

    private int boardSize = 8;

    private Board gameBoard;
    ArrayList<Player> players;

    private int currentX;
    private int currentY;
    private Piece currentChosenPiece = null;

    /*public class MyMouseListener implements MouseListener {
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
            if(currentChosenPiece.move(currentX,currentY,gameBoard.getBoard())){
                gameBoard.getBoard()[previusX][previusY].setPiece(null);
                gameBoard.getBoard()[currentX][currentY].setPiece(currentChosenPiece);
                currentChosenPiece = null;
                System.out.println("Pionek przestawiono "+ currentX + " "+ currentY);
            }
            else if (!currentChosenPiece.move(currentX,currentY,gameBoard.getBoard())){
                System.out.println("Poza możliwościami pionka lub jest tam inny pionek");
                currentChosenPiece = null;
            }
        }else{
            try{
                currentChosenPiece = gameBoard.getBoard()[currentX][currentY].getPiece();
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
        currentX = x;
        currentChosenPiece = spots.get(x).getPiece();
    }


    public void mouseReleased(MouseEvent mouseEvent) {
        if(currentChosenPiece != null){
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
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}*/ //Stara metoda na przesuawnie pionków

    public class MyFocusListener implements FocusListener {
        private int x;
        private int y;

        public MyFocusListener(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public synchronized void focusGained(FocusEvent focusEvent) {
            int previusX = currentX;
            currentX = x;

            int previusY = currentY;
            currentY = y;

            if(currentChosenPiece != null){
                if(currentChosenPiece.move(currentX,currentY,gameBoard.getBoard())){
                    gameBoard.getBoard()[previusX][previusY].setPiece(null);
                    gameBoard.getBoard()[currentX][currentY].setPiece(currentChosenPiece);
                    currentChosenPiece = null;
                    System.out.println("Pionek przestawiono "+ currentX + " "+ currentY);
                    gameBoard.repaintColors();
                }
                else if (!currentChosenPiece.move(currentX,currentY,gameBoard.getBoard())){
                    System.out.println("Poza możliwościami pionka lub jest tam inny pionek");
                    currentChosenPiece = null;
                    gameBoard.repaintColors();
                }
            }else{
                try{
                    currentChosenPiece = gameBoard.getBoard()[currentX][currentY].getPiece();
                    for(Spot s:currentChosenPiece.getPossibleMoves(gameBoard.getBoard())){
                        if(gameBoard.getBoard()[s.getX()][s.getY()].getColor() == Color.WHITE){
                            gameBoard.getBoard()[s.getX()][s.getY()].setColor(Color.blue);
                        }else{
                            gameBoard.getBoard()[s.getX()][s.getY()].setColor(Color.blue);
                        }
                    }
                    System.out.println("Current spot " + currentX + " " + currentY);
                    System.out.println("Previous spot " + previusX + " " + previusY);
                    System.out.println("Udało się załadować " + currentChosenPiece.getPieceIcon());

                } catch (Exception e) {
                    System.out.println("Brak pionka");
                }
            }
        }

        @Override
        public synchronized void focusLost(FocusEvent focusEvent) {

        }
    }

    public class Board extends JPanel {

        private Spot[][] board;
        private chess.game.Board.GameStatus status;

        public Board(){
            setLayout(new GridLayout(boardSize,boardSize));
            setBoard();
            status = chess.game.Board.GameStatus.ACTIVE;
        }

        public synchronized void repaintColors(){
            for(int i = 0; i<boardSize;i++){
                for(int j = 0; j<boardSize;j++){
                    if (j%2 == 0){
                        for(int k = 0; k<boardSize;k++){
                            if (k%2 == 0){
                                board[k][j].setColor(Color.WHITE);
                            }else {
                                board[k][j].setColor(Color.BLACK);
                            }
                        }
                    }else {
                        for(int k = 0; k<boardSize;k++){
                            if (k%2 == 0){
                                board[k][j].setColor(Color.BLACK);
                            }else {
                                board[k][j].setColor(Color.WHITE);
                            }
                        }
                    }

                }
            }
        }

        public synchronized void setBoard(){

            board = new Spot[boardSize][boardSize];

            for(int i = 0; i< boardSize ; i++){
                for(int j = 0; j< boardSize ; j++){
                    if(board[i][j] == null){
                        board[i][j] = new Spot(i,j);
                        board[i][j].addFocusListener(new MyFocusListener(i,j));
                    }
                }
            }

            //Kolorowanie
            repaintColors();

            Rook rook = new Rook();
            rook.setLoctaion(0,0);
            board[0][0].setPiece(rook);

            Queen queen = new Queen();
            queen.setLoctaion(1,0);
            board[1][0].setPiece(queen);

            Bishop bishop = new Bishop();
            bishop.setLoctaion(2,0);
            bishop.setColor(Color.DARK_GRAY);
            board[2][0].setPiece(bishop);

            Knight knight = new Knight();
            knight.setLoctaion(3,1);
            knight.setColor(Color.DARK_GRAY);
            board[3][1].setPiece(knight);

            Pawn pawn = new Pawn();
            pawn.setLoctaion(4,0);
            board[4][0].setPiece(pawn);

            King king = new King();
            king.setLoctaion(5,0);
            king.setColor(Color.DARK_GRAY);
            board[5][0].setPiece(king);

            for(int j = 0; j< boardSize ; j++){
                for(int i = 0; i< boardSize ; i++){
                    board[i][j].revalidate();
                    this.add(board[i][j]);
                }
            }

            this.revalidate();

        }

        public synchronized void refreshBoard(){

            for(int i = 0; i< boardSize ; i++){
                for(int j = 0; j< boardSize ; j++){
                    if(board[i][j] == null){
                        this.add(board[i][j]);
                    }
                }
            }
            this.revalidate();
        }

        public synchronized void setStatus(chess.game.Board.GameStatus status){
            this.status = status;
        }

        public synchronized chess.game.Board.GameStatus getStatus(){
            return this.status;
        }

        public synchronized Spot[][] getBoard(){
            return this.board;
        }
    }

    public enum GameStatus {
        ACTIVE,
        ENDGAME
    }

    public Test(){

        gameBoard=new Board();

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(640,640);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        /*//Stworzenie planszy
        for(int i = 0; i<boardSize;i++){
            for(int j = 0; j<boardSize;j++){
                board[i][j] = new Spot(i,j);
                board[i][j].addMouseListener(new MyMouseListener(i,j));
            }
        }

        //Dodanie kolorów
        for(int i = 0; i<boardSize;i++){
            for(int j = 0; j<boardSize;j++){
                if (j%2 == 0){
                    for(int k = 0; k<boardSize;k++){
                        if (k%2 == 0){
                            board[k][j].setColor(Color.WHITE);
                        }else {
                            board[k][j].setColor(Color.BLACK);
                        }
                    }
                }else {
                    for(int k = 0; k<boardSize;k++){
                        if (k%2 == 0){
                            board[k][j].setColor(Color.BLACK);
                        }else {
                            board[k][j].setColor(Color.WHITE);
                        }
                    }
                }

            }
        }*/

        /*///Dodanie pionka
        Rook rook = new Rook();
        rook.setX(0);
        rook.setY(0);
        board[0][0].setPiece(rook);

        Queen queen = new Queen();
        queen.setLoctaion(0,2);
        //queen.setColor(Color.DARK_GRAY);
        board[0][2].setPiece(queen);

        //Dodanie do głównego panelu
        for(int i = 0; i<boardSize;i++){
            for(int j = 0; j<boardSize;j++){//Zamienione indeksy przy dodawaniu żeby zmienić kierunek dodawaia
                frame.add(board[j][i]);
                board[j][i].revalidate();
            }
        }*/

        gameBoard.revalidate();
        frame.add(gameBoard);
        frame.revalidate();
    }

    public static void main(String[] args){
        new Test();
    }
}
