package chess.game;

import chess.Config;
import chess.pieces.Piece;
import chess.pieces.Queen;

import javax.swing.*;
import java.awt.*;
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
    Config config;


    public Game(){

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(640,640);

        //Gracze
        players = new ArrayList<>(config.playerAmount);

        //Plansza
        gameBoard = new Board(players);

        if(config.pvp){
            for(int i = 0; i< config.playerAmount; i++){
                players.add(new Player.HumanPlayer());
                players.get(i).attackDirection = Player.AttackDirection.values()[i%4];
            }
        }
        else {
            players.add(new Player.HumanPlayer());
            players.get(0).attackDirection = Player.AttackDirection.RIGHT;
            for(int i = 0; i< config.playerAmount-1; i++){
                players.add(new Player.AIPlayer());
            }
        }

        for(int i =0; i<config.boardWidth;i++){
            for(int j =0; j<config.boardWidth;j++){
                gameBoard.getBoard()[i][j].addMouseListener(new MyMouseListener(i,j));
            }
        }

        this.add(gameBoard);
    }

    public class MyMouseListener implements MouseListener {
        private int x;
        private  int y;
        public MyMouseListener(int x,int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

            //Moves pieces
            //moves images!!!
            //worked in test on 2 spots with rook  (rook has additional constants to be changed later)
            //additional prints to check how it works
            //should work here

            int previousX = currentX;
            int previousY = currentY;

            currentX = x;
            currentY = y;

            if(currentChosenPiece != null){
                if(currentChosenPiece.move(currentY,currentX)){
                    System.out.println("Pion przestawiono "+ currentX + " "+ currentY);
                    gameBoard.getBoard()[currentY][currentX].setPiece(currentChosenPiece);
                    gameBoard.getBoard()[previousY][previousX].setPiece(null);
                    currentChosenPiece = null;
                }
                else if (currentChosenPiece.move(currentY,currentY)){
                    System.out.println("Poza możliwościami pionka");
                }
            }else{
                currentChosenPiece = gameBoard.getBoard()[currentY][currentX].getPiece();
                try{
                    System.out.println(currentChosenPiece.getPieceIcon());
                } catch (Exception e) {
                    System.out.println("Brak pionka");
                }
            }

        }

        //press and release don't work yet
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            currentX = x;
            currentY = y;
            currentChosenPiece = gameBoard.getBoard()[currentX][currentY].getPiece();
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            int previousX = currentX;
            int previousY = currentY;

            currentX = x;
            currentY = y;

            if(currentChosenPiece != null){
                if(currentChosenPiece.move(currentX,currentY)){
                    System.out.println("Pion przestawiono "+ currentX + " "+ currentY);
                    gameBoard.getBoard()[currentX][currentY].setPiece(currentChosenPiece);
                    gameBoard.getBoard()[previousX][previousY].setPiece(null);
                    currentChosenPiece = null;
                }
                else if (currentChosenPiece.move(currentX,currentY)){
                    System.out.println("Poza możliwościami pionka");
                }
            }else{
                currentChosenPiece = gameBoard.getBoard()[currentX][currentX].getPiece();
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
                        allPossibleMoves.addAll( p2.getPossibleMoves());
                    }
                }
            }
        }

        try{
            king.getPossibleMoves().remove(allPossibleMoves);
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
