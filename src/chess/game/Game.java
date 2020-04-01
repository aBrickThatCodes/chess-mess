package chess.game;

public class Game {

    private Board board = new Board();
    private Player[] players;

    public Game(){
        super();
    }

    public synchronized void setPlayers(int numberOfPlayers, boolean playerVsPlayer){
        if(playerVsPlayer){
            for(int i = 0; i<numberOfPlayers;i++){
                this.players[i] = new Player.HumanPlayer(i);
            }
        }
        else{
            this.players[0] = new Player.HumanPlayer(0);
            for (int i =1;i<numberOfPlayers;i++){
                this.players[i] = new Player.AIPlayer(i);
            }
        }
    }

    public synchronized Board getBoard(){
        return this.board;
    }

    public synchronized void setCustomBoard(int boardWidth, int boardHeight){
        this.board = new Board(boardWidth,boardHeight);
    }

    public synchronized Player getPlayer(int numberOfPlayer){
        return this.players[numberOfPlayer];
    }
}
