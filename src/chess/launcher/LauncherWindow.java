package chess.launcher;

import javax.swing.*;
import chess.Config;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class LauncherWindow extends JFrame {
    private LauncherWindow launcher;

    public LauncherWindow() {
        super("Chess Mess");
        this.setSize(960, 520);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(2,1));
        this.launcher=this;

        //region Title
        JPanel title=new JPanel();
        this.add(title);

        title.add(new JLabel("Image pending",JLabel.CENTER));
        //endregion

        //region Menu
        JPanel menu=new JPanel(new BorderLayout());
        this.add(menu);

        //region Bottom buttons
        JPanel gameButtons=new JPanel(new FlowLayout());
        menu.add(gameButtons,BorderLayout.PAGE_END);
        
        JButton startButton=new JButton("Play");
        gameButtons.add(startButton);

        JButton loadGameButton=new JButton("Load Game");
        gameButtons.add(loadGameButton);
        //endregion

        //region Menus
        JPanel mainMenu=new JPanel(new GridLayout(1,2));
        menu.add(mainMenu);

        //region Left Panel (game settings)
        JPanel gameSettings=new JPanel(new GridLayout(7,1));
        mainMenu.add(gameSettings);

        //region Board size
        JPanel boardSize=new JPanel(new GridLayout(1,2));
        gameSettings.add(boardSize);

        boardSize.add(new JLabel("Board size:"));

        JPanel sizeFieldsPanel=new JPanel(new GridLayout(1,3));
        boardSize.add(sizeFieldsPanel);

        JTextField xSize=new JTextField("8");
        sizeFieldsPanel.add(xSize);
        xSize.setText(Integer.toString(Config.Instance().boardWidth));

        sizeFieldsPanel.add(new JLabel("X",JLabel.CENTER));

        JTextField ySize=new JTextField("8");
        sizeFieldsPanel.add(ySize);
        ySize.setText(Integer.toString(Config.Instance().boardHeight));

        ActionListener sizeListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                JTextField field=(JTextField)e.getSource();
                int i=-1;
                String s=field.getText();
                try {
                    i=Integer.parseInt(s);
                }
                catch(NumberFormatException exception) {
                    JOptionPane.showMessageDialog(launcher, "Error: Couldn't parse int", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if(i>=Math.max((Config.Instance().playerAmount/2-1)*8-4,8)) {
                    if(field==xSize) {
                        Config.Instance().boardWidth=i;
                    }
                    else if(field==ySize) {
                        Config.Instance().boardHeight=i;
                    }
                }
                xSize.setText(Integer.toString(Config.Instance().boardWidth));
                ySize.setText(Integer.toString(Config.Instance().boardHeight));
			}
        };
        xSize.addActionListener(sizeListener);
        ySize.addActionListener(sizeListener);
        //endregion

        //region GameMode
        String[] gameModes={"Player vs \"AI\"","Hot Seat"};
        JComboBox<String> gameMode=new JComboBox<String>(gameModes);
        gameSettings.add(gameMode);
        gameMode.setSelectedItem(Config.Instance().pvp ? 1 : 0);
        ActionListener gameModeListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String s=(String)((((JComboBox<String>)gameMode).getSelectedItem()));
               if(s=="Player vs \"AI\"") {
                   Config.Instance().pvp=false;
               }
               else if(s=="Hot Seat") {
                   Config.Instance().pvp=true;
               }
            }
        };
        gameMode.addActionListener(gameModeListener);
        //endregion

        //region Player amount panel
        JPanel playerNumber=new JPanel(new FlowLayout());
        gameSettings.add(playerNumber);
        
        JLabel playerNumLabel=new JLabel("Number of players:");
        playerNumber.add(playerNumLabel);
        
        JButton lessPlayersButton=new JButton("-");
        playerNumber.add(lessPlayersButton);
        JLabel playerAmountText=new JLabel(Integer.toString(Config.Instance().playerAmount));
        playerNumber.add(playerAmountText);
        JButton morePlayersButton=new JButton("+");
        playerNumber.add(morePlayersButton);
        ActionListener playerNumListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button=(JButton)e.getSource();
                if(button==morePlayersButton && Config.Instance().playerAmount<(2*((Math.min(Config.Instance().boardWidth,Config.Instance().boardHeight)-4)/8+1))) {
                    ++Config.Instance().playerAmount;
                }
                else if(button==lessPlayersButton && Config.Instance().playerAmount>2) {
                    --Config.Instance().playerAmount;
                }
                playerAmountText.setText(Integer.toString(Config.Instance().playerAmount));
            }
        };
        lessPlayersButton.addActionListener(playerNumListener);
        morePlayersButton.addActionListener(playerNumListener);
        //endregion

        //region Piece settings
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
                PieceSetupFrame setupframe=new PieceSetupFrame();
                setupframe.setVisible(true);
			}
        };
        pieceEdit.addActionListener(pieceEditListener);
        //endregion
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
        saveAnimation.setSelected(Config.Instance().animation);
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
                LauncherWindow main = new LauncherWindow();    

            main.setVisible(true);
            launcher.dispose();
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
                MusicChangeFrame musicChangeFrame=new MusicChangeFrame();
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
                ColorSchemeWindow colorSchemeWindow=new ColorSchemeWindow();
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
        addAbilitiesCheckBox.setSelected(Config.Instance().abilities);
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
        randFieldsCheckBox.setSelected(Config.Instance().randFields);
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
        randPiecesCheckBox.setSelected(Config.Instance().randPieces);
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
        duelsCheckBox.setSelected(Config.Instance().duels);
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
        itemsCheckBox.setSelected(Config.Instance().items);
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
        obstaclesCheckBox.setSelected(Config.Instance().obstacles);
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

    public static void main(String[] args) {
        LauncherWindow window=new LauncherWindow();
        window.setVisible(true);
    }
}