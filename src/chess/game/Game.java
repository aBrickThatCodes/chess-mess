package chess.game;

import chess.Config;
import chess.pieces.King;
import chess.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@SuppressWarnings({"serial","unused"})
public class Game extends JFrame implements Runnable{

    private Board gameBoard;
    private ArrayList<Board> boardChanges = new ArrayList<>();
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

        Player.AttackDirection[] values = Player.AttackDirection.values();

        if(Config.Instance().pvp){
            for(int i = 0; i< Config.Instance().playerAmount; i++){
                players.add(new Player.HumanPlayer((i%2)*7,values[i%Config.Instance().playerAmount], Config.Instance().colors[i]));
                players.get(i).attackDirection = Player.AttackDirection.values()[i%4];
            }
        }
        else {
            players.add(new Player.HumanPlayer(0, Player.AttackDirection.RIGHT,Config.Instance().colors[0]));
            for(int i = 0; i< Config.Instance().playerAmount-1; i++){
                players.add(new Player.AIPlayer());
            }
        }

        for(int i =0; i<Config.Instance().boardWidth;i++){
            for(int j =0; j<Config.Instance().boardWidth;j++){
                gameBoard.getBoard()[i][j].addMouseListener(new MyMouseListener(i,j));
            }
        }

        this.add(gameBoard);
    }

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

    //Ustawia szacha na królu pozostałych graczy
    public synchronized void setCheck() {
        for (ArrayList<Piece> pieces : currentTurn.playerPieces) {
            for (Piece piece : pieces) {
                for (Spot s : piece.getPossibleMoves(gameBoard.getBoard())) {
                    for (Player p : players) {
                        if (p != currentTurn) {
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
            if (p != currentTurn) {
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
        for (ArrayList<Piece> pieces1 : currentTurn.playerPieces) {
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
        for(ArrayList<Piece> aL:currentTurn.playerPieces) {
            for(Piece playerPiece:aL){
                if(playerPiece instanceof King){
                    king = (King) playerPiece;
                    for (Player p : players) {
                        if (p != currentTurn) {
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

                    if(mateTab.size()!= 0 && mateTab.size() == king.getPossibleMoves(gameBoard.getBoard()).size()){
                        king.setIsMate(true);
                    }
                    break;
                }
            }
        }

        /*for (ArrayList<Piece> pieces : currentTurn.playerPieces) {
            for (Piece piece : pieces) {
                if (piece instanceof King) {
                    King king = (King) piece;
                    king.setIsMate(true);
                    ArrayList<Spot> enemyAttacks = new ArrayList<>();
                    for (Player p : players) {
                        if (p != currentTurn) {
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

        for (ArrayList<Piece> pieces1 : currentTurn.playerPieces) {
            for (Piece piece1 : pieces1) {
                if (piece1 instanceof King) {
                    King king = (King) piece1;
                    playerMate = king.getIsMate();
                }
            }
        }
        return playerMate;
    }

    public void run(){
        currentTurn = players.get(0);
        boardChanges.add(gameBoard);
        int i =0;
        while(gameBoard.getStatus() == Board.GameStatus.ACTIVE){
            if(gameBoard != boardChanges.get(boardChanges.size()-1)){
                currentTurn = players.get(i%Config.Instance().playerAmount);
                i++;
                isMate();
            }
        }
        System.exit(0);
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
    }*/
}
