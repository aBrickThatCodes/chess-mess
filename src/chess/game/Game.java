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


@SuppressWarnings({"serial", "unused"})
public class Game extends JFrame {

    GameData gameData = new GameData();

    public Game() {

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(640, 640);

        Player.AttackDirection[] values = Player.AttackDirection.values();

        if (Config.Instance().pvp) {
            for (int i = 0; i < Config.Instance().playerAmount; i++) {
                gameData.getPlayers().add(new Player.HumanPlayer(0, values[i % Config.Instance().playerAmount], Config.Instance().colors[i]));
                gameData.getPlayers().get(i).attackDirection = Player.AttackDirection.values()[i % 4];
            }
        } else {
            gameData.getPlayers().add(new Player.HumanPlayer(0, Player.AttackDirection.RIGHT, Config.Instance().colors[0]));
            for (int i = 1; i < Config.Instance().playerAmount; i++) {
                gameData.getPlayers().add(new Player.AIPlayer(0, values[i % Config.Instance().playerAmount], Config.Instance().colors[i]));
            }
        }

        gameData.getBoard().setBoard(gameData.getPlayers());

        for (int i = 0; i < Config.Instance().boardWidth; i++) {
            for (int j = 0; j < Config.Instance().boardHeight; j++) {
                gameData.getBoard().getBoard()[i][j].addMouseListener(new MyMouseListener(i, j));
            }
        }

        gameData.setCurrentTurn(gameData.getPlayers().get(0));
        this.add(gameData.getBoard());
    }

    public class MyMouseListener implements MouseListener {
        private int x;
        private int y;

        public MyMouseListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

            gameData.setCurrentTurn(gameData.getPlayers().get(gameData.getPlayerNum() % gameData.getPlayers().size()));

