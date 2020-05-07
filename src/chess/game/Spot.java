package chess.game;

import chess.pieces.Piece;
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Spot extends JTextField {

    private Piece piece = null;
    private int x;
    private int y;

    public Spot(int x, int y) {
        setX(x);
        setY(y);
    }

    public synchronized void setColor(Color c) {
        this.setBackground(c);
    }

    public synchronized void setPiece(Piece p) {
        if(p!=null){
            this.piece = p;
            this.setText(p.getPieceIcon());
            this.setForeground(p.getColor());
            this.setFont(new Font("Name",this.getFont().getStyle(),this.getWidth()));
            this.revalidate();
        }else{
            this.piece = null;
            this.setText(null);
            this.revalidate();
        }
    }

    public synchronized Piece getPiece() {
        return piece;
    }

    public synchronized Color getColor() {
        return this.getBackground();
    }

    public synchronized void setX(int x) {
        this.x = x;
    }

    public synchronized int getX(){return this.x;}

    public synchronized void setY(int y) {
        this.y = y;
    }

    public synchronized int getY(){return this.y;}
}
