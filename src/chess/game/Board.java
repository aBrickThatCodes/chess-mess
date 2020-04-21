package chess.game;

import chess.pieces.*;

import java.util.ArrayList;

public class Board {

    Spot[][] board;
    ArrayList<ArrayList<Piece>> playersPieces;

    public Board(){
        this.setBoard();
    }

    public void setBoard(){
        board = new Spot[7][7];

        /* Oryginalna metoda na zrobienie planszy
            //Pierwszy rzad
        board[0][0] = new Spot(new Rook(false,0,0));
        board[1][0] = new Spot(new Knight(true,1,0));
        board[2][0] = new Spot(new Bishop(false,2,0));
        board[3][0] = new Spot(new Queen(false,3,0));
        board[4][0] = new Spot(new King(false,4,0));
        board[5][0] = new Spot(new Bishop(false,5,0));
        board[6][0] = new Spot(new Knight(true,6,0));
        board[7][0] = new Spot(new Rook(false,7,0));

            //Drugi rzad
        board[0][1] = new Spot(new Pawn(true,0,1));
        board[1][1] = new Spot(new Pawn(true,1,1));
        board[2][1] = new Spot(new Pawn(true,2,1));
        board[3][1] = new Spot(new Pawn(true,3,1));
        board[4][1] = new Spot(new Pawn(true,4,1));
        board[5][1] = new Spot(new Pawn(true,5,1));
        board[6][1] = new Spot(new Pawn(true,6,1));
        board[7][1] = new Spot(new Pawn(true,7,1));
            //Pierwszy przeciwny
        board[0][6] = new Spot(new Pawn(true,0,6));
        board[1][6] = new Spot(new Pawn(true,1,6));
        board[2][6] = new Spot(new Pawn(true,2,6));
        board[3][6] = new Spot(new Pawn(true,3,6));
        board[4][6] = new Spot(new Pawn(true,4,6));
        board[5][6] = new Spot(new Pawn(true,5,6));
        board[6][6] = new Spot(new Pawn(true,6,6));
        board[7][6] = new Spot(new Pawn(true,7,6));
        //Drugi przeciwny
        board[0][7] = new Spot(new Rook(false,0,7));
        board[1][7] = new Spot(new Knight(true,1,7));
        board[2][7] = new Spot(new Bishop(false,2,7));
        board[3][7] = new Spot(new King(false,3,7));
        board[4][7] = new Spot(new Queen(false,4,7));
        board[5][7] = new Spot(new Bishop(false,5,7));
        board[6][7] = new Spot(new Knight(true,6,7));
        board[7][7] = new Spot(new Rook(false,7,7));

       */

        //Metoda na plansze przez tablice pionków
        playersPieces = new ArrayList<>();

        //Pionki
        for(int i=0;i<8;i++){
            board[i][1].setPiece(playersPieces.get(0).get(i));
            board[i][6].setPiece(playersPieces.get(1).get(i));
        }

        addPlayerPieces(0,AttackDirection.LEFT);
        addPlayerPieces(7,AttackDirection.RIGHT);
        //Wieża
        board[0][0].setPiece(playersPieces.get(0).get(8));
        board[7][0].setPiece(playersPieces.get(0).get(9));

        board[0][7].setPiece(playersPieces.get(1).get(8));
        board[7][7].setPiece(playersPieces.get(1).get(9));

        //Konie
        board[1][0].setPiece(playersPieces.get(0).get(10));
        board[6][0].setPiece(playersPieces.get(0).get(11));

        board[1][7].setPiece(playersPieces.get(1).get(10));
        board[6][7].setPiece(playersPieces.get(1).get(11));

        //Gońce
        board[2][0].setPiece(playersPieces.get(0).get(12));
        board[5][0].setPiece(playersPieces.get(0).get(13));

        board[2][7].setPiece(playersPieces.get(1).get(12));
        board[5][7].setPiece(playersPieces.get(1).get(13));

        //Królowa
        board[3][0].setPiece(playersPieces.get(0).get(14));
        board[4][7].setPiece(playersPieces.get(1).get(14));

        //Król
        board[4][0].setPiece(playersPieces.get(0).get(15));
        board[3][7].setPiece(playersPieces.get(1).get(15));
    }

    public enum AttackDirection {
        LEFT,
        RIGHT,
        DOWN,
        UP
    }

    public synchronized void addPlayerPieces(int startingRookPosition,AttackDirection attackDirection){
        switch (attackDirection){
            case LEFT:
                ArrayList<Piece> playerPieces = new ArrayList<>();

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
                this.playersPieces.add(new ArrayList<>());
                [break;]
            case RIGHT:
                ArrayList<Piece> playerPieces = new ArrayList<>();
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
                this.playersPieces.add(new ArrayList<>());
                [break;]

            //Potem doda się pozostałe casy do ataku w dół i w góre jak sie to uda
        }
    }

    public void setBoard(int boardHeight,int boardWidth){

    }

}
