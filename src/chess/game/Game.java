package chess.game;

import chess.Config;
import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board;
    private ArrayList<Board> boardChanges;
    private ArrayList<Player> players;
    //public Player currentTurn;
    Config config;


    public Game(){

        //Gracze
        players = new ArrayList<>(config.playerAmount);

        //Plansza
        board.setBoard(players);

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

        while(board.getStatus() == Board.GameStatus.ACTIVE){
            for(Player currentTurn: players){
                
            }
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
                        allPossibleMoves.add((Spot) p2.getPossibleMoves());
                    }
                }
            }
        }

        try{
            king.getPossibleMoves().remove(allPossibleMoves);
        } catch (Exception e) {}
    } //trzeba będzie sprawdzić konkretnie czy działą

    /*public synchronized void setPlayer(Player player, int numPlayer){
        this.players.set(numPlayer, player);
    }

    public synchronized Player getPlayer(int num){
        return this.players.get(num);
    }

    public synchronized void isCheck(){
    }*/
}
