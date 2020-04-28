package chess.game;

import chess.Config;
import chess.pieces.Piece;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Game extends JFrame implements Runnable, MouseListener {

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
        addMouseListener(this);

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

        this.add(gameBoard);
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

    /*public synchronized void setPlayer(Player player, int numPlayer){
        this.players.set(numPlayer, player);
    }

    public synchronized Player getPlayer(int num){
        return this.players.get(num);
    }

    public synchronized void isCheck(){
    }*/

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        currentX = mouseEvent.getX()%this.getWidth()/config.boardWidth;
        currentY = mouseEvent.getY()%this.getHeight()/config.boardHeight;

        if(currentChosenPiece != null){
            if(currentChosenPiece.move(currentX,currentY)){
                System.out.print("Pion przestawiono "+ currentX + " "+ currentY);
                gameBoard.getBoard()[currentX][currentY].setPiece(currentChosenPiece);
                currentChosenPiece = null;
            }
            else if (currentChosenPiece.move(currentX,currentY)){
                System.out.print("Poza możliwościami pionka");
            }
        }else{
            currentChosenPiece = gameBoard.getBoard()[currentX][currentY].getPiece();
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        currentX = mouseEvent.getX()%gameBoard.getWidth()/config.boardWidth;
        currentY = mouseEvent.getY()%gameBoard.getHeight()/config.boardHeight;
        currentChosenPiece = gameBoard.getBoard()[currentX][currentY].getPiece();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(currentChosenPiece != null){
            if(currentChosenPiece.move(currentX,currentY)){
                System.out.print("Pion przestawiono "+ currentX + " "+ currentY);
                gameBoard.getBoard()[currentX][currentY].setPiece(currentChosenPiece);
                currentChosenPiece = null;
            }
            else if (currentChosenPiece.move(currentX,currentY)){
                System.out.print("Poza możliwościami pionka");
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}
}
