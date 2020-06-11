package chess.game;

import chess.pieces.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
            }
            else if (!currentChosenPiece.move(currentX,currentY,gameBoard.getBoard())){
                System.out.println("Poza możliwościami pionka lub jest tam inny pionek");
                currentChosenPiece = null;
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

//Mosue listener do textowej wersji
    /*public class MyFocusListener implements FocusListener {
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

            if(currentChosenPiece != null){
                if(currentChosenPiece instanceof King){
                    King king = (King) currentChosenPiece;
                    if(king.move(currentX,currentY,gameBoard.getBoard(),mayBeChecked(king))) {
                        gameBoard.getBoard()[previusX][previusY].setPiece(null);
                        gameBoard.getBoard()[currentX][currentY].setPiece(currentChosenPiece);
                        currentChosenPiece = null;
                        System.out.println("Pionek przestawiono " + currentX + " " + currentY);
                        playerNum++;
                    }
                }
                else if(currentChosenPiece.move(currentX,currentY,gameBoard.getBoard())){
                    gameBoard.getBoard()[previusX][previusY].setPiece(null);
                    gameBoard.getBoard()[currentX][currentY].setPiece(currentChosenPiece);
                    currentChosenPiece = null;
                    System.out.println("Pionek przestawiono "+ currentX + " "+ currentY);
                }
                else if (!currentChosenPiece.move(currentX,currentY,gameBoard.getBoard())){
                    System.out.println("Poza możliwościami pionka lub jest tam inny pionek");
                    currentChosenPiece = null;
                }
                setCheck();
            }else {
                try{
                    currentChosenPiece = gameBoard.getBoard()[currentX][currentY].getPiece();
                    if(validateChoosenPiece() && currentChosenPiece instanceof King){
                        King king = (King) currentChosenPiece;
                        for(Spot s:king.getPossibleMoves(gameBoard.getBoard(),mayBeChecked(king))){
                            if(gameBoard.getBoard()[s.getX()][s.getY()].getColor() == Color.WHITE){
                                gameBoard.getBoard()[s.getX()][s.getY()].setColor(Color.blue);
                            }else{
                                gameBoard.getBoard()[s.getX()][s.getY()].setColor(Color.blue);
                            }
                        }
                        System.out.println("Current spot " + currentX + " " + currentY);
                        System.out.println("Previous spot " + previusX + " " + previusY);
                        System.out.println("Udało się załadować " + currentChosenPiece.getPieceIcon());

                    }else if(validateChoosenPiece()){
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
    }*/

    @SuppressWarnings("serial")
    public class Board extends JPanel {

        private Spot[][] board;
        private chess.game.Board.GameStatus status;

        public Board(){
            setLayout(new GridLayout(boardSize,boardSize));
            setBoard();
            status = chess.game.Board.GameStatus.ACTIVE;
        }

        public synchronized void setBoard(){

            board = new Spot[boardSize][boardSize];

            for(int i = 0; i< boardSize ; i++){
                for(int j = 0; j< boardSize ; j++){
                    if(board[i][j] == null){
                        board[i][j] = new Spot(i,j);
                        board[i][j].addMouseListener(new MyMouseListener(i,j));
                    }
                }
            }

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

    //Sprawdza czy jest szach na królu
    public synchronized boolean checkForCheck(){
        boolean isCheck= false;
        King king;
        for(Player p: players) {
            if(p != currentPlayer) {
                for (ArrayList<Piece> aL : p.playerPieces) {
                    for (Piece piece : aL) {
                        if (piece instanceof King) {
                            king = (King) piece;
                            if (king.getIsChecked()) {
                                isCheck = king.getIsChecked();
                                break;
                            }
                        }
                    }
                }
            }
        }
        return isCheck;
    }

    //Ustawia szacha na królu
    public synchronized void setCheck() {
        for (ArrayList<Piece> pieces : currentPlayer.playerPieces) {
            for (Piece piece : pieces) {
                for (Spot s : piece.getPossibleMoves(gameBoard.getBoard())) {
                    for (Player p : players) {
                        if (p != currentPlayer) {
                            for (ArrayList<Piece> pieces1 : p.playerPieces) {
                                for (Piece piece1 : pieces1) {
                                    if(piece1 instanceof King) {
                                        if(s.getY() == piece1.getY() && s.getX() == piece1.getX()) {
                                            System.out.println("SZACH");
                                            King king = (King) piece1;
                                            king.isChecked(true);
                                            gameBoard.getBoard()[s.getX()][s.getY()].setColor(Color.yellow);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //Usuwa ruchy królowi
    public synchronized Collection<Spot> mayBeChecked(King king){
        List<Spot> impossibleMoves = new ArrayList<>();
        List<Spot> enemyMoves = new ArrayList<>();

        for (Player p : players) {
            if (p != currentPlayer) {
                for (ArrayList<Piece> pieces : p.playerPieces) {
                    for (Piece piece: pieces) {
                        for(Spot s:piece.getPossibleMoves(gameBoard.getBoard()))
                        if(king.getPossibleMoves(gameBoard.getBoard()).contains(s)){
                            impossibleMoves.add(s);
                        }
                    }
                }
            }
        }

        return impossibleMoves;
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
