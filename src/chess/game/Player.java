package chess.game;

public abstract class Player {

    private boolean isHuman;
    private int number;

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

    public class HumanPlayer extends Player{

        public HumanPlayer(int numberHuman)

        {
            this.setHuman(true);
            this.setNumber(numberHuman);
        }
    }

    public class AIPlayer extends Player{
        AIPlayer(int numberComputer) {
            this.setHuman(false);
            this.setNumber(numberComputer);
        }
    }

}
