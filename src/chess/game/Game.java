package chess.game;

import chess.Config;
import chess.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Game extends JFrame implements Runnable {

    private Board gameBoard;
    private ArrayList<Board> boardChanges;
    private ArrayList<Player> players;
    private Player currentTurn;
    private int currentX;
    private int currentY;
    private Piece currentChosenPiece = null;


    public Game(){

        Config.loadSettings();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(640,640);

        //Gracze
        players = new ArrayList<>(Config.Instance().playerAmount);

        //Plansza
        gameBoard = new Board(players);

        if(Config.Instance().pvp){
            for(int i = 0; i< Config.Instance().playerAmount; i++){
                players.add(new Player.HumanPlayer());
                players.get(i).attackDirection = Player.AttackDirection.values()[i%4];
            }
        }
        else {
            players.add(new Player.HumanPlayer());
            players.get(0).attackDirection = Player.AttackDirection.RIGHT;
            for(int i = 0; i< Config.Instance().playerAmount-1; i++){
                players.add(new Player.AIPlayer());
            }
        }

        for(int i =0; i<Config.Instance().boardWidth;i++){
            for(int j =0; j<Config.Instance().boardWidth;j++){
                gameBoard.getBoard()[i][j].addFocusListener(new MyFocusListener(i,j));
            }
        }

        this.add(gameBoard);
    }

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
        }
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

    public synchronized void addBoardChange(Board board){
        this.boardChanges.add(board);
    }

    public synchronized void deleteKingMovesResultingInCheck(Player currentTurn){
        Piece king = currentTurn.playerPieces[6][0];
        List<Spot> allPossibleMoves = new ArrayList<Spot>();

        for (Player player : players) {
            if (currentTurn != player) {
                for (Piece[] p : player.playerPieces) {
                    for (Piece p2 : p) {
                        //allPossibleMoves.addAll( p2.getPossibleMoves());
                    }
                }
            }
        }

        try{
            //king.getPossibleMoves().remove(allPossibleMoves);
        } catch (Exception e) {}
    } //trzeba będzie sprawdzić konkretnie czy działą

    @Override
    public void run() {

        while(gameBoard.getStatus() == Board.GameStatus.ACTIVE){
            int playerTurn = 0;
            currentTurn = players.get(playerTurn);
            
            playerTurn++;
        }
    }

}
