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

        Image resized=imageChanged.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
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