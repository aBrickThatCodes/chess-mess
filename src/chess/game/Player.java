package chess.game;

import chess.Config;
import chess.pieces.*;

import java.awt.*;
import java.util.ArrayList;

public abstract class Player {

    public ArrayList<ArrayList<Piece>> playerPieces = new ArrayList<>();
    public AttackDirection attackDirection;
    private int startingRookPosition;
    private Color playerColor;

    public class HumanPlayer extends Player{
        public HumanPlayer(int startingRookPosition, AttackDirection attackDirection,Color playerColor){
            addPlayerPieces(startingRookPosition,attackDirection);
            setStartingRookPosition(startingRookPosition);
            this.setPlayerColor(playerColor);
        }
    }

    public class AIPlayer extends Player{
        AIPlayer(int startingRookPosition, AttackDirection attackDirection,Color playerColor) {
            addPlayerPieces(startingRookPosition,attackDirection);
            setStartingRookPosition(startingRookPosition);
            this.setPlayerColor(playerColor);
        }
    }

    public synchronized void setStartingRookPosition(int startingRookPosition){
        this.startingRookPosition = startingRookPosition;
    }

    public synchronized int getStartingRookPosition(){
        return this.startingRookPosition;
    }

    public synchronized void setPlayerColor(Color color){
        this.playerColor = color;
    }

    public synchronized Color getPlayerColor(){
        return this.playerColor;
    }

