package chess.game;

public abstract class Player {

    private boolean isHuman;
    private int number;
    private String playerName;

    public synchronized void setHuman(boolean isHuman){
        this.isHuman = isHuman;
    }

    public synchronized boolean getHuman(){
        return this.isHuman;
    }

    public synchronized void setNumber(int number){
        this.number = number;
    }

    public synchronized int getNumber(){
        return this.number;
    }

    public synchronized void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public synchronized String getPlayerName(){
        return this.playerName;
    }

    public static class HumanPlayer extends Player{
        public HumanPlayer(int numberHuman) {
            this.setHuman(true);
            this.setNumber(numberHuman);
        }
    }

    public static class AIPlayer extends Player{
        AIPlayer(int numberComputer) {
            this.setHuman(false);
            this.setNumber(numberComputer);
        }
    }
}
