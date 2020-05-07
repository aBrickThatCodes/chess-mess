package chess.game;

import chess.Config;
import chess.pieces.*;

import java.awt.*;
import java.util.ArrayList;

public abstract class Player {

    public ArrayList<ArrayList<Piece>> playerPieces;
    public AttackDirection attackDirection;
    private int startingRookPosition;
    private Color playerColor;// = Color.yellow;

    public synchronized void setPlayerPieces(int startingRookPosition,AttackDirection attackDirection){
        switch(attackDirection){
            case LEFT:
                //Grup I
                switch (Config.Instance().pieces[0]) {
                    case PAWN:
                        //Pionki
                        ArrayList<Piece> pawns = new ArrayList();

                        for(int i=0;i<8;i++){
                            Pawn pawn = new Pawn(attackDirection);
                            pawn.setLoctaion(1,startingRookPosition+i);
                            pawns.add(pawn);
                        }

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList();

                        for(int i=0;i<8;i++){
                            Rook rook = new Rook();
                            rook.setLoctaion(1,startingRookPosition+i);
                            rooks.add(rook);
                        }

                        playerPieces.add(rooks);
                        break;
                    case BISHOP:
                        ArrayList<Piece> bishops = new ArrayList();

                        for(int i=0;i<8;i++){
                            Bishop bishop = new Bishop();
                            bishop.setLoctaion(1,startingRookPosition+i);
                            bishops.add(bishop);
                        }

                        playerPieces.add(bishops);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> knights = new ArrayList();

                        for(int i=0;i<8;i++){
                            Knight knight = new Knight();
                            knight.setLoctaion(1,startingRookPosition+i);
                            knights.add(knight);
                        }

                        playerPieces.add(knights);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList();

                        for(int i=0;i<8;i++){
                            Queen queen = new Queen();
                            queen.setLoctaion(1,startingRookPosition+i);
                            queens.add(queen);
                        }

                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> kings = new ArrayList();

                        for(int i=0;i<8;i++){
                            King king = new King();
                            king.setLoctaion(1,startingRookPosition+i);
                            kings.add(king);
                        }

                        playerPieces.add(kings);
                        break;
                }
                //Other groups apart for spots with queen and king
                for(int i = 1; i<4;i++){
                    ArrayList<Piece> pieces = new ArrayList<>();
                    switch (Config.Instance().pieces[i]){
                        case PAWN:

                            Pawn pawn = new Pawn(attackDirection);
                            pawn.setLoctaion(0,startingRookPosition+i);
                            pieces.add(pawn);

                            pawn = new Pawn(attackDirection);
                            pawn.setLoctaion(0,startingRookPosition+7-i);
                            pieces.add(pawn);

                            break;

                        case KING:
                            King king = new King();
                            king.setLoctaion(0,startingRookPosition+i);
                            pieces.add(king);

                            king = new King();
                            king.setLoctaion(0,startingRookPosition+7-i);
                            pieces.add(king);

                            break;
                        case KNIGHT:
                            Knight knight = new Knight();
                            knight.setLoctaion(0,startingRookPosition+i);
                            pieces.add(knight);

                            knight = new Knight();
                            knight.setLoctaion(0,startingRookPosition+7-i);
                            pieces.add(knight);

                            break;
                        case BISHOP:
                            Bishop Bishop = new Bishop();
                            Bishop.setLoctaion(0,startingRookPosition+i);
                            pieces.add(Bishop);

                            Bishop = new Bishop();
                            Bishop.setLoctaion(0,startingRookPosition+7-i);
                            pieces.add(Bishop);

                            break;
                        case QUEEN:
                            Queen Queen = new Queen();
                            Queen.setLoctaion(0,startingRookPosition+i);
                            pieces.add(Queen);

                            Queen = new Queen();
                            Queen.setLoctaion(0,startingRookPosition+7-i);
                            pieces.add(Queen);

                            break;
                        case ROOK:
                            Rook Rook = new Rook();
                            Rook.setLoctaion(0,startingRookPosition+i);
                            pieces.add(Rook);

                            Rook = new Rook();
                            Rook.setLoctaion(0,startingRookPosition+7-i);
                            pieces.add(Rook);

                            break;
                    }
                    playerPieces.add(pieces);
                }
                //Queen spot
                ArrayList<Piece> pieces = new ArrayList();
                switch (Config.Instance().pieces[4]){
                    case PAWN:

                        Pawn pawn = new Pawn(attackDirection);
                        pawn.setLoctaion(0,startingRookPosition+3);
                        pieces.add(pawn);
                        break;

                    case KING:
                        King king = new King();
                        king.setLoctaion(0,startingRookPosition+3);
                        pieces.add(king);
                        break;
                    case KNIGHT:
                        Knight knight = new Knight();
                        knight.setLoctaion(0,startingRookPosition+3);
                        pieces.add(knight);
                        break;
                    case BISHOP:
                        Bishop Bishop = new Bishop();
                        Bishop.setLoctaion(0,startingRookPosition+3);
                        pieces.add(Bishop);
                        break;
                    case QUEEN:
                        Queen Queen = new Queen();
                        Queen.setLoctaion(0,startingRookPosition+3);
                        pieces.add(Queen);
                        break;
                    case ROOK:
                        Rook Rook = new Rook();
                        Rook.setLoctaion(0,startingRookPosition+3);
                        pieces.add(Rook);
                        break;
                }
                playerPieces.add(pieces);
                //King spot
                pieces = new ArrayList();
                switch (Config.Instance().pieces[5]){
                    case PAWN:

                        Pawn pawn = new Pawn(attackDirection);
                        pawn.setLoctaion(0,startingRookPosition+4);
                        pieces.add(pawn);
                        break;

                    case KING:
                        King king = new King();
                        king.setLoctaion(0,startingRookPosition+4);
                        pieces.add(king);
                        break;
                    case KNIGHT:
                        Knight knight = new Knight();
                        knight.setLoctaion(0,startingRookPosition+4);
                        pieces.add(knight);
                        break;
                    case BISHOP:
                        Bishop Bishop = new Bishop();
                        Bishop.setLoctaion(0,startingRookPosition+4);
                        pieces.add(Bishop);
                        break;
                    case QUEEN:
                        Queen Queen = new Queen();
                        Queen.setLoctaion(0,startingRookPosition+4);
                        pieces.add(Queen);
                        break;
                    case ROOK:
                        Rook Rook = new Rook();
                        Rook.setLoctaion(0,startingRookPosition+4);
                        pieces.add(Rook);
                        break;
                }
                playerPieces.add(pieces);

            case RIGHT:

                //Grup I
                switch (Config.Instance().pieces[0]) {
                    case PAWN:
                        //Pionki
                        ArrayList<Piece> pawns = new ArrayList<Piece>();

                        for(int i=Config.Instance().boardWidth-2;i<8;i++){
                            Pawn pawn = new Pawn(attackDirection);
                            pawn.setLoctaion(Config.Instance().boardWidth-2,startingRookPosition+i);
                            pawns.add(pawn);
                        }

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<Piece>();

                        for(int i=Config.Instance().boardWidth-2;i<8;i++){
                            Rook rook = new Rook();
                            rook.setLoctaion(Config.Instance().boardWidth-2,startingRookPosition+i);
                            rooks.add(rook);
                        }

                        playerPieces.add(rooks);
                        break;
                    case BISHOP:
                        ArrayList<Piece> bishops = new ArrayList<Piece>();

                        for(int i=Config.Instance().boardWidth-2;i<8;i++){
                            Bishop bishop = new Bishop();
                            bishop.setLoctaion(Config.Instance().boardWidth-2,startingRookPosition+i);
                            bishops.add(bishop);
                        }

                        playerPieces.add(bishops);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> knights = new ArrayList<Piece>();

                        for(int i=Config.Instance().boardWidth-2;i<8;i++){
                            Knight knight = new Knight();
                            knight.setLoctaion(Config.Instance().boardWidth-2,startingRookPosition+i);
                            knights.add(knight);
                        }

                        playerPieces.add(knights);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<Piece>();

                        for(int i=Config.Instance().boardWidth-2;i<8;i++){
                            Queen queen = new Queen();
                            queen.setLoctaion(Config.Instance().boardWidth-2,startingRookPosition+i);
                            queens.add(queen);
                        }

                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> kings = new ArrayList<Piece>();

                        for(int i=Config.Instance().boardWidth-2;i<8;i++){
                            King king = new King();
                            king.setLoctaion(Config.Instance().boardWidth-2,startingRookPosition+i);
                            kings.add(king);
                        }

                        playerPieces.add(kings);
                        break;
                }
                //Other groups apart for spots with queen and king
                for(int i = 1; i<4;i++){
                    pieces = new ArrayList<>();
                    switch (Config.Instance().pieces[i]){
                        case PAWN:

                            Pawn pawn = new Pawn(attackDirection);
                            pawn.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+i);
                            pieces.add(pawn);

                            pawn = new Pawn(attackDirection);
                            pawn.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+7-i);
                            pieces.add(pawn);

                            break;

                        case KING:
                            King king = new King();
                            king.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+i);
                            pieces.add(king);

                            king = new King();
                            king.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+7-i);
                            pieces.add(king);

