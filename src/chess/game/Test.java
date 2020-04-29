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

    private ArrayList<Spot> spots = new ArrayList<>();
    private ArrayList<Player> players;
    private Player currentTurn;
    private int currentX;
    private int currentY;
    private Piece currentChosenPiece = null;
    Config config;

public class MyMouseListener implements MouseListener {
    private int x;
    private  int previusX;

    public MyMouseListener(int x,int n){
        this.x = x;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        previusX = currentX;
        currentX = x;

        if(currentChosenPiece != null){
            System.out.println(currentChosenPiece.getPieceIcon());
            if(currentChosenPiece.move(currentX,0)){
                System.out.println("Pion przestawiono "+ currentX + " "+ 0);
                spots.get(x).setPiece(currentChosenPiece);
                spots.get(previusX).setPiece(null);
                currentChosenPiece = null;
            }
            else if (currentChosenPiece.move(currentX,0)){
                System.out.println("Poza możliwościami pionka");
            }
        }else{
            try{
                currentChosenPiece = spots.get(x).getPiece();
                System.out.println(currentChosenPiece.getPieceIcon());
                System.out.println("tu jestem");
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
        frame.setLayout(new GridLayout(1,2));

        Spot spot = new Spot(0,0);
        Spot spot1 = new Spot(1,0);

        spots.add(spot);
        spots.add(spot1);

        spot.setColor(Color.BLACK);
        spot.addMouseListener(new MyMouseListener(0,1));

        spot1.setColor(Color.WHITE);
        spot1.addMouseListener(new MyMouseListener(1,0));
        spot.setPiece(new Rook());

        frame.add(spot);
        frame.add(spot1);
    }
    public static void main(String[] args){
        new Test();
    }
}
