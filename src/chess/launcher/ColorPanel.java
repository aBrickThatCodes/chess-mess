package chess.launcher;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class ColorPanel extends JPanel implements MouseListener {
    public ColorPanel(Color color) {
        super();
        this.setBackground(color);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ColorPickFrame colorPickFrame=new ColorPickFrame(this);
        colorPickFrame.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        return;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        return;

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        return;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        return;
    }
}