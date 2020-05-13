package chess.game;

import chess.Config;
import chess.pieces.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

@SuppressWarnings("unused")

public class Test{

    private int boardSize = 8;

    private Board gameBoard;
    ArrayList<Player> players = new ArrayList<>();

    private int currentX;
    private int currentY;
    private Piece currentChosenPiece = null;

    private Player currentPlayer;
    private int playerNum;

    //Stara metoda na przesuawnie pionków przy pomocy MauseListenera
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
    public void mousePressed(MouseEvent mouseEvent) {
    }


    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}

    public class MyFocusListener implements FocusListener {
        private int x;
        private int y;

        public MyFocusListener(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public synchronized void focusGained(FocusEvent focusEvent) {

            currentPlayer = players.get(playerNum%players.size());

            int previusX = currentX;
            currentX = x;

            int previusY = currentY;
            currentY = y;

            checkForCheck();

            if(currentChosenPiece != null){
                if(currentChosenPiece.move(currentX,currentY,gameBoard.getBoard())){
                    gameBoard.getBoard()[previusX][previusY].setPiece(null);
                    gameBoard.getBoard()[currentX][currentY].setPiece(currentChosenPiece);
                    currentChosenPiece = null;
                    System.out.println("Pionek przestawiono "+ currentX + " "+ currentY);
                    gameBoard.repaintColors();
                    playerNum++;
                }
                else if (!currentChosenPiece.move(currentX,currentY,gameBoard.getBoard())){
                    System.out.println("Poza możliwościami pionka lub jest tam inny pionek");
                    currentChosenPiece = null;
                    gameBoard.repaintColors();
                }
            }else {
                try{
                    currentChosenPiece = gameBoard.getBoard()[currentX][currentY].getPiece();
                    if(validateChoosenPiece()){
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
                    }else {
                        currentChosenPiece = null;
                        System.out.println("Pionek przeciwnika lub puste pole");
                    }
                } catch (Exception e) {
                    System.out.println("Brak pionka");
                }
            }
        }

        @Override
        public synchronized void focusLost(FocusEvent focusEvent) {

        }
    }

    @SuppressWarnings("serial")
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

            players.add(new Player.HumanPlayer(0, Player.AttackDirection.LEFT,Color.yellow));
            players.add(new Player.HumanPlayer(0,Player.AttackDirection.RIGHT,Color.blue));

            //Dodawanie pionków
            for(Player player:players){
                for(ArrayList<Piece> pieceRow: player.playerPieces){
                    for(Piece piece: pieceRow){
                        piece.setColor(player.getPlayerColor());
                        board[piece.getX()][piece.getY()].setPiece(piece);

                    }
                }
            }

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

    public synchronized ArrayList<Spot> checkForCheck(){
        ArrayList<Spot> isCheck = null;
        //W nietestowej wersji będzie się brało położenie króla/króli w player pieces z grupy z config
        Piece king = currentPlayer.playerPieces.get(5).get(0);
        for(Player p:players){
            if(p != currentPlayer){
                for(ArrayList<Piece> pieces: p.playerPieces){
                    for(Piece piece1:pieces){
                        for(Spot s:piece1.getPossibleMoves(gameBoard.getBoard())){
                            if(king.getPossibleMoves(gameBoard.getBoard()).contains(s)){
                                isCheck.add(s);
                            }
                            //kolorowanie pól do obserwacji zachowania szacha
                            if(gameBoard.getBoard()[s.getX()][s.getY()].getColor() == Color.WHITE){
                                gameBoard.getBoard()[s.getX()][s.getY()].setColor(Color.red);
                            }else{
                                gameBoard.getBoard()[s.getX()][s.getY()].setColor(Color.red);
                            }
                        }
                    }
                }
            }
        }
        return isCheck;
    }

    public synchronized boolean validateChoosenPiece(){
        boolean isCorrect = false;

        for(ArrayList<Piece> pieces: currentPlayer.playerPieces){
            if(pieces.contains(currentChosenPiece)){
                isCorrect = true;
                break;
            }
        }
        return isCorrect;
    }

    public Test() {

        gameBoard=new Board();

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(640,640);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        gameBoard.revalidate();
        frame.add(gameBoard);
        frame.revalidate();
    }

    public static void main(String[] args){
        new Test();
    }
}
