package chess.launcher;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
class TitlePanel extends JPanel {
    private BufferedImage rawImage;
    public TitlePanel() {
        try {
            String rawPath=this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
            rawImage=ImageIO.read(new File(rawPath.substring("file:/".length(),rawPath.length()-"ChessMess.jar".length())+"resources/titleImage.png"));
            
        }
        catch(IOException exception) {
            this.add(new JLabel("There were problems with loading this image"));
        }
    }
    
    BufferedImage scaleImage(BufferedImage raw) {
        BufferedImage scaled=new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) scaled.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(raw, 0, 0,this.getWidth(),this.getHeight(),null);
        g2.dispose();
        return scaled;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(scaleImage(rawImage), 0, 0, this);
    }
}