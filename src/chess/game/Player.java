package chess.game;

public abstract class Player {

    private boolean isHuman;
    private int number;
    private String playerName;

    public void setHuman(boolean isHuman){
        this.isHuman = isHuman;
    }

    public boolean getHuman(){
        return this.isHuman;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public int getNumber(){
        return this.number;
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public static class HumanPlayer extends Player{

        public HumanPlayer(int numberHuman,String playerName)

        {
            this.setHuman(true);
            this.setNumber(numberHuman);
            this.setPlayerName(playerName);
        }
    }

    public class AIPlayer extends Player{
        AIPlayer(int numberComputer) {
            this.setHuman(false);
            this.setNumber(numberComputer);
        }
    }

}
