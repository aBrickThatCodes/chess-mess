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

    //private Spot[][] board = new Spot[3][3];

    private Board gameBoard;
    ArrayList<Player> players;

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

    public class Board extends JPanel {

        private Spot[][] board;
        private chess.game.Board.GameStatus status;

        public Board(){
            setLayout(new GridLayout(3,3));
            setBoard();
            status = chess.game.Board.GameStatus.ACTIVE;
        }


        public synchronized void setBoard(){

            board = new Spot[3][3];

            for(int i = 0; i< 3 ; i++){
                for(int j = 0; j< 3 ; j++){
                    if(board[i][j] == null){
                        board[i][j] = new Spot(i,j);
                        board[i][j].addMouseListener(new MyMouseListener(i,j));
                    }
                }
            }

            //Kolorowanie
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

            Rook rook = new Rook();
            rook.setLoctaion(0,0);
            board[0][0].setPiece(rook);

            Queen queen = new Queen();
            queen.setLoctaion(1,0);
            board[1][0].setPiece(queen);

            for(int i = 0; i< 3 ; i++){
                for(int j = 0; j< 3 ; j++){
                    board[i][j].revalidate();
                    this.add(board[i][j]);
                }
            }

            this.revalidate();

        /* Oryginalna metoda na zrobienie planszy
            //Pierwszy rzad
        board[0][0] = new Spot(new Rook(false,0,0));
        board[1][0] = new Spot(new Knight(true,1,0));
        board[2][0] = new Spot(new Bishop(false,2,0));
        board[3][0] = new Spot(new Queen(false,3,0));
        board[4][0] = new Spot(new King(false,4,0));
        board[5][0] = new Spot(new Bishop(false,5,0));
        board[6][0] = new Spot(new Knight(true,6,0));
        board[7][0] = new Spot(new Rook(false,7,0));

            //Drugi rzad
        board[0][1] = new Spot(new Pawn(true,0,1));
        board[1][1] = new Spot(new Pawn(true,1,1));
        board[2][1] = new Spot(new Pawn(true,2,1));
        board[3][1] = new Spot(new Pawn(true,3,1));
        board[4][1] = new Spot(new Pawn(true,4,1));
        board[5][1] = new Spot(new Pawn(true,5,1));
        board[6][1] = new Spot(new Pawn(true,6,1));
        board[7][1] = new Spot(new Pawn(true,7,1));
            //Pierwszy przeciwny
        board[0][6] = new Spot(new Pawn(true,0,6));
        board[1][6] = new Spot(new Pawn(true,1,6));
        board[2][6] = new Spot(new Pawn(true,2,6));
        board[3][6] = new Spot(new Pawn(true,3,6));
        board[4][6] = new Spot(new Pawn(true,4,6));
        board[5][6] = new Spot(new Pawn(true,5,6));
        board[6][6] = new Spot(new Pawn(true,6,6));
        board[7][6] = new Spot(new Pawn(true,7,6));
        //Drugi przeciwny
        board[0][7] = new Spot(new Rook(false,0,7));
        board[1][7] = new Spot(new Knight(true,1,7));
        board[2][7] = new Spot(new Bishop(false,2,7));
        board[3][7] = new Spot(new King(false,3,7));
        board[4][7] = new Spot(new Queen(false,4,7));
        board[5][7] = new Spot(new Bishop(false,5,7));
        board[6][7] = new Spot(new Knight(true,6,7));
        board[7][7] = new Spot(new Rook(false,7,7));

        //Metoda na plansze przez tablice pionków

        //Pionki
        for(int i=0;i<8;i++){
            board[i][1].setPiece(players.get(0).playerPieces.get(0).get(i));
            board[i][6].setPiece(players.get(1).playerPieces.get(0).get(i));
        }

        //Wieża
       /* board[0][0].setPiece(playerPieces.get(0).get(8));
        board[7][0].setPiece(playerPieces.get(0).get(9));

        board[0][7].setPiece(playersPieces.get(1).get(8));
        board[7][7].setPiece(playersPieces.get(1).get(9));

        //Konie
        board[1][0].setPiece(playersPieces.get(0).get(10));
        board[6][0].setPiece(playersPieces.get(0).get(11));

        board[1][7].setPiece(playersPieces.get(1).get(10));
        board[6][7].setPiece(playersPieces.get(1).get(11));

        //Gońce
        board[2][0].setPiece(playersPieces.get(0).get(12));
        board[5][0].setPiece(playersPieces.get(0).get(13));

        board[2][7].setPiece(playersPieces.get(1).get(12));
        board[5][7].setPiece(playersPieces.get(1).get(13));

        //Królowa
        board[3][0].setPiece(playersPieces.get(0).get(14));
        board[4][7].setPiece(playersPieces.get(1).get(14));

        //Król
        board[4][0].setPiece(playersPieces.get(0).get(15));
        board[3][7].setPiece(playersPieces.get(1).get(15));*/
        }

        public synchronized void refreshBoard(){

            for(int i = 0; i< 3 ; i++){
                for(int j = 0; j< 3 ; j++){
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
        for(int i = 0; i<3;i++){
            for(int j = 0; j<3;j++){//Zamienione indeksy przy dodawaniu żeby zmienić kierunek dodawaia
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
