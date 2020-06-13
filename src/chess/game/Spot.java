package chess.game;

import chess.Config;
import chess.pieces.Piece;
import java.awt.*;

import javax.swing.JTextField;
import java.awt.image.*;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Spot extends JTextField {

    private Piece piece = null;
    private int x;
    private int y;

    public Spot(int x, int y) {
        super();
        setX(x);
        setY(y);
        this.setEditable(false);
    }

    public synchronized void setColor(Color c) {
        this.setBackground(c);
    }

    public synchronized void setPiece(Piece p) {
        this.piece = p;
        this.revalidate();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(piece!=null) {
            Image image=resizeImage(this.getWidth(), this.getHeight(), piece.getPieceIcon(), new Color(this.getColor().getRGB()), piece.getColor());
            g.drawImage(image, 0, 0, null);
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

    protected Image resizeImage(int width, int height, BufferedImage image, Color bgColor, Color imageColor) {
        BufferedImage imageChanged=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
        for(int x=0;x<image.getWidth();x++) {
            for(int y=0;y<image.getHeight();y++) {
                imageChanged.setRGB(x, y, image.getRGB(x,y));
            }
        }

        if(imageColor==Color.BLACK) {
            imageChanged=replaceColor(imageChanged, Color.WHITE, Color.YELLOW);
            imageChanged=replaceColor(imageChanged, Color.BLACK, Color.WHITE);
            imageChanged=replaceColor(imageChanged, Color.YELLOW, Color.BLACK);
        }
        else if(imageColor!=null) {
            imageChanged=replaceColor(imageChanged, Color.WHITE, imageColor);
        }

        if(bgColor!=null) {
            imageChanged=replaceColor(imageChanged, Config.backGroundColor, bgColor);
        }

        return imageChanged.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    protected static BufferedImage replaceColor(BufferedImage img, Color toReplace, Color replacer) {
        BufferedImage imageChanged=img.getSubimage(0, 0, img.getWidth(), img.getHeight());
        for (int x = 0; x < imageChanged.getWidth(); x++) {
            for (int y = 0; y < imageChanged.getHeight(); y++) {
                Color color = new Color(imageChanged.getRGB(x, y));
                if(color.getRGB()==toReplace.getRGB())
                    imageChanged.setRGB(x, y, replacer.getRGB());
            }
        }
        return imageChanged;
    }
}
