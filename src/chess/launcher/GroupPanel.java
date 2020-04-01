package chess.launcher;

import javax.swing.JLabel;
import javax.swing.JPanel;
import chess.Config;
import chess.Config.Pieces;
import javax.swing.JComboBox;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class GroupPanel extends JPanel {
    private int index;
    private JComboBox<Pieces> comboBox;

    public GroupPanel(int index) {
        super(new GridLayout(2,1));
        this.index=index;
        this.add(new JLabel("Group "+Integer.toString(this.index+1)));
        comboBox=new JComboBox<Pieces>(Config.Instance().pieces);
        this.add(comboBox);
        comboBox.setSelectedIndex(this.index);
    }

    public Pieces getPieces() {
        return (Pieces)comboBox.getSelectedItem();
    }

}