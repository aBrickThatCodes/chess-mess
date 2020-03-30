package chess.game;

import chess.pieces.*;

public class Board {
    Spot[][] board;

    public Board(){
        this.setBoard();
    }

    public void setBoard(){
        board = new Spot[7][7];
            //Pierwszy rzad
        board[0][0] = new Spot(new Rook(false));
        board[1][0] = new Spot(new Knight(true));
        board[2][0] = new Spot(new Bishop(false));
        board[3][0] = new Spot(new Queen(false));
        board[4][0] = new Spot(new King(false));
        board[5][0] = new Spot(new Bishop(false));
        board[6][0] = new Spot(new Knight(true));
        board[7][0] = new Spot(new Rook(false));
            //Drugi rzad
        board[0][1] = new Spot(new Pawn(true));
        board[1][1] = new Spot(new Pawn(true));
        board[2][1] = new Spot(new Pawn(true));
        board[3][1] = new Spot(new Pawn(true));
        board[4][1] = new Spot(new Pawn(true));
        board[5][1] = new Spot(new Pawn(true));
        board[6][1] = new Spot(new Pawn(true));
        board[7][1] = new Spot(new Pawn(true));
            //Pierwszy przeciwny
        board[0][6] = new Spot(new Pawn(true));
        board[1][6] = new Spot(new Pawn(true));
        board[2][6] = new Spot(new Pawn(true));
        board[3][6] = new Spot(new Pawn(true));
        board[4][6] = new Spot(new Pawn(true));
        board[5][6] = new Spot(new Pawn(true));
        board[6][6] = new Spot(new Pawn(true));
        board[7][6] = new Spot(new Pawn(true));
        //Drugi przeciwny
        board[0][7] = new Spot(new Rook(false));
        board[1][7] = new Spot(new Knight(true));
        board[2][7] = new Spot(new Bishop(false));
        board[3][7] = new Spot(new King(false));
        board[4][7] = new Spot(new Queen(false));
        board[5][7] = new Spot(new Bishop(false));
        board[6][7] = new Spot(new Knight(true));
        board[7][7] = new Spot(new Rook(false));
    }

    public void setBoard(int boardHeight,int boardWidth){
        //tutaj trzeba będzie pomuśleć na jakimś forem może który by spoty inicjował
        //zastanawiam się też czy nie lepiej będzie jak najpierw zrobi się pole bez figury i potem dopiero się figurę wstawi bo tak chyba będzie prościej je potem przestawiać
    }

}
