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

    public void useItem(Spot spot,Player player) {
        if(spot.item==1) {
            Spot s;
            while(true) {
                Random random=new Random();
                s=board[random.nextInt(Config.Instance().boardWidth)][random.nextInt(Config.Instance().boardHeight)];
                if(s.getPiece()!=null)
                    if(s.getPiece().getColor()!=spot.getPiece().getColor());
                        break;
            }
            s.setPiece(null);
        }
        
        else if(spot.item==2) {
            int newPiece=new Random().nextInt(5);
            Piece p;
            switch(newPiece) {
                case 0:
                    p=new Pawn(player.attackDirection);
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
                default:
                    throw new IllegalStateException("Unexpected value: " + newPiece);
            }
            p.setColor(spot.getPiece().getColor());
            spot.setPiece(p);
            p.setLocation(spot.getX(),spot.getY());
            board[spot.getX()][spot.getY()].setPiece(p);
        }
        spot.item=0;
    }
}