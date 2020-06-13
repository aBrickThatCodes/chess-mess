package chess.game;

import chess.Config;
import chess.pieces.Piece;

import java.util.ArrayList;

public class GameData {
    //Z Game
    private Game game;
    private Board gameBoard;
    private ArrayList<Board> boardChanges;
    private ArrayList<Player> players;
    private Player currentTurn;
    private int currentX;
    private int currentY;
    private Piece currentChosenPiece;

    //Z Board
    private Board board;
    private GameStatus status;

    public GameData() {
        players = new ArrayList<>(Config.Instance().playerAmount);
        board = new Board(this.getPlayers());
        boardChanges = new ArrayList<>();
        currentChosenPiece = null;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public ArrayList<Board> getBoardChanges(){
        return boardChanges;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentTurn() {
        return currentTurn;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public Piece getCurrentChosenPiece() {
        return currentChosenPiece;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Board getBoard() {
        return board;
    }

    public enum GameStatus {
        ACTIVE,
        ENDGAME
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public void setBoardChanges(ArrayList<Board> boardChanges) {
        this.boardChanges = boardChanges;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setCurrentTurn(Player currentTurn) {
        this.currentTurn = currentTurn;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setCurrentChosenPiece(Piece currentChosenPiece) {
        this.currentChosenPiece = currentChosenPiece;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}

