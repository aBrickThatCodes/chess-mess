package chess.game;

import chess.Config;

import java.util.ArrayList;

public class Game {

    public Board board;
    public ArrayList<Board> boaredChanges;
    public ArrayList<Player> players;
    public Player currentTurn;
    Config config;


    public Game(){

        //Plansza
        board.setBoard(players);

        //Gracze
        players = new ArrayList<>(config.playerAmount);

        if(config.pvp){
            for(int i = 0; i< config.playerAmount; i++){
                players.add(new Player.HumanPlayer());
                players.get(i).attackDirection = Player.AttackDirection.values()[i%4];
            }
        }
        else {
            players.add(new Player.HumanPlayer());
            players.get(0).attackDirection = Player.AttackDirection.RIGHT;
            for(int i = 0; i< config.playerAmount -1; i++){
                players.add(new Player.AIPlayer());
            }
        }

    }

    public synchronized void addBoardChange(Board board){
        this.boaredChanges.add(board);
    }

    public synchronized void checkCheck(){
        
    }

    /*public synchronized void setPlayer(Player player, int numPlayer){
        this.players.set(numPlayer, player);
    }

    public synchronized Player getPlayer(int num){
        return this.players.get(num);
    }

    public synchronized void isCheck(){
    }*/
}
