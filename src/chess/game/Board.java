package chess.game;


import chess.Config;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Board extends JPanel {

    private Spot[][] board;
    private GameStatus status;

    public Board(ArrayList<Player> players){
        this.setLayout(new GridLayout(Config.Instance().boardHeight,Config.Instance().boardWidth));
        //setBoard(players);
        status = GameStatus.ACTIVE;
    }

    public synchronized void setBoard(ArrayList<Player> players){

        board = new Spot[Config.Instance().boardWidth][Config.Instance().boardHeight];

        for(int i = 0; i< players.size() ; i++){

            switch (players.get(i).attackDirection){
                case RIGHT:
                    int startingRookPosision = (board[0].length-8)/2;

                    //Pionki - de facto kolejne grupy figur ustawiane na podstawie na razie nie aktywnej metody ustawiającej z config
                    for(int j=0;j<8;j++){
                        board[1][startingRookPosision+j].setPiece(players.get(i).playerPieces.get(0).get(j));
                        players.get(i).playerPieces.get(0).get(j).setLoctaion(1,startingRookPosision+j);
                    }

                    //Wieże, Gońce, Konie
                    for(int j=0;j<4;j++){
                        board[0][startingRookPosision+j].setPiece(players.get(i).playerPieces.get(j + 1).get(0));
                        players.get(i).playerPieces.get(1).get(0).setLoctaion(0,startingRookPosision+j);

                        board[0][startingRookPosision+7-j].setPiece(players.get(i).playerPieces.get(j + 1).get(1));
                        players.get(i).playerPieces.get(1).get(0).setLoctaion(0,startingRookPosision+7-j);
                    }

                    //Królowa
                    board[0][startingRookPosision+4].setPiece(players.get(i).playerPieces.get(5).get(0));
                    players.get(i).playerPieces.get(1).get(0).setLoctaion(0,startingRookPosision+4);

                    //Król
                    board[0][startingRookPosision+5].setPiece(players.get(i).playerPieces.get(6).get(0));
                    players.get(i).playerPieces.get(1).get(0).setLoctaion(0,startingRookPosision+5);
                    break;

                case LEFT:
                    startingRookPosision = (board[board.length].length-8)/2;

                    //Pionki
                    for(int j=0;j<8;j++){
                        board[board.length-1][startingRookPosision+j].setPiece(players.get(i).playerPieces.get(0).get(j));
                        players.get(i).playerPieces.get(0).get(j).setLoctaion(board.length-1,startingRookPosision+j);
                    }

                    //Wieże, Gońce, Konie
                    for(int j=0;j<4;j++){
                        board[board.length][startingRookPosision+j].setPiece(players.get(i).playerPieces.get(j + 1).get(0));
                        players.get(i).playerPieces.get(1).get(0).setLoctaion(board.length,startingRookPosision+j);

                        board[board.length][startingRookPosision+7-j].setPiece(players.get(i).playerPieces.get(j + 1).get(1));
                        players.get(i).playerPieces.get(1).get(0).setLoctaion(board.length,startingRookPosision+7-j);
                    }

                    //Królowa
                    board[board.length][startingRookPosision+5].setPiece(players.get(i).playerPieces.get(5).get(0));
                    players.get(i).playerPieces.get(1).get(0).setLoctaion(board.length,startingRookPosision+5);

                    //Król
                    board[board.length][startingRookPosision+4].setPiece(players.get(i).playerPieces.get(6).get(0));
                    players.get(i).playerPieces.get(1).get(0).setLoctaion(board.length,startingRookPosision+4);
                    break;
                case UP:
                    startingRookPosision = board.length;

                    //Pionki
                    for(int j=0;j<8;j++){
                        board[j][startingRookPosision-1].setPiece(players.get(i).playerPieces.get(0).get(j));
                        players.get(i).playerPieces.get(0).get(j).setLoctaion(j,startingRookPosision);
                    }

                    //Wieże, Gońce, Konie
                    for(int j=0;j<4;j++){
                        board[j][startingRookPosision].setPiece(players.get(i).playerPieces.get(j + 1).get(0));
                        players.get(i).playerPieces.get(1).get(0).setLoctaion(j,startingRookPosision);

                        board[7-j][startingRookPosision].setPiece(players.get(i).playerPieces.get(j + 1).get(1));
                        players.get(i).playerPieces.get(1).get(0).setLoctaion(7-j,startingRookPosision);
                    }

                    //Królowa
                    board[5][startingRookPosision].setPiece(players.get(i).playerPieces.get(5).get(0));
                    players.get(i).playerPieces.get(1).get(0).setLoctaion(5,startingRookPosision);

                    //Król
                    board[4][startingRookPosision].setPiece(players.get(i).playerPieces.get(6).get(0));
                    players.get(i).playerPieces.get(1).get(0).setLoctaion(4,startingRookPosision);
                    break;
                case DOWN:
                    startingRookPosision = 0;

                    //Pionki
                    for(int j=0;j<8;j++){
                        board[j][startingRookPosision+1].setPiece(players.get(i).playerPieces.get(0).get(j));
                        players.get(i).playerPieces.get(0).get(j).setLoctaion(j,startingRookPosision);
                    }

                    //Wieże, Gońce, Konie
                    for(int j=0;j<4;j++){
                        board[j][startingRookPosision].setPiece(players.get(i).playerPieces.get(j + 1).get(0));
                        players.get(i).playerPieces.get(1).get(0).setLoctaion(j,startingRookPosision);

                        board[7-j][startingRookPosision].setPiece(players.get(i).playerPieces.get(j + 1).get(1));
                        players.get(i).playerPieces.get(1).get(0).setLoctaion(7-j,startingRookPosision);
                    }

                    //Królowa
                    board[4][startingRookPosision].setPiece(players.get(i).playerPieces.get(5).get(0));
                    players.get(i).playerPieces.get(1).get(0).setLoctaion(4,startingRookPosision);

                    //Król
                    board[5][startingRookPosision].setPiece(players.get(i).playerPieces.get(6).get(0));
                    players.get(i).playerPieces.get(1).get(0).setLoctaion(5,startingRookPosision);
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
        refreshBoard();
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
    }

    public enum GameStatus {
        ACTIVE,
        ENDGAME
    }
}