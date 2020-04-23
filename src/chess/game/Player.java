package chess.game;

import chess.Config;
import chess.pieces.*;

public abstract class Player {

    Config config;
    public Piece[][] playerPieces;
    public AttackDirection attackDirection;

    // private boolean isHuman;
    /*public void setHuman(boolean isHuman){
        this.isHuman = isHuman;
    }

    public boolean getHuman(){
        return this.isHuman;
    }*/

    public synchronized void setPlayerPieces(){
        //Grup I
        switch (config.pieces[0]) {
            case PAWN:
                for (int i = 0; i < 8; i++) {
                    playerPieces[0][i] = new Pawn();
                }
                break;
            case ROOK:
                for (int i = 0; i < 8; i++) {
                    playerPieces[0][i] = new Rook();
                }
                break;
            case BISHOP:
                for (int i = 0; i < 8; i++) {
                    playerPieces[0][i] = new Bishop();
                }
                break;
            case KNIGHT:
                for (int i = 0; i < 8; i++) {
                    playerPieces[0][i] = new Knight();
                }
                break;
            case QUEEN:
                for (int i = 0; i < 8; i++) {
                    playerPieces[0][i] = new Queen();
                }
                break;
            case KING:
                for (int i = 0; i < 8; i++) {
                    playerPieces[0][i] = new King();
                }
                break;
        }
        //Other Grups
        for(int i =1; i<config.pieces.length-1; i++){
            switch (config.pieces[i]){
                case PAWN:
                    playerPieces[i][0] = new Pawn();
                    playerPieces[i][1] = new Pawn();
                    break;
                case ROOK:
                    playerPieces[i][0] = new Rook();
                    playerPieces[i][1] = new Rook();
                    break;
                case BISHOP:
                    playerPieces[i][0] = new Bishop();
                    playerPieces[i][1] = new Bishop();
                    break;
                case KNIGHT:
                    playerPieces[i][0] = new Knight();
                    playerPieces[i][1] = new Knight();
                    break;
                case QUEEN:
                    playerPieces[i][0] = new Queen();
                    break;
                case KING:
                    playerPieces[i][0] = new King();
                    break;
            }
        }
    }

    public static class HumanPlayer extends Player{

        public HumanPlayer(){
            //setHuman(true);
            setPlayerPieces();
        }
    }

    public static class AIPlayer extends Player{
        AIPlayer() {
            //setHuman(false);
            setPlayerPieces();
        }
    }

    /*public synchronized void addPlayerPieces(int startingRookPosition, Board.AttackDirection attackDirection){

        switch (attackDirection){
            case LEFT:
                //Pionki dodaje wg schamatu 8xpionki 8xpozostałe figury parami Wieża - konie - gońce - królowa - król
                //Żeby potem był ładny dostęp do króla
                //Pionki
                for(int i=startingRookPosition;i<8+startingRookPosition;i++) playerPieces.add(new Pawn(true,i,1));
                //Wieża
                playerPieces.add(new Rook(false,startingRookPosition,0));
                playerPieces.add(new Rook(false,startingRookPosition+7,0));
                //Konie
                playerPieces.add(new Knight(true,startingRookPosition+1,0));
                playerPieces.add(new Knight(true,6+startingRookPosition,0));
                //Gońce
                playerPieces.add(new Bishop(false,2+startingRookPosition,0));
                playerPieces.add(new Bishop(false,5+startingRookPosition,0));
                //Królowa
                playerPieces.add(new Queen(false,3+startingRookPosition,0));
                //Król
                playerPieces.add(new King(false,4+startingRookPosition,0));
                this.playerPieces.add(new ArrayList<>());
                break;
            case RIGHT:

                //Pionki
                for(int i=startingRookPosition;i<8+startingRookPosition;i++) playerPieces.add(new Pawn(true,i,6));
                //Wieża
                playerPieces.add(new Rook(false,startingRookPosition,7));
                playerPieces.add(new Rook(false,startingRookPosition+7,7));
                //Konie
                playerPieces.add(new Knight(true,startingRookPosition+1,7));
                playerPieces.add(new Knight(true,6+startingRookPosition,7));
                //Gońce
                playerPieces.add(new Bishop(false,2+startingRookPosition,7));
                playerPieces.add(new Bishop(false,5+startingRookPosition,7));
                //Królowa
                playerPieces.add(new Queen(false,4+startingRookPosition,7));
                //Król
                playerPieces.add(new King(false,3+startingRookPosition,7));
                this.playerPieces.add(new ArrayList<>());
                break;

            //Potem doda się pozostałe casy do ataku w dół i w góre jak sie to uda
        }
    }*/

    enum AttackDirection {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }
}