package chess.game;

import chess.ImageDrawPanel;
import chess.pieces.Piece;
import java.awt.*;

@SuppressWarnings("serial")
public class Spot extends ImageDrawPanel {

    private Piece piece = null;
    private int x;
    private int y;

    public Spot(int x, int y) {
        super();
        setX(x);
        setY(y);
        if((x%2+y%2)==0)
            this.setBackground(Color.WHITE);
        else
            this.setBackground(Color.BLACK);
    }

    public synchronized void setColor(Color c) {
        this.setBackground(c);
    }

    public synchronized void setPiece(Piece p) {
        if(p!=null){
            this.piece = p;
        }else{
            this.piece = null;
        }
        this.revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(piece!=null) {
            Image image=resizeImage(this.getWidth(), this.getHeight(), piece.getPieceIcon(), this.getColor(), piece.getColor());
            g.drawImage(image, (this.getWidth()-image.getWidth(null))/2, (this.getHeight()-image.getHeight(null))/2, this);
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
