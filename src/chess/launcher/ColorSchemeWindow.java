package chess.launcher;

import javax.swing.JFrame;
import javax.swing.JPanel;
import chess.Config;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ColorSchemeWindow extends JFrame implements ActionListener {
    private JButton applyButton;
    private JButton cancelButton;
    private ColorPanel [] colorPanels;

    public ColorSchemeWindow() {
        super("Color Scheme Editor");
        this.setSize(Math.min(4, Config.Instance().playerAmount)*62,Math.min(1,Config.Instance().playerAmount/4)*50);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //region Color scheme panel
        
        JPanel colorSchemePanel=new JPanel(new GridLayout(3,4));
        colorPanels=new ColorPanel[Config.Instance().colors.length];
        for(int i=0;i<Config.Instance().colors.length;i++) {
            colorPanels[i]=new ColorPanel(Config.Instance().colors[i]);
            colorSchemePanel.add(colorPanels[i]);
        }
        this.add(colorSchemePanel,BorderLayout.CENTER);
        //endregion

        //region Buttons panel
        JPanel buttonsPanel=new JPanel(new FlowLayout());
        this.add(buttonsPanel,BorderLayout.PAGE_END);


        applyButton=new JButton("Apply");
        applyButton.addActionListener(this);
        buttonsPanel.add(applyButton);
        
        cancelButton=new JButton("Cancel");
        cancelButton.addActionListener(this);
        buttonsPanel.add(cancelButton);
        //endregion
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button=(JButton)e.getSource();

        if(button==applyButton) {
            for(int i=0;i<colorPanels.length;i++) {
                Config.Instance().colors[i]=colorPanels[i].getBackground();
            }
        }
        this.dispose();
    }
}