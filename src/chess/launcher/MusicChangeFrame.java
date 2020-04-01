package chess.launcher;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import chess.Config;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MusicChangeFrame extends JFrame implements ActionListener {
    private JButton applyButton,cancelButton;
    private JFileChooser fileChooser;
    JLabel filePathLabel;

    public MusicChangeFrame() {
        super("Choose your own music");
        this.setLayout(new BorderLayout());
        this.setSize(400,100);

        JPanel musicChooserPanel=new JPanel(new BorderLayout());
        this.add(musicChooserPanel,BorderLayout.CENTER);

        filePathLabel=new JLabel();
        if(Config.Instance().musicFile!=null) {
            filePathLabel.setText(Config.Instance().musicFile.getPath());
        }
        musicChooserPanel.add(filePathLabel,BorderLayout.CENTER);
        //region File chooser button
        JButton musicChooserButton=new JButton("Browse");
        musicChooserPanel.add(musicChooserButton,BorderLayout.EAST);
        ActionListener a=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser=new JFileChooser();
                FileNameExtensionFilter filter=new FileNameExtensionFilter("Music file (.mp3)", "mp3");
                fileChooser.setFileFilter(filter);
                int result=fileChooser.showOpenDialog(null);
                if(result==JFileChooser.APPROVE_OPTION) {
                    filePathLabel.setText(fileChooser.getSelectedFile().getPath());
                }
            }
            
        };
        musicChooserButton.addActionListener(a);
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
            Config.Instance().musicFile=fileChooser.getSelectedFile();
        }
        this.dispose();
	}
}