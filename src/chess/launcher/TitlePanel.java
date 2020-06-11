package chess.launcher;

import javax.swing.JLabel;
import chess.Config;
import chess.ImageDrawPanel;
import java.awt.Graphics;

@SuppressWarnings("serial")
class TitlePanel extends ImageDrawPanel {
    public boolean resourcesLoaded=true;
    
    public TitlePanel() {
        super();
        
        if(!Config.Instance().resourcesLoaded) {
            this.add(new JLabel("There were problems with loading resources. Check your internet connection"));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(Config.Instance().resourcesLoaded)
            g.drawImage(resizeImage(this.getWidth(), this.getHeight(), Config.Instance().titleImage, null,null), 0, 0, this);
    }
}