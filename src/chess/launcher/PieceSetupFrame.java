package chess.launcher;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import chess.Config;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PieceSetupFrame extends JFrame implements ActionListener {
    GroupPanel [] groupPanels;
    JButton applyButton,cancelButton;

    public PieceSetupFrame() {
        super("Piece Setup Editor");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600,350);
        this.setLayout(new BorderLayout());
        
        JPanel pieceSetup=new JPanel(new GridLayout(Config.Instance().pieces.length/3+(Config.Instance().pieces.length%3==0 ? 0:1),Config.Instance().pieces.length/2));
        groupPanels=new GroupPanel[Config.Instance().pieces.length];
        for(int i=0;i<Config.Instance().pieces.length;i++) {
            groupPanels[i]=new GroupPanel(i);
            pieceSetup.add(groupPanels[i]);
        }
        this.add(pieceSetup,BorderLayout.CENTER);

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
            for(int i=0;i<Config.Instance().pieces.length;i++) {
                Config.Instance().pieces[i]=groupPanels[i].getPieces();
            }   
       }
       this.dispose();
    }
}