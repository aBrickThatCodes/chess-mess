package chess.game;

import chess.Config;
import chess.ImageDrawPanel;
import chess.pieces.Piece;
import java.awt.*;

import javax.swing.JTextField;
import java.awt.image.*;

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
        BufferedImage imageChanged=image.getSubimage(0, 0, image.getWidth(), image.getHeight());     
        Graphics2D g2d=(Graphics2D)image.getGraphics();
        if(imageColor==Color.BLACK) {
            imageChanged=replaceColor(imageChanged, Color.WHITE, Color.YELLOW);
            imageChanged=replaceColor(imageChanged, Color.BLACK, Color.WHITE);
            imageChanged=replaceColor(imageChanged, Color.YELLOW, Color.BLACK);
        }
        else if(imageColor!=null);
            imageChanged=replaceColor(imageChanged, Color.WHITE, imageColor);

        if(bgColor!=null)
            imageChanged=replaceColor(imageChanged, Config.backGroundColor, bgColor);
            
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(imageChanged,0,0,image.getWidth(this),image.getHeight(this),null);
        g2d.dispose();

        Image resized=image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        return resized;
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
