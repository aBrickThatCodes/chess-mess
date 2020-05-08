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
    String path;
    public TitlePanel() {
        path=this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        if(path.endsWith(".jar")) {
            try {
                path=this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
                path=path.substring("file:/".length(),path.length()-"ChessMess.jar".length())+"resources/titleImage.png";
                rawImage=ImageIO.read(new File(path));
            }
            catch(IOException exception) {
                this.add(new JLabel("There were problems with loading this image. Try restarting the application"));
            }
        }
        else {
            this.add(new JLabel("Photo avaliable only in .jar form of the app"));
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