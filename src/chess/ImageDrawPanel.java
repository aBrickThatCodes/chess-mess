package chess;

import javax.swing.JPanel;
import java.awt.image.*;
import java.awt.*;

@SuppressWarnings("serial")
public class ImageDrawPanel extends JPanel {
    public ImageDrawPanel() {
        super();
    }

    protected Image resizeImage(int width, int height, BufferedImage image, Color bgColor, Color imageColor) {     
        Graphics2D g2d=(Graphics2D)image.getGraphics();
        if(imageColor==Color.BLACK) {
            replaceColor(image, Color.WHITE, Color.YELLOW);
            replaceColor(image, Color.BLACK, Color.WHITE);
            replaceColor(image, Color.YELLOW, Color.BLACK);
        }
        else if(imageColor!=null);
            replaceColor(image, Color.WHITE, imageColor);

        if(bgColor!=null)
            replaceColor(image, Config.backGroundColor, bgColor);
            
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image,0,0,image.getWidth(this),image.getHeight(this),null);
        g2d.dispose();

        Image resized=image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        return resized;
    }

    protected static void replaceColor(BufferedImage img, Color toReplace, Color replacer) {
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                Color color = new Color(img.getRGB(x, y));
                if(color.getRGB()==toReplace.getRGB())
                    img.setRGB(x, y, replacer.getRGB());
            }
        }
    }
}