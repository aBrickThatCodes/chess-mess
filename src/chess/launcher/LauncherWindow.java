package chess.launcher;

import javax.swing.*;
import chess.Config;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class LauncherWindow extends JFrame {
    LauncherWindow thisWindow;
    ColorSchemeWindow colorSchemeWindow;
    PieceSetupFrame pieceSetupFrame;
    MusicChangeFrame musicChangeFrame;
    public LauncherWindow() {
        super("Chess Mess");
        thisWindow=this;
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        } 
        catch (Exception exception)
        {}
        this.setSize(960, 520);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(2,1));

        //region Title
        TitlePanel title=new TitlePanel();
        this.add(title);

        
        //endregion

        //region Menu
        JPanel menu=new JPanel(new BorderLayout());
        menu.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        this.add(menu);

        //region Bottom buttons
        JPanel gameButtons=new JPanel(new FlowLayout());
        gameButtons.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        menu.add(gameButtons,BorderLayout.PAGE_END);
        
        JButton startButton=new JButton("Play");
        gameButtons.add(startButton);

        JButton loadGameButton=new JButton("Load Game");
        gameButtons.add(loadGameButton);
        //endregion

        //region Menus
        JPanel mainMenu=new JPanel(new GridLayout(1,2));
        mainMenu.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        menu.add(mainMenu);

        //region Left Panel (game settings)
        JPanel gameSettings=new JPanel(new GridLayout(7,1));
        gameSettings.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        mainMenu.add(gameSettings);

        //region Board size
        JPanel boardSize=new JPanel(new GridLayout(1,4));
        gameSettings.add(boardSize);

        boardSize.add(new JLabel("Board size:"));

        JTextField xSize=new JTextField(Integer.toString(Config.Instance().boardWidth));
        boardSize.add(xSize);

        boardSize.add(new JLabel("X",JLabel.CENTER));

        JTextField ySize=new JTextField(Integer.toString(Config.Instance().boardHeight));
        boardSize.add(ySize);

        JButton sizeApplyButton=new JButton("Apply");
        boardSize.add(sizeApplyButton);
        ActionListener sizeApplyListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sizeX=8, sizeY=8;
                try {
                    sizeX=Integer.parseInt(xSize.getText());
                    sizeY=Integer.parseInt(ySize.getText());
                }
                catch(NumberFormatException exception) {
                    JOptionPane.showMessageDialog(thisWindow, "Could not parse the size", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(sizeX<8 || sizeY<8) {
                    JOptionPane.showMessageDialog(thisWindow, "Size too small", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Config.Instance().boardWidth=sizeX;
                Config.Instance().boardHeight=sizeY;
                Config.Instance().correctValues();
                updateLauncher();
            }
        };
        sizeApplyButton.addActionListener(sizeApplyListener);
        //endregion

        String[] gameModes={"Player vs \"AI\"","Hot Seat"};
        JComboBox<String> gameMode=new JComboBox<String>(gameModes);
        gameSettings.add(gameMode);

        //region Num of players
        JPanel playerNumber=new JPanel(new GridLayout(1,2));
        gameSettings.add(playerNumber);
        
        JLabel playerNumText=new JLabel("Number of players:");
        playerNumber.add(playerNumText);
        //region Number of players buttons
        JButton lessPlayersButton=new JButton("-");
        JButton morePlayersButton=new JButton("+");
        JLabel numLabel=new JLabel(Integer.toString(Config.Instance().playerAmount),JLabel.CENTER);
        ActionListener playerNumListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button=(JButton)e.getSource();
                try {
                    if(button==lessPlayersButton && Config.Instance().playerAmount>2) {
                        Config.Instance().playerAmount--;
                    }
                    else if(button==morePlayersButton && Config.Instance().playerAmount<Config.Instance().maxPlayers) {
                        Config.Instance().playerAmount++;
                    }
                    Config.Instance().correctValues();
                    numLabel.setText(Integer.toString(Config.Instance().playerAmount));
                }
                catch(NullPointerException exception) {
                }
            }
        };
        lessPlayersButton.addActionListener(playerNumListener);
        morePlayersButton.addActionListener(playerNumListener);
        playerNumber.add(lessPlayersButton);
        playerNumber.add(numLabel);
        playerNumber.add(morePlayersButton);
        //endregion
        //endregion

        
        JPanel pieceSettings=new JPanel(new GridLayout(1,3));
        gameSettings.add(pieceSettings);

        /*
        pieceSettings.add(new JLabel("Number of pieces:"));
        JTextField pieceNum=new JTextField("16");
        pieceSettings.add(pieceNum);
        */

        //region Piece setup button
        JButton pieceEdit=new JButton("Choose pieces");
        pieceSettings.add(pieceEdit);
        ActionListener pieceEditListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                pieceSetupFrame=new PieceSetupFrame();
                pieceSetupFrame.setVisible(true);
			}
        };
        pieceEdit.addActionListener(pieceEditListener);
        //endregion

        JButton tutorial=new JButton("Player guide");
        gameSettings.add(tutorial);

        /*
        JButton language=new JButton("Choose language");
        gameSettings.add(language);
        */

        //region Save to anim checkbox
        JCheckBox saveAnimation=new JCheckBox("Save to animation");
        gameSettings.add(saveAnimation);
        ActionListener animListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton)e.getSource();
                Config.Instance().animation= abstractButton.getModel().isSelected();
            }
        };
        saveAnimation.addActionListener(animListener);
        //endregion

        //endregion

        //region Right Panel (rule settings)
        JPanel rulesSettings=new JPanel(new BorderLayout());
        rulesSettings.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        mainMenu.add(rulesSettings);

        //region Rule Settings Buttons
        JPanel ruleSettingButtons=new JPanel(new GridLayout(4,1));
        rulesSettings.add(ruleSettingButtons,BorderLayout.PAGE_START);

        //region Save settings
        JButton saveSettings=new JButton("Save settings");
        ruleSettingButtons.add(saveSettings);
        ActionListener saveSetttingsListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                Config.Instance().saveSettings();
			}
        };
        saveSettings.addActionListener(saveSetttingsListener);
        //endregion

        //region Load settings button
        JButton loadSettings=new JButton("Load settings");
        ruleSettingButtons.add(loadSettings);
        ActionListener loadSetttingsListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                Config.loadSettings();
                updateLauncher();
			}
        };
        loadSettings.addActionListener(loadSetttingsListener);
        //endregion

        //region Change music
        JButton changeMusic=new JButton("Add some music");
        ruleSettingButtons.add(changeMusic);
        ActionListener changeMusicListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                musicChangeFrame=new MusicChangeFrame();
                musicChangeFrame.setVisible(true);
			}
        };
        changeMusic.addActionListener(changeMusicListener);
        //endregion

        //region Color picker
        JButton pickColour=new JButton("Pick your own colour scheme");
        ruleSettingButtons.add(pickColour);
        ActionListener colorPickListener=new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                colorSchemeWindow=new ColorSchemeWindow();
                colorSchemeWindow.setVisible(true);
            }
        };
        pickColour.addActionListener(colorPickListener);
        //endregion

        //endregion

        //region Rule Checkboxes
        JPanel ruleCheckBoxes=new JPanel(new GridLayout(3,2));
        rulesSettings.add(ruleCheckBoxes, BorderLayout.CENTER);

        //region Abilities checkbox
        JCheckBox addAbilitiesCheckBox=new JCheckBox("Additional abilities");
        ruleCheckBoxes.add(addAbilitiesCheckBox);
        ActionListener abilitiesListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton)e.getSource();
                Config.Instance().abilities= abstractButton.getModel().isSelected();
            }
        };
        addAbilitiesCheckBox.addActionListener(abilitiesListener);
        //endregion

        //region Random fields checkbox
        JCheckBox randFieldsCheckBox=new JCheckBox("Randomizing fields");
        ruleCheckBoxes.add(randFieldsCheckBox);
        ActionListener randFieldsListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton)e.getSource();
                Config.Instance().randFields= abstractButton.getModel().isSelected();
            }
        };
        randFieldsCheckBox.addActionListener(randFieldsListener);
        //endregion

        //region Random pieces checkbox
        JCheckBox randPiecesCheckBox=new JCheckBox("Random pieces");
        ruleCheckBoxes.add(randPiecesCheckBox);
        ActionListener randPiecesListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton)e.getSource();
                Config.Instance().randPieces= abstractButton.getModel().isSelected();
            }
        };
        randPiecesCheckBox.addActionListener(randPiecesListener);
        //endregion

        //region jRPG checkbox
        JCheckBox duelsCheckBox=new JCheckBox("jRPG fights");
        ruleCheckBoxes.add(duelsCheckBox);
        ActionListener duelsListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton)e.getSource();
                Config.Instance().duels= abstractButton.getModel().isSelected();
            }
        };
        duelsCheckBox.addActionListener(duelsListener);
        //endregion

        //region Items checkbox
        JCheckBox itemsCheckBox=new JCheckBox("Items");
        ruleCheckBoxes.add(itemsCheckBox);
        ActionListener itemsListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton)e.getSource();
                Config.Instance().items= abstractButton.getModel().isSelected();
            }
        };
        itemsCheckBox.addActionListener(itemsListener);
        //endregion

        //region Obstacles checkbox
        JCheckBox obstaclesCheckBox=new JCheckBox("Obstacles");
        ruleCheckBoxes.add(obstaclesCheckBox);
        ActionListener obstaclesListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton)e.getSource();
                Config.Instance().obstacles= abstractButton.getModel().isSelected();
            }
        };
        obstaclesCheckBox.addActionListener(obstaclesListener);
        //endregion
        //endregion
        //endregion
        //endregion
        //endregion
    }

    void updateLauncher() {
        if(colorSchemeWindow!=null) {
            colorSchemeWindow.dispose();
        }
        if(musicChangeFrame!=null) {
            musicChangeFrame.dispose();
        }
        if(pieceSetupFrame!=null) {
            pieceSetupFrame.dispose();
        }
        LauncherWindow newLauncher=new LauncherWindow();
        newLauncher.setVisible(true);
        thisWindow.dispose();
    }

    public static void main(String[] args) {
        LauncherWindow window=new LauncherWindow();
        window.setVisible(true);
    }
}