package chess.launcher;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class ColorPickFrame extends JFrame implements ActionListener {
    private ColorPanel colorPanel;
    private JButton applyButton,cancelButton;
    private JColorChooser colorPicker;

    public ColorPickFrame(ColorPanel colorPanel) {
        super("Color Picker");
        this.setResizable(false);
        this.setSize(450, 300);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.colorPanel = colorPanel;

        colorPicker = new JColorChooser(colorPanel.getBackground());
        this.add(colorPicker, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        this.add(buttonsPanel, BorderLayout.PAGE_END);

        applyButton = new JButton("Apply");
        applyButton.addActionListener(this);
        buttonsPanel.add(applyButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        buttonsPanel.add(cancelButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button=(JButton)e.getSource();

        if(button==applyButton) {
            colorPanel.setBackground(colorPicker.getColor());
        }
        this.dispose();
    }
}