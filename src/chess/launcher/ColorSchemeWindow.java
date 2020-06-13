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
    private JButton applyButton, cancelButton, nextPageButton,prevPageButton;
    JPanel colorSchemePanel;
    private ColorPanel [][] colorPanels;
    int currentPage,pages;

    public ColorSchemeWindow() {
        super("Color Scheme Editor");
        currentPage=0;
        pages=Config.Instance().colors.length/16+(Config.Instance().colors.length%16!=0 ? 1:0);
        this.setSize(400+Math.min(4, Config.Instance().colors.length)*62,(Config.Instance().colors.length/4+(Config.Instance().colors.length%4!=0 ? 1 : 0))*50+100);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //region Buttons panel
        JPanel buttonsPanel=new JPanel(new FlowLayout());
        this.add(buttonsPanel,BorderLayout.PAGE_END);

        //region Color scheme panel
        colorSchemePanelSetup();
        //endregion

        applyButton=new JButton("Apply");
        applyButton.addActionListener(this);
        buttonsPanel.add(applyButton);
        
        cancelButton=new JButton("Cancel");
        cancelButton.addActionListener(this);
        buttonsPanel.add(cancelButton);
        //endregion

        //region Page buttons
        nextPageButton=new JButton("Next page");
        nextPageButton.addActionListener(this);
        this.add(nextPageButton,BorderLayout.EAST);
        if(pages==1)
            nextPageButton.setEnabled(false);

        prevPageButton=new JButton("Previous page");
        prevPageButton.addActionListener(this);
        this.add(prevPageButton,BorderLayout.WEST);
        prevPageButton.setEnabled(false);
        //endregion
    }

    void colorSchemePanelSetup() {
        if(colorSchemePanel==null) {
            colorSchemePanel=new JPanel(new GridLayout(Math.min(4,Config.Instance().colors.length/4+(Config.Instance().colors.length%4!=0 ? 1 : 0)),Math.min(4,Config.Instance().colors.length)));
            this.add(colorSchemePanel);
        
            colorPanels=new ColorPanel[pages][];

            for(int i=0;i<pages;i++) {
                colorPanels[i]=new ColorPanel[Math.min(16,Config.Instance().colors.length-16*i)]; 
                for(int j=0;j<Math.min(16,Config.Instance().colors.length-16*i);j++) {
                    colorPanels[i][j]=new ColorPanel(Config.Instance().colors[j+16*i]);
                }
            }
        }
        else {
            colorSchemePanel.removeAll();
        }

        for(int i=0;i<Math.min(16,Config.Instance().colors.length-16*currentPage);i++) {
            colorSchemePanel.add(colorPanels[currentPage][i]);
        }
        colorSchemePanel.revalidate();
        colorSchemePanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button=(JButton)e.getSource();

        if(button==applyButton || button==cancelButton) {
            if(button==applyButton) {
                for(int i=0;i<pages;i++) {
                    for(int j=0;j<Math.min(16,Config.Instance().colors.length-16*currentPage);j++) {
                        Config.Instance().colors[j+16*i]=colorPanels[i][j].getBackground();
                        Config.Instance().correctValues();
                    }
                }
            }
            this.dispose();
        }
        else if(button==nextPageButton && currentPage<pages-1) {
            ++currentPage;
            colorSchemePanelSetup();
            prevPageButton.setEnabled(true);
            if(currentPage==pages-1) {
                nextPageButton.setEnabled(false);
            }
        }
        else if(button==prevPageButton && currentPage>0) {
            --currentPage;
            colorSchemePanelSetup();
            nextPageButton.setEnabled(true);
            if(currentPage==0) {
                prevPageButton.setEnabled(false);
            }
        }
    }
}