                            break;
                        case KNIGHT:
                            Knight knight = new Knight();
                            knight.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+i);
                            pieces.add(knight);

                            knight = new Knight();
                            knight.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+7-i);
                            pieces.add(knight);

                            break;
                        case BISHOP:
                            Bishop Bishop = new Bishop();
                            Bishop.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+i);
                            pieces.add(Bishop);

                            Bishop = new Bishop();
                            Bishop.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+7-i);
                            pieces.add(Bishop);

                            break;
                        case QUEEN:
                            Queen Queen = new Queen();
                            Queen.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+i);
                            pieces.add(Queen);

                            Queen = new Queen();
                            Queen.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+7-i);
                            pieces.add(Queen);

                            break;
                        case ROOK:
                            Rook Rook = new Rook();
                            Rook.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+i);
                            pieces.add(Rook);

                            Rook = new Rook();
                            Rook.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+7-i);
                            pieces.add(Rook);

                            break;
                    }
                    playerPieces.add(pieces);
                }
                //Queen spot
                pieces = new ArrayList<Piece>();
                switch (Config.Instance().pieces[4]){
                    case PAWN:

                        Pawn pawn = new Pawn(attackDirection);
                        pawn.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+4);
                        pieces.add(pawn);
                        break;

                    case KING:
                        King king = new King();
                        king.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+4);
                        pieces.add(king);
                        break;
                    case KNIGHT:
                        Knight knight = new Knight();
                        knight.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+4);
                        pieces.add(knight);
                        break;
                    case BISHOP:
                        Bishop Bishop = new Bishop();
                        Bishop.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+4);
                        pieces.add(Bishop);
                        break;
                    case QUEEN:
                        Queen Queen = new Queen();
                        Queen.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+4);
                        pieces.add(Queen);
                        break;
                    case ROOK:
                        Rook Rook = new Rook();
                        Rook.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+4);
                        pieces.add(Rook);
                        break;
                }
                playerPieces.add(pieces);
                //King spot
                pieces = new ArrayList<Piece>();
                switch (Config.Instance().pieces[5]){
                    case PAWN:

                        Pawn pawn = new Pawn(attackDirection);
                        pawn.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+3);
                        pieces.add(pawn);
                        break;

                    case KING:
                        King king = new King();
                        king.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+3);
                        pieces.add(king);
                        break;
                    case KNIGHT:
                        Knight knight = new Knight();
                        knight.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+3);
                        pieces.add(knight);
                        break;
                    case BISHOP:
                        Bishop Bishop = new Bishop();
                        Bishop.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+3);
                        pieces.add(Bishop);
                        break;
                    case QUEEN:
                        Queen Queen = new Queen();
                        Queen.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+3);
                        pieces.add(Queen);
                        break;
                    case ROOK:
                        Rook Rook = new Rook();
                        Rook.setLoctaion(Config.Instance().boardWidth-1,startingRookPosition+3);
                        pieces.add(Rook);
                        break;
                }
                playerPieces.add(pieces);
            case DOWN:
                break;
            case UP:
                break;
        }
    }

    public static class HumanPlayer extends Player{

        public HumanPlayer(int startingRookPosition, AttackDirection attackDirection,Color playerColor){
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

    public static class AIPlayer extends Player{
        AIPlayer() {
            //setPlayerPieces();
        }
    }

    public synchronized void addPlayerPieces(int startingRookPosition,AttackDirection attackDirection){ //Metoda tworząca podstawowe szachy do testów

        playerPieces = new ArrayList<>();
        switch (attackDirection){
            case LEFT:
                //Pionki
                ArrayList<Piece> pawns = new ArrayList<Piece>();
                for(int i=0;i<8;i++){
                    Pawn pawn = new Pawn(attackDirection);
                    pawn.setLoctaion(1,startingRookPosition+i);
                    pawns.add(pawn);
                }
                playerPieces.add(pawns);
                //Wieża
                ArrayList<Piece> rooks = new ArrayList<>();

                Rook rook1 = new Rook();
                rook1.setLoctaion(0,startingRookPosition);
                rooks.add(rook1);

                Rook rook2 = new Rook();
                rook2.setLoctaion(0,startingRookPosition+7);
                rooks.add(rook2);

                playerPieces.add(rooks);

                //Konie
                ArrayList<Piece> knights = new ArrayList<>();

                Knight knight1 = new Knight();
                knight1.setLoctaion(0,startingRookPosition+1);
                knights.add(knight1);

                Knight knight2 = new Knight();
                knight2.setLoctaion(0,startingRookPosition+6);
                knights.add(knight2);

                playerPieces.add(knights);
                //Gońce
                ArrayList<Piece> bishops = new ArrayList<>();

                Bishop bishop1 = new Bishop();
                bishop1.setLoctaion(0,startingRookPosition+2);
                bishops.add(bishop1);

                Bishop bishop2 = new Bishop();
                bishop2.setLoctaion(0,startingRookPosition+5);
                bishops.add(bishop2);

                playerPieces.add(bishops);

                //Królowa
                ArrayList<Piece> queens = new ArrayList<>();
                Queen queen = new Queen();
                queen.setLoctaion(0,startingRookPosition+3);
                queens.add(queen);
                playerPieces.add(queens);
                //Król
                ArrayList<Piece> kings = new ArrayList<>();
                King king = new King();
                king.setLoctaion(0,startingRookPosition+4);
                kings.add(king);
                playerPieces.add(kings);
                break;
            case RIGHT:
                //Pionki
                pawns = new ArrayList<Piece>();

                for(int i=0;i<8;i++){
                    Pawn pawn = new Pawn(attackDirection);
                    pawn.setLoctaion(6,startingRookPosition+i);
                    pawns.add(pawn);
                }
                playerPieces.add(pawns);
                //Wieża
                rooks = new ArrayList<>();

                Rook rook6 = new Rook();
                rook6.setLoctaion(7,startingRookPosition);
                rooks.add(rook6);

                rook2 = new Rook();
                rook2.setLoctaion(7,startingRookPosition+7);
                rooks.add(rook2);

                playerPieces.add(rooks);

                //Konie
                knights = new ArrayList<>();

                Knight knight6 = new Knight();
                knight6.setLoctaion(7,startingRookPosition+1);
                knights.add(knight6);

                knight2 = new Knight();
                knight2.setLoctaion(7,startingRookPosition+6);
                knights.add(knight2);

                playerPieces.add(knights);
                //Gońce
                 bishops = new ArrayList<>();

                Bishop bishop6 = new Bishop();
                bishop6.setLoctaion(7,startingRookPosition+2);
                bishops.add(bishop6);

                bishop2 = new Bishop();
                bishop2.setLoctaion(7,startingRookPosition+5);
                bishops.add(bishop2);

                playerPieces.add(bishops);

                //Królowa
                queens = new ArrayList<>();

                queen = new Queen();
                queen.setLoctaion(7,startingRookPosition+4);
                queens.add(queen);

                playerPieces.add(queens);
                //Król
                kings = new ArrayList<>();

                king = new King();
                king.setLoctaion(7,startingRookPosition+3);
                kings.add(king);

                playerPieces.add(kings);
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
        DOWN
    }
}