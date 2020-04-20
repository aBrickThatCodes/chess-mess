package chess.game;

import java.util.ArrayList;

public class Game {
    Board board = new Board();
    ArrayList<Board> boaredChanges;
    ArrayList<Player> players;
    Player currentTurn;
    GameStatus status;


    public Game(){ //podstawowe szachy
        //Plansza
        board.setBoard();
        //Gracze
        players = new ArrayList<>();
        players.add(new Player.HumanPlayer(1,"White"));
        players.add(new Player.HumanPlayer(2,"Black"));
    }

    public Game(String[] args){

    }


    public enum GameStatus {
        ACTIVE,
        BLACK_WIN,
        WHITE_WIN,
        FORFEIT,
        STALEMATE,
        RESIGNATION
    }

    public synchronized void addBoardChange(Board board){
        this.boaredChanges.add(board);
    }

    public synchronized void setStatus(GameStatus status){
        this.status = status;
    }

    public synchronized GameStatus getStatus(){
        return this.status;
    }

    public synchronized void setPlayer(Player player, int numPlayer){
        this.players.set(numPlayer, player);
    }

    public synchronized Player getPlayer(int num){
        return this.players.get(num);
    }
}
