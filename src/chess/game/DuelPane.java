package chess.game;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chess.ImageDrawPanel;
import chess.pieces.Piece;
import java.awt.*;
import java.awt.event.*;

public class DuelPane {
    static int [] health, actions;
    static Piece winner, current, attacker, defender;

    public static Piece duel(Piece att, Piece def) {
        health=new int[2];
        health[0]=health[1]=10;
        actions=new int[2];
        actions[0]=actions[1]=0;
        current=attacker=att;
        defender=def;
        winner=null;


        ImageDrawPanel drawPanel=new ImageDrawPanel();
        drawPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));

        //region Buttons
        JPanel actionButtonsPanel=new JPanel(new FlowLayout());
        JLabel pieceLabel=new JLabel("Attacker");
        actionButtonsPanel.add(pieceLabel);
        JButton attackButton=new JButton("Attack"), defendButton=new JButton("Defend"), runButton=new JButton("Run");
        actionButtonsPanel.add(attackButton);
        actionButtonsPanel.add(defendButton);
        actionButtonsPanel.add(runButton);

        ActionListener a=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button=(JButton)e.getSource();
                if(button==runButton) {
                    if(current==attacker)
                        winner=defender;
                    else
                        winner=attacker;
                }

                else if(button==attackButton) {
                    if(current==attacker) {
                        actions[0]=1;
                        current=defender;
                        pieceLabel.setText("Defender");
                    }
                    else {
                        actions[1]=1;
                        current=attacker;
                        pieceLabel.setText("Attacker");
                    }
                }

                else if(button==defendButton) {
                    if(current==attacker) {
                        actions[0]=2;
                        current=defender;
                        pieceLabel.setText("Defender");
                    }
                    else {
                        actions[1]=2;
                        current=attacker;
                        pieceLabel.setText("Attacker");
                    }
                }
            }
        };
        attackButton.addActionListener(a);
        defendButton.addActionListener(a);
        runButton.addActionListener(a);
        //endregion

        JFrame frame=new JFrame("Fight!");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(400,300);
        frame.setResizable(false);
        frame.add(drawPanel, BorderLayout.CENTER);
        frame.add(actionButtonsPanel,BorderLayout.SOUTH);
        frame.setVisible(true);

        DuelThread duelThread=new DuelThread();
        duelThread.start();

        while(winner==null) {
            Graphics g=drawPanel.getGraphics();
            Color attackerColor=current==attacker ? Color.GREEN : Color.GRAY;
            Image attackerImage=drawPanel.resizeImage(drawPanel.getWidth()/2-20,drawPanel.getWidth()/2-20,attacker.getPieceIcon(),attackerColor,attacker.getColor());
            Color defenderColor=current==defender ? Color.GREEN : Color.GRAY;
            Image defenderImage=drawPanel.resizeImage(drawPanel.getWidth()/2-20,drawPanel.getWidth()/2-20,defender.getPieceIcon(),defenderColor,defender.getColor());
            g.drawImage(attackerImage, 10, 20, null);
            g.drawImage(defenderImage, drawPanel.getWidth()/2+10, 20, null);
            g.setColor(Color.RED);
            g.fillRect(10 , 0, health[0]*(drawPanel.getWidth()/2-20)/10, 5);
            g.fillRect(drawPanel.getWidth()/2+10 , 0, health[1]*(drawPanel.getWidth()/2-20)/10, 5);
            g.dispose();
            drawPanel.revalidate();
        }
        frame.dispose();
        return winner;
    }
}