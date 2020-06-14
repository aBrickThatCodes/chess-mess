package chess.launcher;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.filechooser.FileNameExtensionFilter;
import chess.Config;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AssetChangeFrame extends JFrame implements ActionListener {
    private JButton applyButton,cancelButton;
    JButton [] browseButtons;
    JLabel [] filePathLabels;

    public AssetChangeFrame() {
        super("Add your own assets");
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setSize(600,25*(Config.assetNum+1));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel assetChooserPanel=new JPanel(new BorderLayout());
        this.add(assetChooserPanel,BorderLayout.CENTER);

        //region Asset names
        JPanel assetNameLabelPanel=new JPanel(new GridLayout(Config.assetNum,1));
        assetChooserPanel.add(assetNameLabelPanel,BorderLayout.WEST);
        assetNameLabelPanel.add(new JLabel("Music"));
        assetNameLabelPanel.add(new JLabel("Title Screen Image"));
        assetNameLabelPanel.add(new JLabel("Obstacle"));
        assetNameLabelPanel.add(new JLabel("Portal"));
        assetNameLabelPanel.add(new JLabel("Pawn"));
        assetNameLabelPanel.add(new JLabel("Rook"));
        assetNameLabelPanel.add(new JLabel("Knight"));
        assetNameLabelPanel.add(new JLabel("Bishop"));
        assetNameLabelPanel.add(new JLabel("Queen"));
        assetNameLabelPanel.add(new JLabel("King"));
        //endregion

        //region File paths
        JPanel filePathLabelPanel=new JPanel(new GridLayout(Config.assetNum,1));
        filePathLabelPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        assetChooserPanel.add(filePathLabelPanel,BorderLayout.CENTER);

        filePathLabels=new JLabel[Config.assetNum];
        for(int i=0;i<filePathLabels.length;i++) {
            filePathLabels[i]=new JLabel();
            if(Config.Instance().filePaths!=null)
                if(Config.Instance().filePaths[i]!=null)
                    filePathLabels[i].setText(Config.Instance().filePaths[i]);
            filePathLabelPanel.add(filePathLabels[i]);
        }
        //endregion

        //region Browse buttons
        JPanel browseButtonsPanel=new JPanel(new GridLayout(Config.assetNum,1));
        assetChooserPanel.add(browseButtonsPanel, BorderLayout.EAST);

        ActionListener browseListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button=(JButton)e.getSource();
                JFileChooser fileChooser=new JFileChooser();
                FileNameExtensionFilter filter;

                if(button==browseButtons[0])
                    filter=new FileNameExtensionFilter("Music file", "mp3");
                else
                    filter=new FileNameExtensionFilter("Image file", "jpg","png");

                fileChooser.setFileFilter(filter);
                int result=fileChooser.showOpenDialog(null);
                if(result==JFileChooser.APPROVE_OPTION) {
                    for(int i=0;i<browseButtons.length;i++) {
                        if(button==browseButtons[i]) {
                            filePathLabels[i].setText(fileChooser.getSelectedFile().getAbsolutePath());
                            break;
                        }
                    }
                }
            }
        };

        browseButtons=new JButton[Config.assetNum];
        for(int i=0;i<browseButtons.length;i++) {
            browseButtons[i]=new JButton("Browse");
            browseButtonsPanel.add(browseButtons[i]);
            browseButtons[i].addActionListener(browseListener);
        }
        //endregion

        //region Bottom buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        this.add(buttonsPanel, BorderLayout.PAGE_END);

        applyButton = new JButton("Apply");
        applyButton.addActionListener(this);
        buttonsPanel.add(applyButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        buttonsPanel.add(cancelButton);
        //endregion
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button=(JButton)e.getSource();

        if(button==applyButton) {
            for(int i=0;i<Config.assetNum;i++)
                Config.Instance().filePaths[i]=filePathLabels[i].getText();
            Config.Instance().correctValues();
        }
        this.dispose();
	}
}