    //Tymczasowa metoda dodająca pionki w standardowym ustawieniu
    public synchronized void addPlayerPieces(int startingRookPosition,AttackDirection attackDirection){ //Metoda tworząca podstawowe szachy do testów
        playerPieces = new ArrayList<>();
        switch (attackDirection){
            case LEFT:
                //Pionki - grupa I
                switch (Config.Instance().pieces[0]){
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<Piece>();
                        for(int i=0;i<8;i++){
                            Pawn pawn = new Pawn(attackDirection);
                            pawn.setLocation(1,startingRookPosition+i);
                            pawns.add(pawn);
                        }
                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<Piece>();
                        for(int i=0;i<8;i++){
                            Rook rook = new Rook();
                            rook.setLocation(1,startingRookPosition+i);
                            rooks.add(rook);
                        }
                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> knights = new ArrayList<Piece>();
                        for(int i=0;i<8;i++){
                            Knight knight = new Knight();
                            knight.setLocation(1, startingRookPosition+i);
                            knights.add(knight);
                        }
                        playerPieces.add(knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> bishops = new ArrayList<>();
                        for(int i=0;i<8;i++){
                            Bishop bishop = new Bishop();
                            bishop.setLocation(1, startingRookPosition+i);
                            bishops.add(bishop);
                        }
                        playerPieces.add(bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        for(int i=0;i<8;i++){
                            Queen queen = new Queen();
                            queen.setLocation(1, startingRookPosition+i);
                            queens.add(queen);
                        }
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> kings = new ArrayList<>();
                        for(int i=0;i<8;i++){
                            King king = new King();
                            king.setLocation(1, startingRookPosition+i);
                            kings.add(king);
                        }
                        playerPieces.add(kings);
                        break;
                }
                //Wieże - grupa II
                switch (Config.Instance().pieces[1]){
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(0,startingRookPosition);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(0,startingRookPosition+7);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(0,startingRookPosition);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(0,startingRookPosition+7);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(0,startingRookPosition);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(0,startingRookPosition+7);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(0,startingRookPosition);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(0,startingRookPosition+7);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(0,startingRookPosition);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(0,startingRookPosition+7);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(0,startingRookPosition);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(0,startingRookPosition+7);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Konie - grupa III
                switch (Config.Instance().pieces[2]){
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(0,startingRookPosition+1);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(0,startingRookPosition+6);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(0,startingRookPosition+1);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(0,startingRookPosition+6);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(0,startingRookPosition+1);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(0,startingRookPosition+6);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(0,startingRookPosition+1);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(0,startingRookPosition+6);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(0,startingRookPosition+1);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(0,startingRookPosition+6);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(0,startingRookPosition+1);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(0,startingRookPosition+6);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Gońce - grupa IV
                switch (Config.Instance().pieces[3]){
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(0,startingRookPosition+2);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(0,startingRookPosition+5);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(0,startingRookPosition+2);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(0,startingRookPosition+5);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(0,startingRookPosition+2);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(0,startingRookPosition+5);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(0,startingRookPosition+2);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(0,startingRookPosition+5);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(0,startingRookPosition+2);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(0,startingRookPosition+5);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(0,startingRookPosition+2);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(0,startingRookPosition+5);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Królowa - grupa V
                switch (Config.Instance().pieces[4]){
                    case PAWN:
                        ArrayList<Piece> Pawns = new ArrayList<>();
                        Pawn Pawn = new Pawn(attackDirection);
                        Pawn.setLocation(0,startingRookPosition+3);
                        Pawns.add(Pawn);
                        playerPieces.add(Pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> Rooks = new ArrayList<>();
                        Rook Rook = new Rook();
                        Rook.setLocation(0,startingRookPosition+3);
                        Rooks.add(Rook);
                        playerPieces.add(Rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();
                        Knight Knight = new Knight();
                        Knight.setLocation(0,startingRookPosition+3);
                        Knights.add(Knight);
                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();
                        Bishop Bishop = new Bishop();
                        Bishop.setLocation(0,startingRookPosition+3);
                        Bishops.add(Bishop);
                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        Queen queen = new Queen();
                        queen.setLocation(0,startingRookPosition+3);
                        queens.add(queen);
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();
                        King King = new King();
                        King.setLocation(0,startingRookPosition+3);
                        Kings.add(King);
                        playerPieces.add(Kings);
                        break;
                }
                //Król - grupa VI
                switch (Config.Instance().pieces[5]){
                    case PAWN:
                        ArrayList<Piece> Pawns = new ArrayList<>();
                        Pawn Pawn = new Pawn(attackDirection);
                        Pawn.setLocation(0,startingRookPosition+4);
                        Pawns.add(Pawn);
                        playerPieces.add(Pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> Rooks = new ArrayList<>();
                        Rook Rook = new Rook();
                        Rook.setLocation(0,startingRookPosition+4);
                        Rooks.add(Rook);
                        playerPieces.add(Rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();
                        Knight Knight = new Knight();
                        Knight.setLocation(0,startingRookPosition+4);
                        Knights.add(Knight);
                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();
                        Bishop Bishop = new Bishop();
                        Bishop.setLocation(0,startingRookPosition+4);
                        Bishops.add(Bishop);
                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        Queen queen = new Queen();
                        queen.setLocation(0,startingRookPosition+4);
                        queens.add(queen);
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();
                        King King = new King();
                        King.setLocation(0,startingRookPosition+4);
                        Kings.add(King);
                        playerPieces.add(Kings);
                        break;
                }
                break;
            case RIGHT:
                //Pionki - grupa I
                switch (Config.Instance().pieces[0]){
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<Piece>();
                        for(int i=0;i<8;i++){
                            Pawn pawn = new Pawn(attackDirection);
                            pawn.setLocation(Config.Instance().boardWidth-2,startingRookPosition+i);
                            pawns.add(pawn);
                        }
                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<Piece>();
                        for(int i=0;i<8;i++){
                            Rook rook = new Rook();
                            rook.setLocation(Config.Instance().boardWidth-2,startingRookPosition+i);
                            rooks.add(rook);
                        }
                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> knights = new ArrayList<Piece>();
                        for(int i=0;i<8;i++){
                            Knight knight = new Knight();
                            knight.setLocation(Config.Instance().boardWidth-2, startingRookPosition+i);
                            knights.add(knight);
                        }
                        playerPieces.add(knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> bishops = new ArrayList<>();
                        for(int i=0;i<8;i++){
                            Bishop bishop = new Bishop();
                            bishop.setLocation(Config.Instance().boardWidth-2, startingRookPosition+i);
                            bishops.add(bishop);
                        }
                        playerPieces.add(bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        for(int i=0;i<8;i++){
                            Queen queen = new Queen();
                            queen.setLocation(Config.Instance().boardWidth-2, startingRookPosition+i);
                            queens.add(queen);
                        }
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> kings = new ArrayList<>();
                        for(int i=0;i<8;i++){
                            King king = new King();
                            king.setLocation(Config.Instance().boardWidth-2, startingRookPosition+i);
                            kings.add(king);
                        }
                        playerPieces.add(kings);
                        break;
                }
                //Wieże - grupa II
                switch (Config.Instance().pieces[1]){
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(Config.Instance().boardWidth-1,startingRookPosition);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+7);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(Config.Instance().boardWidth-1,startingRookPosition);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+7);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(Config.Instance().boardWidth-1,startingRookPosition);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+7);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(Config.Instance().boardWidth-1,startingRookPosition);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+7);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(Config.Instance().boardWidth-1,startingRookPosition);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+7);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(Config.Instance().boardWidth-1,startingRookPosition);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+7);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Konie - grupa III
                switch (Config.Instance().pieces[2]){
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(Config.Instance().boardWidth-1,startingRookPosition+1);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+6);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(Config.Instance().boardWidth-1,startingRookPosition+1);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+6);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(Config.Instance().boardWidth-1,startingRookPosition+1);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+6);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(Config.Instance().boardWidth-1,startingRookPosition+1);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+6);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(Config.Instance().boardWidth-1,startingRookPosition+1);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+6);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(Config.Instance().boardWidth-1,startingRookPosition+1);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+6);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Gońce - grupa IV
                switch (Config.Instance().pieces[3]){
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(Config.Instance().boardWidth-1,startingRookPosition+2);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+5);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(Config.Instance().boardWidth-1,startingRookPosition+2);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+5);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(Config.Instance().boardWidth-1,startingRookPosition+2);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+5);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(Config.Instance().boardWidth-1,startingRookPosition+2);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+5);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(Config.Instance().boardWidth-1,startingRookPosition+2);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(0,startingRookPosition+5);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(Config.Instance().boardWidth-1,startingRookPosition+2);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(Config.Instance().boardWidth-1,startingRookPosition+5);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Królowa - grupa V
                switch (Config.Instance().pieces[4]){
                    case PAWN:
                        ArrayList<Piece> Pawns = new ArrayList<>();
                        Pawn Pawn = new Pawn(attackDirection);
                        Pawn.setLocation(Config.Instance().boardWidth-1,startingRookPosition+3);
                        Pawns.add(Pawn);
                        playerPieces.add(Pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> Rooks = new ArrayList<>();
                        Rook Rook = new Rook();
                        Rook.setLocation(Config.Instance().boardWidth-1,startingRookPosition+3);
                        Rooks.add(Rook);
                        playerPieces.add(Rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();
                        Knight Knight = new Knight();
                        Knight.setLocation(Config.Instance().boardWidth-1,startingRookPosition+3);
                        Knights.add(Knight);
                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();
                        Bishop Bishop = new Bishop();
                        Bishop.setLocation(Config.Instance().boardWidth-1,startingRookPosition+3);
                        Bishops.add(Bishop);
                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        Queen queen = new Queen();
                        queen.setLocation(Config.Instance().boardWidth-1,startingRookPosition+3);
                        queens.add(queen);
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();
                        King King = new King();
                        King.setLocation(Config.Instance().boardWidth-1,startingRookPosition+3);
                        Kings.add(King);
                        playerPieces.add(Kings);
                        break;
                }
                //Król - grupa VI
                switch (Config.Instance().pieces[5]){
                    case PAWN:
                        ArrayList<Piece> Pawns = new ArrayList<>();
                        Pawn Pawn = new Pawn(attackDirection);
                        Pawn.setLocation(Config.Instance().boardWidth-1,startingRookPosition+4);
                        Pawns.add(Pawn);
                        playerPieces.add(Pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> Rooks = new ArrayList<>();
                        Rook Rook = new Rook();
                        Rook.setLocation(Config.Instance().boardWidth-1,startingRookPosition+4);
                        Rooks.add(Rook);
                        playerPieces.add(Rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();
                        Knight Knight = new Knight();
                        Knight.setLocation(Config.Instance().boardWidth-1,startingRookPosition+4);
                        Knights.add(Knight);
                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();
                        Bishop Bishop = new Bishop();
                        Bishop.setLocation(Config.Instance().boardWidth-1,startingRookPosition+4);
                        Bishops.add(Bishop);
                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        Queen queen = new Queen();
                        queen.setLocation(Config.Instance().boardWidth-1,startingRookPosition+4);
                        queens.add(queen);
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();
                        King King = new King();
                        King.setLocation(Config.Instance().boardWidth-1,startingRookPosition+4);
                        Kings.add(King);
                        playerPieces.add(Kings);
                        break;
                }
                break;
            case DOWN:
                break;
            case UP:
                break;
        }
    }

    public enum AttackDirection {
        RIGHT,
        LEFT,
        UP,
        DOWN;
    }
}