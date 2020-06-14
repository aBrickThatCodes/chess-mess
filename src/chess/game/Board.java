package chess.game;

import chess.Config;
import chess.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class Board extends JPanel {

    public Spot[][] board;

    public Board() {
        setLayout(new GridLayout(Config.Instance().boardWidth, Config.Instance().boardHeight));
    }

    public synchronized void repaintColors() {
        if (Config.Instance().boardWidth == Config.Instance().boardHeight) {
            for (int j = 0; j < Config.Instance().boardWidth; j++) {
                    for (int k = 0; k < Config.Instance().boardHeight; k++) {
                        board[k][j].setColor(Config.Instance().colors[(k+j)%2]);
                    }
            }
        } else {
            for (int j = 0; j < Config.Instance().boardWidth; j++) {
                if (j % 2 == 0) {
                    for (int k = 0; k < Config.Instance().boardHeight; k++) {
                        if (k % 2 == 0) {
                            board[j][k].setColor(Color.WHITE);
                        } else {
                            board[j][k].setColor(Color.BLACK);
                        }
                    }
                } else if (j % 2 == 1) {
                    for (int k = 0; k < Config.Instance().boardHeight; k++) {
                        if (k % 2 == 0) {
                            board[j][k].setColor(Color.BLACK);
                        } else {
                            board[j][k].setColor(Color.WHITE);
                        }
                    }
                }
            }
        }
    }

    public synchronized void setBoard(ArrayList<Player> players) {

        board = new Spot[Config.Instance().boardWidth][Config.Instance().boardHeight];

        //Wypełnianie planszy polami
        for (int i = 0; i < Config.Instance().boardWidth; i++) {
            for (int j = 0; j < Config.Instance().boardHeight; j++) {
                if (board[i][j] == null) {
                    board[i][j] = new Spot(i, j);
                }
            }
        }

        //Ustawienie pionków
        for (Player player : players) {
            if (player.attackDirection == Player.AttackDirection.LEFT || player.attackDirection == Player.AttackDirection.RIGHT) {
                for (ArrayList<Piece> pieceRow : player.playerPieces) {
                    for (Piece piece : pieceRow) {
                        piece.setColor(player.getPlayerColor());
                        board[piece.getX()][piece.getY()].setPiece(piece);
                    }
                }
            }else{
                for (ArrayList<Piece> pieceRow : player.playerPieces) {
                    for (Piece piece : pieceRow) {
                        piece.setColor(player.getPlayerColor());
                        board[piece.getX()][piece.getY()].setPiece(piece);
                    }
                }
            }
        }

        
        //region Random stuff
        for(int i=0;i<Config.Instance().maxPlacements;i++) {
            if(Config.Instance().randFields)
                randomFreeSpot().isTeleporting=true;
            if(Config.Instance().obstacles)
                randomFreeSpot().isBlocked=true;
            if(Config.Instance().items)
                randomFreeSpot().item=new Random().nextInt(2)+1;
        }
        //endregion
        
        /*for (int i = 0; i < players.size(); i++) {
            switch (players.get(i).attackDirection) {
                case RIGHT:
                    int startingRookPosision = (board[0].length - 8) / 2;

                    //Pionki - de facto kolejne grupy figur ustawiane na podstawie na razie nie aktywnej metody ustawiającej z config
                    for (int j = 0; j < 8; j++) {
                        board[1][startingRookPosision + j].setPiece(players.get(i).playerPieces.get(0).get(j));
                        players.get(i).playerPieces.get(0).get(j).setLocation(1, startingRookPosision + j);
                    }

                    //Wieże, Gońce, Konie
                    for (int j = 0; j < 4; j++) {
                        board[0][startingRookPosision + j].setPiece(players.get(i).playerPieces.get(j + 1).get(0));
                        players.get(i).playerPieces.get(1).get(0).setLocation(0, startingRookPosision + j);

                        board[0][startingRookPosision + 7 - j].setPiece(players.get(i).playerPieces.get(j + 1).get(1));
                        players.get(i).playerPieces.get(1).get(0).setLocation(0, startingRookPosision + 7 - j);
                    }

                    //Królowa
                    board[0][startingRookPosision + 4].setPiece(players.get(i).playerPieces.get(5).get(0));
                    players.get(i).playerPieces.get(1).get(0).setLocation(0, startingRookPosision + 4);

                    //Król
                    board[0][startingRookPosision + 5].setPiece(players.get(i).playerPieces.get(6).get(0));
                    players.get(i).playerPieces.get(1).get(0).setLocation(0, startingRookPosision + 5);
                    break;

                case LEFT:
                    startingRookPosision = (board[board.length].length - 8) / 2;

                    //Pionki
                    for (int j = 0; j < 8; j++) {
                        board[board.length - 1][startingRookPosision + j].setPiece(players.get(i).playerPieces.get(0).get(j));
                        players.get(i).playerPieces.get(0).get(j).setLocation(board.length - 1, startingRookPosision + j);
                    }

                    //Wieże, Gońce, Konie
                    for (int j = 0; j < 4; j++) {
                        board[board.length][startingRookPosision + j].setPiece(players.get(i).playerPieces.get(j + 1).get(0));
                        players.get(i).playerPieces.get(1).get(0).setLocation(board.length, startingRookPosision + j);

                        board[board.length][startingRookPosision + 7 - j].setPiece(players.get(i).playerPieces.get(j + 1).get(1));
                        players.get(i).playerPieces.get(1).get(0).setLocation(board.length, startingRookPosision + 7 - j);
                    }

                    //Królowa
                    board[board.length][startingRookPosision + 5].setPiece(players.get(i).playerPieces.get(5).get(0));
                    players.get(i).playerPieces.get(1).get(0).setLocation(board.length, startingRookPosision + 5);

                    //Król
                    board[board.length][startingRookPosision + 4].setPiece(players.get(i).playerPieces.get(6).get(0));
                    players.get(i).playerPieces.get(1).get(0).setLocation(board.length, startingRookPosision + 4);
                    break;
                case UP:
                    startingRookPosision = board.length;

                    //Pionki
                    for (int j = 0; j < 8; j++) {
                        board[j][startingRookPosision - 1].setPiece(players.get(i).playerPieces.get(0).get(j));
                        players.get(i).playerPieces.get(0).get(j).setLocation(j, startingRookPosision);
                    }

                    //Wieże, Gońce, Konie
                    for (int j = 0; j < 4; j++) {
                        board[j][startingRookPosision].setPiece(players.get(i).playerPieces.get(j + 1).get(0));
                        players.get(i).playerPieces.get(1).get(0).setLocation(j, startingRookPosision);

                        board[7 - j][startingRookPosision].setPiece(players.get(i).playerPieces.get(j + 1).get(1));
                        players.get(i).playerPieces.get(1).get(0).setLocation(7 - j, startingRookPosision);
                    }

                    //Królowa
                    board[5][startingRookPosision].setPiece(players.get(i).playerPieces.get(5).get(0));
                    players.get(i).playerPieces.get(1).get(0).setLocation(5, startingRookPosision);

                    //Król
                    board[4][startingRookPosision].setPiece(players.get(i).playerPieces.get(6).get(0));
                    players.get(i).playerPieces.get(1).get(0).setLocation(4, startingRookPosision);
                    break;
                case DOWN:
                    startingRookPosision = 0;

                    //Pionki
                    for (int j = 0; j < 8; j++) {
                        board[j][startingRookPosision + 1].setPiece(players.get(i).playerPieces.get(0).get(j));
                        players.get(i).playerPieces.get(0).get(j).setLocation(j, startingRookPosision);
                    }

                    //Wieże, Gońce, Konie
                    for (int j = 0; j < 4; j++) {
                        board[j][startingRookPosision].setPiece(players.get(i).playerPieces.get(j + 1).get(0));
                        players.get(i).playerPieces.get(1).get(0).setLocation(j, startingRookPosision);

                        board[7 - j][startingRookPosision].setPiece(players.get(i).playerPieces.get(j + 1).get(1));
                        players.get(i).playerPieces.get(1).get(0).setLocation(7 - j, startingRookPosision);
                    }

                    //Królowa
                    board[4][startingRookPosision].setPiece(players.get(i).playerPieces.get(5).get(0));
                    players.get(i).playerPieces.get(1).get(0).setLocation(4, startingRookPosision);

                    //Król
                    board[5][startingRookPosision].setPiece(players.get(i).playerPieces.get(6).get(0));
                    players.get(i).playerPieces.get(1).get(0).setLocation(5, startingRookPosision);
                    break;
            }
        }*/

        for (int i = 0; i < Config.Instance().boardWidth; i++) {
            for (int j = 0; j < Config.Instance().boardHeight; j++) {
                board[i][j].revalidate();
                this.add(board[i][j]);
            }
        }


        this.revalidate();

        //Kolorowanie
        repaintColors();
        refreshBoard();
    }

    public Spot[][] getBoard() {
        return board;
    }

    public synchronized void refreshBoard() {
        for (int j = 0; j < Config.Instance().boardWidth; j++) {
            if (j % 2 == 0) {
                for (int k = 0; k < Config.Instance().boardHeight; k++) {
                    if (k % 2 == 0) {
                        board[j][k].setColor(Color.BLACK);
                    } else {
                        board[j][k].setColor(Color.WHITE);
                    }
                }
            } else for (int k = 0; k < Config.Instance().boardHeight; k++) {
                if (k % 2 == 0) {
                    board[j][k].setColor(Color.WHITE);
                } else {
                    board[j][k].setColor(Color.BLACK);
                }
            }
        }

        repaintColors();
        this.revalidate();
    }

    public Spot randomFreeSpot() {
        Spot spot;
        do {
            Random random=new Random();
            spot=board[random.nextInt(Config.Instance().boardWidth)][random.nextInt(Config.Instance().boardHeight)];
        } while(spot.getPiece()!=null || spot.isBlocked || spot.isTeleporting);
        return spot;
    }

    public void useItem(Spot spot) {
        if(spot.item==1) {
            Spot s;
            do {
                Random random=new Random();
                s=board[random.nextInt(Config.Instance().boardWidth)][random.nextInt(Config.Instance().boardHeight)];
            } while(s.getPiece()==null || s.getPiece().getColor()==spot.getPiece().getColor());
            s.setPiece(null);
        }
        else if(spot.item==2) {
            int newPiece=new Random().nextInt(5);
            Piece p;
            switch(newPiece) {
                case 0:
                    p=new Pawn():
                    //configure it
                    break;
                case 1:
                    p=new Rook();
                    //configure it
                    break;
                case 2:
                    p=new Knight();
                    //configure it
                    break;
                case 3:
                    p=new Bishop();
                    //configure it
                    break;
                case 4:
                    p=new Queen();
                    //configure it
                    break;
            }
            p.setColor(spot.getPiece().getColor());
            spot.setPiece(p);
        }
    }
}