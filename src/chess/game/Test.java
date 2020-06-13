package chess.game;

import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

@SuppressWarnings("unused")

public class Test implements Runnable {

    private int boardSize = 8;

    private Board gameBoard;
    ArrayList<Player> players = new ArrayList<>();

    private int currentX;
    private int currentY;
    private Piece currentChosenPiece = null;

    private Player currentPlayer;
    private int playerNum;

    @Override
    public void run() {
        gameBoard = new Board();

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(640, 640);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ArrayList<Board> boardChanges = new ArrayList<>();


        gameBoard.revalidate();
        frame.add(gameBoard);
        frame.revalidate();
    }

    //Stara metoda na przesuawnie pionków przy pomocy MauseListenera
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
}*/

    public class MyFocusListener implements FocusListener {
        private int x;
        private int y;

        public MyFocusListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public synchronized void focusGained(FocusEvent focusEvent) {

            currentPlayer = players.get(playerNum % players.size());

            int previusX = currentX;
            currentX = x;

            int previusY = currentY;
            currentY = y;

            if (currentChosenPiece != null) {
                if (currentChosenPiece instanceof King) {
                    King king = (King) currentChosenPiece;
                    if (king.move(currentX, currentY, gameBoard.getBoard(), mayBeChecked(king))) {
                        gameBoard.getBoard()[previusX][previusY].setPiece(null);
                        gameBoard.getBoard()[currentX][currentY].setPiece(currentChosenPiece);
                        currentChosenPiece = null;
                        System.out.println("Pionek przestawiono " + currentX + " " + currentY);
                        playerNum++;
                    } else {
                        currentChosenPiece = null;
                    }
                } else if (currentChosenPiece.move(currentX, currentY, gameBoard.getBoard())) {
                    gameBoard.getBoard()[previusX][previusY].setPiece(null);
                    gameBoard.getBoard()[currentX][currentY].setPiece(currentChosenPiece);
                    System.out.println("Pionek przestawiono " + currentX + " " + currentY);
                    playerNum++;
                    currentChosenPiece = null;
                } else if (!currentChosenPiece.move(currentX, currentY, gameBoard.getBoard())) {
                    System.out.println("Poza możliwościami pionka lub jest tam inny pionek");
                    currentChosenPiece = null;
                }
                gameBoard.refreshBoard();
                gameBoard.repaintColors();
                setCheck();
                isMate();
            } else {
                try {
                    currentChosenPiece = gameBoard.getBoard()[currentX][currentY].getPiece();
                    if (validateChoosenPiece() && currentChosenPiece instanceof King) {
                        King king = (King) currentChosenPiece;
                        for (Spot s : king.getPossibleMoves(gameBoard.getBoard(), mayBeChecked(king))) {
                            if (gameBoard.getBoard()[s.getX()][s.getY()].getColor() == Color.WHITE) {
                                gameBoard.getBoard()[s.getX()][s.getY()].setColor(Color.blue);
                            } else {
                                gameBoard.getBoard()[s.getX()][s.getY()].setColor(Color.blue);
                            }
                        }
                        System.out.println("Current spot " + currentX + " " + currentY);
                        System.out.println("Previous spot " + previusX + " " + previusY);
                        System.out.println("Udało się załadować " + currentChosenPiece.getPieceIcon());

                    } else if (validateChoosenPiece()) {
                        for (Spot s : currentChosenPiece.getPossibleMoves(gameBoard.getBoard())) {
                            if (gameBoard.getBoard()[s.getX()][s.getY()].getColor() == Color.WHITE) {
                                gameBoard.getBoard()[s.getX()][s.getY()].setColor(Color.blue);
                            } else {
                                gameBoard.getBoard()[s.getX()][s.getY()].setColor(Color.blue);
                            }
                        }
                        System.out.println("Current spot " + currentX + " " + currentY);
                        System.out.println("Previous spot " + previusX + " " + previusY);
                        System.out.println("Udało się załadować " + currentChosenPiece.getPieceIcon());
                    } else {
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
        private GameStatus status;

        public Board() {
            setLayout(new GridLayout(boardSize, boardSize));
            setBoard();
            status = GameStatus.ACTIVE;
        }

        public synchronized void repaintColors() {
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (j % 2 == 0) {
                        for (int k = 0; k < boardSize; k++) {
                            if (k % 2 == 0) {
                                board[k][j].setColor(Color.WHITE);
                            } else {
                                board[k][j].setColor(Color.BLACK);
                            }
                        }
                    } else {
                        for (int k = 0; k < boardSize; k++) {
                            if (k % 2 == 0) {
                                board[k][j].setColor(Color.BLACK);
                            } else {
                                board[k][j].setColor(Color.WHITE);
                            }
                        }
                    }

                }
            }
        }

        public synchronized void setBoard() {
            board = new Spot[boardSize][boardSize];

            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (board[i][j] == null) {
                        board[i][j] = new Spot(i, j);
                        board[i][j].addFocusListener(new MyFocusListener(i, j));
                    }
                }
            }

            //Kolorowanie
            repaintColors();

            players.add(new Player.HumanPlayer(0, Player.AttackDirection.LEFT, Color.yellow));
            players.add(new Player.HumanPlayer(0, Player.AttackDirection.RIGHT, Color.blue));

            //Dodawanie pionków
            for (Player player : players) {
                for (ArrayList<Piece> pieceRow : player.playerPieces) {
                    for (Piece piece : pieceRow) {
                        piece.setColor(player.getPlayerColor());
                        board[piece.getX()][piece.getY()].setPiece(piece);
                    }
                }
            }

            for (int j = 0; j < boardSize; j++) {
                for (int i = 0; i < boardSize; i++) {
                    board[i][j].revalidate();
                    this.add(board[i][j]);
                }
            }

            this.revalidate();

        }

        public synchronized void refreshBoard() {
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (j % 2 == 0) {
                        for (int k = 0; k < boardSize; k++) {
                            if (k % 2 == 0) {
                                board[k][j].setColor(Color.BLACK);
                            } else {
                                board[k][j].setColor(Color.WHITE);
                            }
                        }
                    } else {
                        for (int k = 0; k < boardSize; k++) {
                            if (k % 2 == 0) {
                                board[k][j].setColor(Color.WHITE);
                            } else {
                                board[k][j].setColor(Color.BLACK);
                            }
                        }
                    }

                }
            }

            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (j % 2 == 0) {
                        for (int k = 0; k < boardSize; k++) {
                            if (k % 2 == 0) {
                                board[k][j].setColor(Color.WHITE);
                            } else {
                                board[k][j].setColor(Color.BLACK);
                            }
                        }
                    } else {
                        for (int k = 0; k < boardSize; k++) {
                            if (k % 2 == 0) {
                                board[k][j].setColor(Color.BLACK);
                            } else {
                                board[k][j].setColor(Color.WHITE);
                            }
                        }
                    }
                }
            }
        }

        public synchronized void setStatus(GameStatus status) {
            this.status = status;
        }

        public synchronized GameStatus getStatus() {
            return this.status;
        }

        public synchronized Spot[][] getBoard() {
            return this.board;
        }
    }

    public enum GameStatus {
        ACTIVE,
        ENDGAME
    }

    public synchronized boolean validateChoosenPiece() {
        boolean isCorrect = false;

        for (ArrayList<Piece> pieces : currentPlayer.playerPieces) {
            if (pieces.contains(currentChosenPiece)) {
                isCorrect = true;
                break;
            }
        }
        return isCorrect;
    }

    //Ustawia szacha na królu pozostałych graczy
    public synchronized void setCheck() {
        for (ArrayList<Piece> pieces : currentPlayer.playerPieces) {
            for (Piece piece : pieces) {
                for (Spot s : piece.getPossibleMoves(gameBoard.getBoard())) {
                    for (Player p : players) {
                        if (p != currentPlayer) {
                            for (ArrayList<Piece> pieces1 : p.playerPieces) {
                                for (Piece piece1 : pieces1) {
                                    if (piece1 instanceof King) {
                                        King king = (King) piece1;
                                        if (s.getY() == piece1.getY() && s.getX() == piece1.getX()) {
                                            System.out.println("SZACH");
                                            king.isChecked(true);
                                            gameBoard.getBoard()[s.getX()][s.getY()].setColor(Color.yellow);
                                        } else {
                                            king.isChecked(false);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //Tablica ruchów królowi powodujące szach
    public synchronized Collection<Spot> mayBeChecked(King king) {
        List<Spot> impossibleMoves = new ArrayList<>();
        for (Player p : players) {
            if (p != currentPlayer) {
                for (ArrayList<Piece> pieces : p.playerPieces) {
                    for (Piece piece : pieces) {
                        for (Spot s : piece.getPossibleAttacks(gameBoard.getBoard()))
                            if (king.getPossibleMoves(gameBoard.getBoard()).contains(s)) {
                                impossibleMoves.add(s);
                            }
                    }
                }
            }
        }
        return impossibleMoves;
    }

    //Sprawdza czy jest szach na królu gracza
    public synchronized boolean isChecked() {
        boolean isChecked = false;
        for (ArrayList<Piece> pieces1 : currentPlayer.playerPieces) {
            for (Piece piece1 : pieces1) {
                if (piece1 instanceof King) {
                    King king = (King) piece1;
                    isChecked = king.getIsChecked();
                }
            }
        }
        return isChecked;
    }

    //Ustawia mat na królu gracza
    public synchronized void isMate() {
        List<Spot> impossibleMoves = new ArrayList<>();
        King king;
        for(ArrayList<Piece> aL:currentPlayer.playerPieces) {
            for(Piece playerPiece:aL){
                if(playerPiece instanceof King){
                    king = (King) playerPiece;
                    for (Player p : players) {
                        if (p != currentPlayer) {
                            for (ArrayList<Piece> pieces : p.playerPieces) {
                                for (Piece piece : pieces) {
                                    for (Spot s : piece.getPossibleAttacks(gameBoard.getBoard()))
                                        if (king.getPossibleMoves(gameBoard.getBoard()).contains(s)) {
                                            impossibleMoves.add(s);
                                        }
                                }
                            }
                        }
                    }
                    ArrayList<Boolean> mateTab = new ArrayList<>();
                    for(Spot kingSpot: king.getPossibleMoves(gameBoard.getBoard())){
                        if(impossibleMoves.contains(kingSpot)){
                            mateTab.add(true);
                        }
                    }

                    System.out.println(mateTab.size() + " " + king.getPossibleMoves(gameBoard.getBoard()).size());
                    if(mateTab.size()!= 0 && mateTab.size() == king.getPossibleMoves(gameBoard.getBoard()).size()){
                        king.setIsMate(true);
                        System.out.println("king.setIsMate(true);");
                    }
                    break;
                }
            }
        }

        /*for (ArrayList<Piece> pieces : currentPlayer.playerPieces) {
            for (Piece piece : pieces) {
                if (piece instanceof King) {
                    King king = (King) piece;
                    king.setIsMate(true);
                    ArrayList<Spot> enemyAttacks = new ArrayList<>();
                    for (Player p : players) {
                        if (p != currentPlayer) {
                            for (ArrayList<Piece> pieces1 : p.playerPieces) {
                                for (Piece enemyPiece : pieces1) {
                                    if(enemyPiece instanceof Pawn){
                                        enemyAttacks.addAll(enemyPiece.getPossibleAttacks(gameBoard.getBoard()));
                                    }else {
                                        enemyAttacks.addAll(enemyPiece.getPossibleMoves(gameBoard.getBoard()));
                                    }
                                }
                            }
                        }
                    }
                    for(Spot kingSpot:king.getPossibleMoves(gameBoard.getBoard(),mayBeChecked(king))){
                        if(!enemyAttacks.contains(kingSpot)){
                            System.out.println("king.setIsMate(false)");
                            king.setIsMate(false);
                        }
                    }
                    break;
                }
            }
        }*/
    }

    //Sprawdza czy król aktualnego gracza ma mata
    public boolean checkForMate() {
        boolean playerMate = false;

        for (ArrayList<Piece> pieces1 : currentPlayer.playerPieces) {
            for (Piece piece1 : pieces1) {
                if (piece1 instanceof King) {
                    King king = (King) piece1;
                    playerMate = king.getIsMate();
                }
            }
        }
        return playerMate;
    }


    public Test() {
        gameBoard = new Board();

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(640, 640);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ArrayList<Board> boardChanges = new ArrayList<>();


        gameBoard.revalidate();
        frame.add(gameBoard);
        frame.revalidate();
    }

    public static void main(String[] args) {
        new Test();
    }
}
