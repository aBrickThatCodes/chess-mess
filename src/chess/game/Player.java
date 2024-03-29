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

    public static class HumanPlayer extends Player {
        public HumanPlayer(int startingRookPosition, AttackDirection attackDirection, Color playerColor) {
            setStartingRookPosition(startingRookPosition);
            this.setPlayerColor(playerColor);
            addPlayerPieces(attackDirection);
        }
    }

    public static class AIPlayer extends Player {
        AIPlayer(int startingRookPosition, AttackDirection attackDirection, Color playerColor) {
            setStartingRookPosition(startingRookPosition);
            this.setPlayerColor(playerColor);
            addPlayerPieces(attackDirection);
        }
    }

    public synchronized void setStartingRookPosition(int startingRookPosition) {
        this.startingRookPosition = startingRookPosition;
    }

    public synchronized int getStartingRookPosition() {
        return this.startingRookPosition;
    }

    public synchronized void setPlayerColor(Color color) {
        this.playerColor = color;
    }

    public synchronized Color getPlayerColor() {
        return this.playerColor;
    }

    //Tymczasowa metoda dodająca pionki w standardowym ustawieniu
    public synchronized void addPlayerPieces(AttackDirection attackDirection) { //Metoda tworząca podstawowe szachy do testów
        playerPieces = new ArrayList<>();
        int middleLeft = 0;
        int middleRight = Config.Instance().boardWidth;
        int startingRookPosition = (Config.Instance().boardHeight - 8) / 2;
        switch (attackDirection) {
            case LEFT:
                //Pionki - grupa I
                switch (Config.Instance().pieces[0]) {
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<Piece>();
                        for (int i = 0; i < 8; i++) {
                            Pawn pawn = new Pawn(attackDirection);
                            pawn.setLocation(middleLeft + 1, startingRookPosition + i);
                            pawns.add(pawn);
                        }
                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<Piece>();
                        for (int i = 0; i < 8; i++) {
                            Rook rook = new Rook();
                            rook.setLocation(middleLeft + 1, startingRookPosition + i);
                            rooks.add(rook);
                        }
                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> knights = new ArrayList<Piece>();
                        for (int i = 0; i < 8; i++) {
                            Knight knight = new Knight();
                            knight.setLocation(middleLeft + 1, startingRookPosition + i);
                            knights.add(knight);
                        }
                        playerPieces.add(knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> bishops = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Bishop bishop = new Bishop();
                            bishop.setLocation(middleLeft + 1, startingRookPosition + i);
                            bishops.add(bishop);
                        }
                        playerPieces.add(bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Queen queen = new Queen();
                            queen.setLocation(middleLeft + 1, startingRookPosition + i);
                            queens.add(queen);
                        }
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> kings = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            King king = new King();
                            king.setLocation(middleLeft + 1, startingRookPosition + i);
                            kings.add(king);
                        }
                        playerPieces.add(kings);
                        break;
                }
                //Wieże - grupa II
                switch (Config.Instance().pieces[1]) {
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(middleLeft, startingRookPosition);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(middleLeft, startingRookPosition + 7);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(middleLeft, startingRookPosition);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(middleLeft, startingRookPosition + 7);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(middleLeft, startingRookPosition);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(middleLeft, startingRookPosition + 7);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(middleLeft, startingRookPosition);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(middleLeft, startingRookPosition + 7);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(middleLeft, startingRookPosition);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(middleLeft, startingRookPosition + 7);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(middleLeft, startingRookPosition);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(middleLeft, startingRookPosition + 7);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Konie - grupa III
                switch (Config.Instance().pieces[2]) {
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(middleLeft, startingRookPosition + 1);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(middleLeft, startingRookPosition + 6);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(middleLeft, startingRookPosition + 1);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(middleLeft, startingRookPosition + 6);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(middleLeft, startingRookPosition + 1);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(middleLeft, startingRookPosition + 6);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(middleLeft, startingRookPosition + 1);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(middleLeft, startingRookPosition + 6);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(middleLeft, startingRookPosition + 1);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(middleLeft, startingRookPosition + 6);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(middleLeft, startingRookPosition + 1);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(middleLeft, startingRookPosition + 6);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Gońce - grupa IV
                switch (Config.Instance().pieces[3]) {
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(middleLeft, startingRookPosition + 2);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(middleLeft, startingRookPosition + 5);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(middleLeft, startingRookPosition + 2);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(middleLeft, startingRookPosition + 5);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(middleLeft, startingRookPosition + 2);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(middleLeft, startingRookPosition + 5);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(middleLeft, startingRookPosition + 2);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(middleLeft, startingRookPosition + 5);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(middleLeft, startingRookPosition + 2);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(0, startingRookPosition + 5);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(middleLeft, startingRookPosition + 2);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(middleLeft, startingRookPosition + 5);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Królowa - grupa V
                switch (Config.Instance().pieces[4]) {
                    case PAWN:
                        ArrayList<Piece> Pawns = new ArrayList<>();
                        Pawn Pawn = new Pawn(attackDirection);
                        Pawn.setLocation(middleLeft, startingRookPosition + 3);
                        Pawns.add(Pawn);
                        playerPieces.add(Pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> Rooks = new ArrayList<>();
                        Rook Rook = new Rook();
                        Rook.setLocation(middleLeft, startingRookPosition + 3);
                        Rooks.add(Rook);
                        playerPieces.add(Rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();
                        Knight Knight = new Knight();
                        Knight.setLocation(middleLeft, startingRookPosition + 3);
                        Knights.add(Knight);
                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();
                        Bishop Bishop = new Bishop();
                        Bishop.setLocation(middleLeft, startingRookPosition + 3);
                        Bishops.add(Bishop);
                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        Queen queen = new Queen();
                        queen.setLocation(middleLeft, startingRookPosition + 3);
                        queens.add(queen);
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();
                        King King = new King();
                        King.setLocation(middleLeft, startingRookPosition + 3);
                        Kings.add(King);
                        playerPieces.add(Kings);
                        break;
                }
                //Król - grupa VI
                switch (Config.Instance().pieces[5]) {
                    case PAWN:
                        ArrayList<Piece> Pawns = new ArrayList<>();
                        Pawn Pawn = new Pawn(attackDirection);
                        Pawn.setLocation(middleLeft, startingRookPosition + 4);
                        Pawns.add(Pawn);
                        playerPieces.add(Pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> Rooks = new ArrayList<>();
                        Rook Rook = new Rook();
                        Rook.setLocation(middleLeft, startingRookPosition + 4);
                        Rooks.add(Rook);
                        playerPieces.add(Rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();
                        Knight Knight = new Knight();
                        Knight.setLocation(middleLeft, startingRookPosition + 4);
                        Knights.add(Knight);
                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();
                        Bishop Bishop = new Bishop();
                        Bishop.setLocation(middleLeft, startingRookPosition + 4);
                        Bishops.add(Bishop);
                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        Queen queen = new Queen();
                        queen.setLocation(middleLeft, startingRookPosition + 4);
                        queens.add(queen);
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();
                        King King = new King();
                        King.setLocation(middleLeft, startingRookPosition + 4);
                        Kings.add(King);
                        playerPieces.add(Kings);
                        break;
                }
                break;
            case RIGHT:
                //Pionki - grupa I
                switch (Config.Instance().pieces[0]) {
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Pawn pawn = new Pawn(attackDirection);
                            pawn.setLocation(middleRight - 2, startingRookPosition + i);
                            pawns.add(pawn);
                        }
                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Rook rook = new Rook();
                            rook.setLocation(middleRight - 2, startingRookPosition + i);
                            rooks.add(rook);
                        }
                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> knights = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Knight knight = new Knight();
                            knight.setLocation(middleRight - 2, startingRookPosition + i);
                            knights.add(knight);
                        }
                        playerPieces.add(knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> bishops = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Bishop bishop = new Bishop();
                            bishop.setLocation(middleRight - 2, startingRookPosition + i);
                            bishops.add(bishop);
                        }
                        playerPieces.add(bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Queen queen = new Queen();
                            queen.setLocation(middleRight - 2, startingRookPosition + i);
                            queens.add(queen);
                        }
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> kings = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            King king = new King();
                            king.setLocation(middleRight - 2, startingRookPosition + i);
                            kings.add(king);
                        }
                        playerPieces.add(kings);
                        break;
                }
                //Wieże - grupa II
                switch (Config.Instance().pieces[1]) {
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(middleRight - 1, startingRookPosition);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(middleRight - 1, startingRookPosition + 7);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(middleRight - 1, startingRookPosition);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(middleRight - 1, startingRookPosition + 7);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(middleRight - 1, startingRookPosition);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(middleRight - 1, startingRookPosition + 7);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(middleRight - 1, startingRookPosition);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(middleRight - 1, startingRookPosition + 7);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(middleRight - 1, startingRookPosition);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(middleRight - 1, startingRookPosition + 7);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(middleRight - 1, startingRookPosition);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(middleRight - 1, startingRookPosition + 7);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Konie - grupa III
                switch (Config.Instance().pieces[2]) {
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(middleRight - 1, startingRookPosition + 1);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(middleRight - 1, startingRookPosition + 6);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(middleRight - 1, startingRookPosition + 1);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(middleRight - 1, startingRookPosition + 6);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(middleRight - 1, startingRookPosition + 1);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(middleRight - 1, startingRookPosition + 6);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(middleRight - 1, startingRookPosition + 1);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(middleRight - 1, startingRookPosition + 6);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(middleRight - 1, startingRookPosition + 1);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(middleRight - 1, startingRookPosition + 6);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(middleRight - 1, startingRookPosition + 1);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(middleRight - 1, startingRookPosition + 6);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Gońce - grupa IV
                switch (Config.Instance().pieces[3]) {
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(middleRight - 1, startingRookPosition + 2);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(middleRight - 1, startingRookPosition + 5);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(middleRight - 1, startingRookPosition + 2);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(middleRight - 1, startingRookPosition + 5);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(middleRight - 1, startingRookPosition + 2);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(middleRight - 1, startingRookPosition + 5);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(middleRight - 1, startingRookPosition + 2);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(middleRight - 1, startingRookPosition + 5);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(middleRight - 1, startingRookPosition + 2);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(0, startingRookPosition + 5);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(middleRight - 1, startingRookPosition + 2);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(middleRight - 1, startingRookPosition + 5);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Królowa - grupa V
                switch (Config.Instance().pieces[4]) {
                    case PAWN:
                        ArrayList<Piece> Pawns = new ArrayList<>();
                        Pawn Pawn = new Pawn(attackDirection);
                        Pawn.setLocation(middleRight - 1, startingRookPosition + 3);
                        Pawns.add(Pawn);
                        playerPieces.add(Pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> Rooks = new ArrayList<>();
                        Rook Rook = new Rook();
                        Rook.setLocation(middleRight - 1, startingRookPosition + 3);
                        Rooks.add(Rook);
                        playerPieces.add(Rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();
                        Knight Knight = new Knight();
                        Knight.setLocation(middleRight - 1, startingRookPosition + 3);
                        Knights.add(Knight);
                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();
                        Bishop Bishop = new Bishop();
                        Bishop.setLocation(middleRight - 1, startingRookPosition + 3);
                        Bishops.add(Bishop);
                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        Queen queen = new Queen();
                        queen.setLocation(middleRight - 1, startingRookPosition + 3);
                        queens.add(queen);
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();
                        King King = new King();
                        King.setLocation(middleRight - 1, startingRookPosition + 3);
                        Kings.add(King);
                        playerPieces.add(Kings);
                        break;
                }
                //Król - grupa VI
                switch (Config.Instance().pieces[5]) {
                    case PAWN:
                        ArrayList<Piece> Pawns = new ArrayList<>();
                        Pawn Pawn = new Pawn(attackDirection);
                        Pawn.setLocation(middleRight - 1, startingRookPosition + 4);
                        Pawns.add(Pawn);
                        playerPieces.add(Pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> Rooks = new ArrayList<>();
                        Rook Rook = new Rook();
                        Rook.setLocation(middleRight - 1, startingRookPosition + 4);
                        Rooks.add(Rook);
                        playerPieces.add(Rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();
                        Knight Knight = new Knight();
                        Knight.setLocation(middleRight - 1, startingRookPosition + 4);
                        Knights.add(Knight);
                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();
                        Bishop Bishop = new Bishop();
                        Bishop.setLocation(middleRight - 1, startingRookPosition + 4);
                        Bishops.add(Bishop);
                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        Queen queen = new Queen();
                        queen.setLocation(middleRight - 1, startingRookPosition + 4);
                        queens.add(queen);
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();
                        King King = new King();
                        King.setLocation(middleRight - 1, startingRookPosition + 4);
                        Kings.add(King);
                        playerPieces.add(Kings);
                        break;
                }
                break;
            case UP:
                //Pionki - grupa I
                switch (Config.Instance().pieces[0]) {
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Pawn pawn = new Pawn(attackDirection);
                            pawn.setLocation(startingRookPosition + i, middleRight - 2);
                            pawns.add(pawn);
                        }
                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Rook rook = new Rook();
                            rook.setLocation(startingRookPosition + i, middleRight - 2);
                            rooks.add(rook);
                        }
                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> knights = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Knight knight = new Knight();
                            knight.setLocation(startingRookPosition + i, middleRight - 2);
                            knights.add(knight);
                        }
                        playerPieces.add(knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> bishops = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Bishop bishop = new Bishop();
                            bishop.setLocation(startingRookPosition + i, middleRight - 2);
                            bishops.add(bishop);
                        }
                        playerPieces.add(bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Queen queen = new Queen();
                            queen.setLocation(startingRookPosition + i, middleRight - 2);
                            queens.add(queen);
                        }
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> kings = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            King king = new King();
                            king.setLocation(startingRookPosition + i, middleRight - 2);
                            kings.add(king);
                        }
                        playerPieces.add(kings);
                        break;
                }
                //Wieże - grupa II
                switch (Config.Instance().pieces[1]) {
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(startingRookPosition, middleRight - 1);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(startingRookPosition + 7, middleRight - 1);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(startingRookPosition, middleRight - 1);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(startingRookPosition + 7, middleRight - 1);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(startingRookPosition, middleRight - 1);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(startingRookPosition + 7, middleRight - 1);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(startingRookPosition, middleRight - 1);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(startingRookPosition + 7, middleRight - 1);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(startingRookPosition, middleRight - 1);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(startingRookPosition + 7, middleRight - 1);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(startingRookPosition, middleRight - 1);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(startingRookPosition + 7, middleRight - 1);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Konie - grupa III
                switch (Config.Instance().pieces[2]) {
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(startingRookPosition + 1, middleRight - 1);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(startingRookPosition + 6, middleRight - 1);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(startingRookPosition + 1, middleRight - 1);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(startingRookPosition + 6, middleRight - 1);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(startingRookPosition + 1, middleRight - 1);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(startingRookPosition + 6, middleRight - 1);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(startingRookPosition + 1, middleRight - 1);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(startingRookPosition + 6, middleRight - 1);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(startingRookPosition + 1, middleRight - 1);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(startingRookPosition + 6, middleRight - 1);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(startingRookPosition + 1, middleRight - 1);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(startingRookPosition + 6, middleRight - 1);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Gońce - grupa IV
                switch (Config.Instance().pieces[3]) {
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(startingRookPosition + 2, middleRight - 1);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(startingRookPosition + 5, middleRight - 1);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(startingRookPosition + 2, middleRight - 1);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(startingRookPosition + 5, middleRight - 1);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(startingRookPosition + 2, middleRight - 1);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(startingRookPosition + 5, middleRight - 1);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(startingRookPosition + 2, middleRight - 1);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(startingRookPosition + 5, middleRight - 1);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(startingRookPosition + 2, middleRight - 1);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(0, startingRookPosition + 5);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(startingRookPosition + 2, middleRight - 1);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(startingRookPosition + 5, middleRight - 1);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Królowa - grupa V
                switch (Config.Instance().pieces[4]) {
                    case PAWN:
                        ArrayList<Piece> Pawns = new ArrayList<>();
                        Pawn Pawn = new Pawn(attackDirection);
                        Pawn.setLocation(startingRookPosition + 3, middleRight - 1);
                        Pawns.add(Pawn);
                        playerPieces.add(Pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> Rooks = new ArrayList<>();
                        Rook Rook = new Rook();
                        Rook.setLocation(startingRookPosition + 3, middleRight - 1);
                        Rooks.add(Rook);
                        playerPieces.add(Rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();
                        Knight Knight = new Knight();
                        Knight.setLocation(startingRookPosition + 3, middleRight - 1);
                        Knights.add(Knight);
                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();
                        Bishop Bishop = new Bishop();
                        Bishop.setLocation(startingRookPosition + 3, middleRight - 1);
                        Bishops.add(Bishop);
                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        Queen queen = new Queen();
                        queen.setLocation(startingRookPosition + 3, middleRight - 1);
                        queens.add(queen);
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();
                        King King = new King();
                        King.setLocation(startingRookPosition + 3, middleRight - 1);
                        Kings.add(King);
                        playerPieces.add(Kings);
                        break;
                }
                //Król - grupa VI
                switch (Config.Instance().pieces[5]) {
                    case PAWN:
                        ArrayList<Piece> Pawns = new ArrayList<>();
                        Pawn Pawn = new Pawn(attackDirection);
                        Pawn.setLocation(startingRookPosition + 4, middleRight - 1);
                        Pawns.add(Pawn);
                        playerPieces.add(Pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> Rooks = new ArrayList<>();
                        Rook Rook = new Rook();
                        Rook.setLocation(startingRookPosition + 4, middleRight - 1);
                        Rooks.add(Rook);
                        playerPieces.add(Rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();
                        Knight Knight = new Knight();
                        Knight.setLocation(startingRookPosition + 4, middleRight - 1);
                        Knights.add(Knight);
                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();
                        Bishop Bishop = new Bishop();
                        Bishop.setLocation(startingRookPosition + 4, middleRight - 1);
                        Bishops.add(Bishop);
                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        Queen queen = new Queen();
                        queen.setLocation(startingRookPosition + 4, middleRight - 1);
                        queens.add(queen);
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();
                        King King = new King();
                        King.setLocation(startingRookPosition + 4, middleRight - 1);
                        Kings.add(King);
                        playerPieces.add(Kings);
                        break;
                }
                break;
            case DOWN:
                //Pionki - grupa I
                switch (Config.Instance().pieces[0]) {
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Pawn pawn = new Pawn(attackDirection);
                            pawn.setLocation(startingRookPosition + i, middleLeft + 1);
                            pawns.add(pawn);
                        }
                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Rook rook = new Rook();
                            rook.setLocation(startingRookPosition + i, middleLeft + 1);
                            rooks.add(rook);
                        }
                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> knights = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Knight knight = new Knight();
                            knight.setLocation(startingRookPosition + i, middleLeft + 1);
                            knights.add(knight);
                        }
                        playerPieces.add(knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> bishops = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Bishop bishop = new Bishop();
                            bishop.setLocation(startingRookPosition + i, middleLeft + 1);
                            bishops.add(bishop);
                        }
                        playerPieces.add(bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            Queen queen = new Queen();
                            queen.setLocation(startingRookPosition + i, middleLeft + 1);
                            queens.add(queen);
                        }
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> kings = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            King king = new King();
                            king.setLocation(startingRookPosition + i, middleLeft + 1);
                            kings.add(king);
                        }
                        playerPieces.add(kings);
                        break;
                }
                //Wieże - grupa II
                switch (Config.Instance().pieces[1]) {
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(startingRookPosition, middleLeft);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(startingRookPosition + 7, middleLeft);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(startingRookPosition, middleLeft);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(startingRookPosition + 7, middleLeft);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(startingRookPosition, middleLeft);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(startingRookPosition + 7, middleLeft);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(startingRookPosition, middleLeft);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(startingRookPosition + 7, middleLeft);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(startingRookPosition, middleLeft);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(startingRookPosition + 7, middleLeft);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(startingRookPosition, middleLeft);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(startingRookPosition + 7, middleLeft);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Konie - grupa III
                switch (Config.Instance().pieces[2]) {
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(startingRookPosition + 1, middleLeft);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(startingRookPosition + 6, middleLeft);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(startingRookPosition + 1, middleLeft);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(startingRookPosition + 6, middleLeft);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(startingRookPosition + 1, middleLeft);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(startingRookPosition + 6, middleLeft);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(startingRookPosition + 1, middleLeft);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(startingRookPosition + 6, middleLeft);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(startingRookPosition + 1, middleLeft);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(startingRookPosition + 6, middleLeft);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(startingRookPosition + 1, middleLeft);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(startingRookPosition + 6, middleLeft);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Gońce - grupa IV
                switch (Config.Instance().pieces[3]) {
                    case PAWN:
                        ArrayList<Piece> pawns = new ArrayList<>();

                        Pawn pawn1 = new Pawn(attackDirection);
                        pawn1.setLocation(startingRookPosition + 2, middleLeft);
                        pawns.add(pawn1);

                        Pawn Pawn2 = new Pawn(attackDirection);
                        Pawn2.setLocation(startingRookPosition + 5, middleLeft);
                        pawns.add(Pawn2);

                        playerPieces.add(pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> rooks = new ArrayList<>();

                        Rook rook1 = new Rook();
                        rook1.setLocation(startingRookPosition + 2, middleLeft);
                        rooks.add(rook1);

                        Rook rook2 = new Rook();
                        rook2.setLocation(startingRookPosition + 5, middleLeft);
                        rooks.add(rook2);

                        playerPieces.add(rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();

                        Knight Knight1 = new Knight();
                        Knight1.setLocation(startingRookPosition + 2, middleLeft);
                        Knights.add(Knight1);

                        Knight Knight2 = new Knight();
                        Knight2.setLocation(startingRookPosition + 5, middleLeft);
                        Knights.add(Knight2);

                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();

                        Bishop Bishop1 = new Bishop();
                        Bishop1.setLocation(startingRookPosition + 2, middleLeft);
                        Bishops.add(Bishop1);

                        Bishop Bishop2 = new Bishop();
                        Bishop2.setLocation(startingRookPosition + 5, middleLeft);
                        Bishops.add(Bishop2);

                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> Queens = new ArrayList<>();

                        Queen Queen1 = new Queen();
                        Queen1.setLocation(startingRookPosition + 2, middleLeft);
                        Queens.add(Queen1);

                        Queen Queen2 = new Queen();
                        Queen2.setLocation(0, startingRookPosition + 5);
                        Queens.add(Queen2);

                        playerPieces.add(Queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();

                        King King1 = new King();
                        King1.setLocation(startingRookPosition + 2, middleLeft);
                        Kings.add(King1);

                        King King2 = new King();
                        King2.setLocation(startingRookPosition + 5, middleLeft);
                        Kings.add(King2);

                        playerPieces.add(Kings);
                        break;
                }
                //Królowa - grupa V
                switch (Config.Instance().pieces[4]) {
                    case PAWN:
                        ArrayList<Piece> Pawns = new ArrayList<>();
                        Pawn Pawn = new Pawn(attackDirection);
                        Pawn.setLocation(startingRookPosition + 3, middleLeft);
                        Pawns.add(Pawn);
                        playerPieces.add(Pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> Rooks = new ArrayList<>();
                        Rook Rook = new Rook();
                        Rook.setLocation(startingRookPosition + 3, middleLeft);
                        Rooks.add(Rook);
                        playerPieces.add(Rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();
                        Knight Knight = new Knight();
                        Knight.setLocation(startingRookPosition + 3, middleLeft);
                        Knights.add(Knight);
                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();
                        Bishop Bishop = new Bishop();
                        Bishop.setLocation(startingRookPosition + 3, middleLeft);
                        Bishops.add(Bishop);
                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        Queen queen = new Queen();
                        queen.setLocation(startingRookPosition + 3, middleLeft);
                        queens.add(queen);
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();
                        King King = new King();
                        King.setLocation(startingRookPosition + 3, middleLeft);
                        Kings.add(King);
                        playerPieces.add(Kings);
                        break;
                }
                //Król - grupa VI
                switch (Config.Instance().pieces[5]) {
                    case PAWN:
                        ArrayList<Piece> Pawns = new ArrayList<>();
                        Pawn Pawn = new Pawn(attackDirection);
                        Pawn.setLocation(startingRookPosition + 4, middleLeft);
                        Pawns.add(Pawn);
                        playerPieces.add(Pawns);
                        break;
                    case ROOK:
                        ArrayList<Piece> Rooks = new ArrayList<>();
                        Rook Rook = new Rook();
                        Rook.setLocation(startingRookPosition + 4, middleLeft);
                        Rooks.add(Rook);
                        playerPieces.add(Rooks);
                        break;
                    case KNIGHT:
                        ArrayList<Piece> Knights = new ArrayList<>();
                        Knight Knight = new Knight();
                        Knight.setLocation(startingRookPosition + 4, middleLeft);
                        Knights.add(Knight);
                        playerPieces.add(Knights);
                        break;
                    case BISHOP:
                        ArrayList<Piece> Bishops = new ArrayList<>();
                        Bishop Bishop = new Bishop();
                        Bishop.setLocation(startingRookPosition + 4, middleLeft);
                        Bishops.add(Bishop);
                        playerPieces.add(Bishops);
                        break;
                    case QUEEN:
                        ArrayList<Piece> queens = new ArrayList<>();
                        Queen queen = new Queen();
                        queen.setLocation(startingRookPosition + 4, middleLeft);
                        queens.add(queen);
                        playerPieces.add(queens);
                        break;
                    case KING:
                        ArrayList<Piece> Kings = new ArrayList<>();
                        King King = new King();
                        King.setLocation(startingRookPosition + 4, middleLeft);
                        Kings.add(King);
                        playerPieces.add(Kings);
                        break;
                }
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