            if (gameData.getCurrentTurn() instanceof Player.HumanPlayer) {

                int previusX = gameData.getCurrentX();
                gameData.setCurrentX(x);

                int previusY = gameData.getCurrentY();
                gameData.setCurrentY(y);

                if (gameData.getCurrentChosenPiece() != null) {
                    if (gameData.getCurrentChosenPiece() instanceof King) {
                        King king = (King) gameData.getCurrentChosenPiece();
                        if (king.move(gameData.getCurrentX(), gameData.getCurrentY(), gameData.getBoard().getBoard(), mayBeChecked(king))) {
                            gameData.getBoard().getBoard()[previusX][previusY].setPiece(null);
                            gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(king);
                            king = null;
                            gameData.setCurrentChosenPiece(null);
                            System.out.println("Pionek przestawiono " + gameData.getCurrentY() + " " + gameData.getCurrentY());
                            gameData.setPlayerNum(gameData.getPlayerNum() + 1);
                        } else {
                            gameData.setCurrentChosenPiece(null);
                        }
                    } else if (gameData.getCurrentChosenPiece().move(gameData.getCurrentX(), gameData.getCurrentY(), gameData.getBoard().getBoard())) {
                        gameData.getBoard().getBoard()[previusX][previusY].setPiece(null);
                        gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                        gameData.setCurrentChosenPiece(null);
                        System.out.println("Pionek przestawiono " + gameData.getCurrentY() + " " + gameData.getCurrentY());
                        gameData.setPlayerNum(gameData.getPlayerNum() + 1);
                    } else if (!gameData.getCurrentChosenPiece().move(gameData.getCurrentX(), gameData.getCurrentY(), gameData.getBoard().getBoard())) {
                        System.out.println("Poza możliwościami pionka lub jest tam inny pionek");
                        gameData.setCurrentChosenPiece(null);
                    }
                    gameData.getBoard().refreshBoard();
                    gameData.getBoard().repaintColors();
                    setCheck();
                    isMate();
                } else {
                    try {
                        gameData.setCurrentChosenPiece(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece());
                        if (validateChoosenPiece() && gameData.getCurrentChosenPiece() instanceof King) {
                            King king = (King) gameData.getCurrentChosenPiece();
                            for (Spot s : king.getPossibleMoves(gameData.getBoard().getBoard(), mayBeChecked(king))) {
                                if (gameData.getBoard().getBoard()[s.getX()][s.getY()].getColor() == Color.WHITE) {
                                    gameData.getBoard().getBoard()[s.getX()][s.getY()].setColor(Color.blue);
                                } else {
                                    gameData.getBoard().getBoard()[s.getX()][s.getY()].setColor(Color.blue);
                                }
                            }
                            System.out.println("Current spot " + gameData.getCurrentY() + " " + gameData.getCurrentY());
                            System.out.println("Previous spot " + previusX + " " + previusY);
                        } else if (validateChoosenPiece()) {
                            for (Spot s : gameData.getCurrentChosenPiece().getPossibleMoves(gameData.getBoard().getBoard())) {
                                if (gameData.getBoard().getBoard()[s.getX()][s.getY()].getColor() == Color.WHITE) {
                                    gameData.getBoard().getBoard()[s.getX()][s.getY()].setColor(Color.blue);
                                } else {
                                    gameData.getBoard().getBoard()[s.getX()][s.getY()].setColor(Color.blue);
                                }
                            }
                            System.out.println("Current spot " + gameData.getCurrentY() + " " + gameData.getCurrentY());
                            System.out.println("Previous spot " + previusX + " " + previusY);
                        } else {
                            gameData.setCurrentChosenPiece(null);
                            System.out.println("Pionek przeciwnika lub puste pole");
                        }
                    } catch (Exception e) {
                        System.out.println("Brak pionka");
                    }
                }
            } else if (gameData.getCurrentTurn() instanceof Player.AIPlayer) {

                ArrayList<Piece> possiblePieces = new ArrayList<>();
                ArrayList<Spot> possibleMoves;

                for (ArrayList<Piece> pieces : gameData.getCurrentTurn().playerPieces) {
                    possiblePieces.addAll(pieces);
                }

                Spot choosenMove;

                for (Piece p : possiblePieces) {
                    gameData.setCurrentChosenPiece(possiblePieces.get((int) (possiblePieces.size() * Math.random())));
                    possibleMoves = (ArrayList<Spot>) gameData.getCurrentChosenPiece().getPossibleMoves(gameData.getBoard().getBoard());
                    if (possibleMoves.size() != 0) {
                        choosenMove = possibleMoves.get((int) ((possibleMoves.size() - 1) * Math.random()));
                        gameData.setCurrentX(choosenMove.getX());
                        gameData.setCurrentY(choosenMove.getY());
                        break;
                    }
                }

                int previusX = gameData.getCurrentChosenPiece().getX();
                int previusY = gameData.getCurrentChosenPiece().getY();

                if (gameData.getCurrentChosenPiece() instanceof King) {
                    King king = (King) gameData.getCurrentChosenPiece();
                    if (king.move(gameData.getCurrentX(), gameData.getCurrentY(), gameData.getBoard().getBoard(), mayBeChecked(king))) {
                        gameData.getBoard().getBoard()[previusX][previusY].setPiece(null);
                        gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(king);
                        king = null;
                        gameData.setCurrentChosenPiece(null);
                        System.out.println("Pionek przestawiono " + gameData.getCurrentY() + " " + gameData.getCurrentY());
                        gameData.setPlayerNum(gameData.getPlayerNum() + 1);
                    } else {
                        gameData.setCurrentChosenPiece(null);
                    }
                } else if (gameData.getCurrentChosenPiece().move(gameData.getCurrentX(), gameData.getCurrentY(), gameData.getBoard().getBoard())) {
                    gameData.getBoard().getBoard()[previusX][previusY].setPiece(null);
                    gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                    gameData.setCurrentChosenPiece(null);
                    System.out.println("Pionek przestawiono " + gameData.getCurrentY() + " " + gameData.getCurrentY());
                    gameData.setPlayerNum(gameData.getPlayerNum() + 1);
                } else if (!gameData.getCurrentChosenPiece().move(gameData.getCurrentX(), gameData.getCurrentY(), gameData.getBoard().getBoard())) {
                    System.out.println("Poza możliwościami pionka lub jest tam inny pionek");
                    gameData.setCurrentChosenPiece(null);
                }
                gameData.getBoard().refreshBoard();
                gameData.getBoard().repaintColors();
                setCheck();
                isMate();
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

    public synchronized boolean validateChoosenPiece() {
        boolean isCorrect = false;

        for (ArrayList<Piece> pieces : gameData.getCurrentTurn().playerPieces) {
            if (pieces.contains(gameData.getCurrentChosenPiece())) {
                isCorrect = true;
                break;
            }
        }
        return isCorrect;
    }

    //Ustawia szacha na królu pozostałych graczy
    public synchronized void setCheck() {
        for (ArrayList<Piece> pieces : gameData.getCurrentTurn().playerPieces) {
            for (Piece piece : pieces) {
                for (Spot s : piece.getPossibleMoves(gameData.getBoard().getBoard())) {
                    for (Player p : gameData.getPlayers()) {
                        if (p != gameData.getCurrentTurn()) {
                            for (ArrayList<Piece> pieces1 : p.playerPieces) {
                                for (Piece piece1 : pieces1) {
                                    if (piece1 instanceof King) {
                                        King king = (King) piece1;
                                        if (s.getY() == piece1.getY() && s.getX() == piece1.getX()) {
                                            System.out.println("SZACH");
                                            king.isChecked(true);
                                            gameData.getBoard().getBoard()[s.getX()][s.getY()].setColor(Color.yellow);
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
        for (Player p : gameData.getPlayers()) {
            if (p != gameData.getCurrentTurn()) {
                for (ArrayList<Piece> pieces : p.playerPieces) {
                    for (Piece piece : pieces) {
                        for (Spot s : piece.getPossibleAttacks(gameData.getBoard().getBoard()))
                            if (king.getPossibleMoves(gameData.getBoard().getBoard()).contains(s)) {
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
        for (ArrayList<Piece> pieces1 : gameData.getCurrentTurn().playerPieces) {
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
        for (ArrayList<Piece> aL : gameData.getCurrentTurn().playerPieces) {
            for (Piece playerPiece : aL) {
                if (playerPiece instanceof King) {
                    king = (King) playerPiece;
                    for (Player p : gameData.getPlayers()) {
                        if (p != gameData.getCurrentTurn()) {
                            for (ArrayList<Piece> pieces : p.playerPieces) {
                                for (Piece piece : pieces) {
                                    for (Spot s : piece.getPossibleAttacks(gameData.getBoard().getBoard()))
                                        if (king.getPossibleMoves(gameData.getBoard().getBoard()).contains(s)) {
                                            impossibleMoves.add(s);
                                        }
                                }
                            }
                        }
                    }
                    ArrayList<Boolean> mateTab = new ArrayList<>();
                    for (Spot kingSpot : king.getPossibleMoves(gameData.getBoard().getBoard())) {
                        if (impossibleMoves.contains(kingSpot)) {
                            mateTab.add(true);
                        }
                    }

                    if (mateTab.size() != 0 && mateTab.size() == king.getPossibleMoves(gameData.getBoard().getBoard()).size()) {
                        king.setIsMate(true);
                        System.out.println("MAT!");
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

        for (ArrayList<Piece> pieces1 : gameData.getCurrentTurn().playerPieces) {
            for (Piece piece1 : pieces1) {
                if (piece1 instanceof King) {
                    King king = (King) piece1;
                    playerMate = king.getIsMate();
                }
            }
        }
        return playerMate;
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
