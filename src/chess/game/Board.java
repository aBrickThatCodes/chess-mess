package chess.game;


import chess.Config;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings({"serial","unused"})
public class Board extends JPanel {

    private Spot[][] board;
    private GameStatus status;

    public Board(ArrayList<Player> players){
        setLayout(new GridLayout(Config.Instance().boardHeight,Config.Instance().boardWidth));
        //setBoard(players);
        //status = GameStatus.ACTIVE;
    }

    /*public synchronized void setBoard(ArrayList<Player> players){

        board = new Spot[Config.Instance().boardWidth][Config.Instance().boardHeight];

        for(int i = 0; i< players.size() ; i++){

            switch (players.get(i).attackDirection){
                case RIGHT:
                    int startingRookPosision = (board[0].length-8)/2;

                    //Pionki
                    for(int j=0;j<8;j++){
                        board[1][startingRookPosision+j].setPiece(players.get(i).playerPieces[0][j]);
                        players.get(i).playerPieces[0][j].setLoctaion(1,startingRookPosision+j);
                    }

                    //Wieże, Gońce, Konie
                    for(int j=0;j<4;j++){
                        board[0][startingRookPosision+j].setPiece(players.get(i).playerPieces[j+1][0]);
                        players.get(i).playerPieces[1][0].setLoctaion(0,startingRookPosision+j);

                        board[0][startingRookPosision+7-j].setPiece(players.get(i).playerPieces[j+1][1]);
                        players.get(i).playerPieces[1][0].setLoctaion(0,startingRookPosision+7-j);
                    }

                    //Królowa
                    board[0][startingRookPosision+4].setPiece(players.get(i).playerPieces[5][0]);
                    players.get(i).playerPieces[1][0].setLoctaion(0,startingRookPosision+4);

                    //Król
                    board[0][startingRookPosision+5].setPiece(players.get(i).playerPieces[6][0]);
                    players.get(i).playerPieces[1][0].setLoctaion(0,startingRookPosision+5);
                    break;

                case LEFT:
                    startingRookPosision = (board[board.length].length-8)/2;

                    //Pionki
                    for(int j=0;j<8;j++){
                        board[board.length-1][startingRookPosision+j].setPiece(players.get(i).playerPieces[0][j]);
                        players.get(i).playerPieces[0][j].setLoctaion(board.length-1,startingRookPosision+j);
                    }

                    //Wieże, Gońce, Konie
                    for(int j=0;j<4;j++){
                        board[board.length][startingRookPosision+j].setPiece(players.get(i).playerPieces[j+1][0]);
                        players.get(i).playerPieces[1][0].setLoctaion(board.length,startingRookPosision+j);

                        board[board.length][startingRookPosision+7-j].setPiece(players.get(i).playerPieces[j+1][1]);
                        players.get(i).playerPieces[1][0].setLoctaion(board.length,startingRookPosision+7-j);
                    }

                    //Królowa
                    board[board.length][startingRookPosision+5].setPiece(players.get(i).playerPieces[5][0]);
                    players.get(i).playerPieces[1][0].setLoctaion(board.length,startingRookPosision+5);

                    //Król
                    board[board.length][startingRookPosision+4].setPiece(players.get(i).playerPieces[6][0]);
                    players.get(i).playerPieces[1][0].setLoctaion(board.length,startingRookPosision+4);
                    break;
                case UP:

                    break;
                case DOWN:

                    break;
            }
        }

        //Wypełnianie pozostalych pól
        for(int i = 0; i< Config.Instance().boardWidth ; i++){
            for(int j = 0; j< Config.Instance().boardHeight ; j++){
                if(board[i][j] == null){
                    board[i][j] = new Spot(i,j);
                }
            }
        }

        //Kolorowanie
        for(int i = 0; i<Config.Instance().boardWidth;i++){
            for(int j = 0; j<Config.Instance().boardHeight;j++){
                if (j%2 == 0){
                    for(int k = 0; k<Config.Instance().boardWidth;k++){
                        if (k%2 == 0){
                            board[k][j].setColor(Color.WHITE);
                        }else {
                            board[k][j].setColor(Color.BLACK);
                        }
                    }
                }else {
                    for(int k = 0; k<Config.Instance().boardWidth;k++){
                        if (k%2 == 0){
                            board[k][j].setColor(Color.BLACK);
                        }else {
                            board[k][j].setColor(Color.WHITE);
                        }
                    }
                }

            }
        }

        refreshBoard();

        /*Oryginalna metoda na zrobienie planszy
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

        //Metoda na plansze przez tablice pionków

        //Pionki
        for(int i=0;i<8;i++){
            board[i][1].setPiece(players.get(0).playerPieces.get(0).get(i));
            board[i][6].setPiece(players.get(1).playerPieces.get(0).get(i));
        }

        //Wieża
       /* board[0][0].setPiece(playerPieces.get(0).get(8));
        board[7][0].setPiece(playerPieces.get(0).get(9));

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

    public synchronized void refreshBoard(){

        for(int i = 0; i< Config.Instance().boardWidth ; i++){
            for(int j = 0; j< Config.Instance().boardHeight ; j++){
                this.add(board[j][i]);
            }
        }
        this.revalidate();
    }

    public synchronized void setStatus(GameStatus status){
        this.status = status;
    }

    public synchronized GameStatus getStatus(){
        return this.status;
    }

    public synchronized Spot[][] getBoard(){
        return this.board;
    }*/

    public enum GameStatus {
        ACTIVE,
        ENDGAME
    }
}
