package chess.game;

import chess.Config;
import chess.pieces.Piece;
import chess.pieces.Rook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Test{

    private ArrayList<ArrayList<Spot>> board = new ArrayList<>();
    private ArrayList<Spot> spots0 = new ArrayList<>();
    private ArrayList<Spot> spots1 = new ArrayList<>();
    private ArrayList<Player> players;
    private Player currentTurn;
    private int currentX;
    private int currentY;
    private Piece currentChosenPiece = null;
    Config config;

public class MyMouseListener implements MouseListener {
    private int x;
    private int y;

    public MyMouseListener(int x,int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int previusX = currentX;
        currentX = x;

        int previusY = currentY;
        currentY = y;

        if(currentChosenPiece != null){
            System.out.println(currentChosenPiece.getPieceIcon());
            if(currentChosenPiece.move(currentY,currentX)){
                System.out.println("Pion przestawiono "+ currentX + " "+ currentY);
                board.get(y).get(x).setPiece(currentChosenPiece);
                board.get(previusY).get(previusX).setPiece(null);
                currentChosenPiece = null;
            }
            else if (currentChosenPiece.move(currentY,currentX)){
                System.out.println("Poza możliwościami pionka");
            }
        }else{
            try{
                currentChosenPiece = board.get(y).get(x).getPiece();
                System.out.println("current " + currentX + " " + currentY);
                System.out.println("previous " + previusX + " " + previusY);

            } catch (Exception e) {
                System.out.println("Brak pionka");
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        /*currentX = x;
        currentChosenPiece = spots.get(x).getPiece();*/
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        /*if(currentChosenPiece != null){
            if(currentChosenPiece.move(currentX,0)){
                System.out.println("Pion przestawiono "+ currentX + " "+ 0);
                currentChosenPiece = null;
            }
            else if (currentChosenPiece.move(currentX,0)){
                System.out.println("Poza możliwościami pionka");
            }
        }else{
            currentChosenPiece = spots.get(n).getPiece();
            try{
                System.out.println(currentChosenPiece.getPieceIcon());
            } catch (Exception e) {
                System.out.println("Brak pionka");
            }
        }*/
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}


    Test(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(640,640);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(2,3));

        Spot spot = new Spot(0,0);
        Spot spot1 = new Spot(1,0);
        Spot spot2 = new Spot(2,0);

        Spot spot3 = new Spot(0,1);
        Spot spot4 = new Spot(1,1);
        Spot spot5 = new Spot(2,1);

        spots0.add(spot);
        spots0.add(spot1);
        spots0.add(spot2);

        spots1.add(spot3);
        spots1.add(spot4);
        spots1.add(spot5);

        spot.setColor(Color.BLACK);
        spot.addMouseListener(new MyMouseListener(0,0));

        spot1.setColor(Color.WHITE);
        spot1.addMouseListener(new MyMouseListener(1,0));

        spot2.setColor(Color.BLACK);
        spot2.addMouseListener(new MyMouseListener(2,0));

        spot3.setColor(Color.WHITE);
        spot3.addMouseListener(new MyMouseListener(0,1));

        spot4.setColor(Color.black);
        spot4.addMouseListener(new MyMouseListener(1,1));

        spot5.setColor(Color.white);
        spot5.addMouseListener(new MyMouseListener(2,1));

        board.add(spots0);
        board.add(spots1);

        board.get(0).get(0).setPiece(new Rook());

        for(ArrayList<Spot> s: board) for(Spot s2: s) frame.add(s2);

    }
    public static void main(String[] args){
        new Test();
    }
}
