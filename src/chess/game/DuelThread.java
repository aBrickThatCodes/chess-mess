package chess.game;

import java.util.Random;

public class DuelThread extends Thread {
    public DuelThread() {
        super();
    }

    public void run() {
        boolean duel=true;
        while(duel) {
            if(DuelPane.actions[0]!=0 && DuelPane.actions[1]!=0) {
                Random r=new Random();
                DuelPane.health[0]=DuelPane.actions[1]%2*(r.nextInt(3)+1)-DuelPane.actions[0]/2*(r.nextInt(3)+1);
                DuelPane.health[0]=DuelPane.actions[1]%2*(r.nextInt(3)+1)-DuelPane.actions[0]/2*(r.nextInt(3)+1);
                    if(DuelPane.health[1]<=0) {
                        DuelPane.winner=DuelPane.attacker;
                        duel=false;
                    }
                    else if(DuelPane.health[0]<=0) {
                        DuelPane.winner=DuelPane.attacker;
                        duel=false;
                    }
                    else
                        DuelPane.actions[0]=DuelPane.actions[1]=0;
            }
        }
    }
}