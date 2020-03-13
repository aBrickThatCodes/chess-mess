package chess.launcher;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class LauncherWindow extends JFrame {
    public LauncherWindow() {
        super("Chess");
        this.setSize(960, 520);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(2,1));

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
        
        JButton closeButton=new JButton("Exit");
        gameButtons.add(closeButton);
        //endregion

        //region Menus
        JPanel mainMenu=new JPanel(new GridLayout(1,2));
        menu.add(mainMenu);

        //region Left Panel (game settings)
        JPanel gameSettings=new JPanel(new GridLayout(7,1));
        mainMenu.add(gameSettings);


        JPanel boardSize=new JPanel(new GridLayout(1,3));
        gameSettings.add(boardSize);

        JTextField xSize=new JTextField("8");
        boardSize.add(xSize);

        boardSize.add(new JLabel("X",JLabel.CENTER));

        JTextField ySize=new JTextField("8");
        boardSize.add(ySize);


        String[] gameModes={"Player vs \"AI\"","Hot Seat"};
        JComboBox<String> gameMode=new JComboBox<String>(gameModes);
        gameSettings.add(gameMode);


        JPanel playerNumber=new JPanel(new GridLayout(1,2));
        gameSettings.add(playerNumber);
        
        JLabel playerNumText=new JLabel("Number of players:");
        playerNumber.add(playerNumText);
        JSpinner playerNumSpin=new JSpinner();
        playerNumber.add(playerNumSpin);


        JPanel pieceSettings=new JPanel(new GridLayout(1,3));
        gameSettings.add(pieceSettings);
        pieceSettings.add(new JLabel("Number of pieces:"));

        JTextField pieceNum=new JTextField("16");
        pieceSettings.add(pieceNum);

        JButton pieceEdit=new JButton("Choose pieces");
        pieceSettings.add(pieceEdit);


        JButton tutorial=new JButton("Player guide");
        gameSettings.add(tutorial);

        JButton language=new JButton("Choose language");
        gameSettings.add(language);

        JCheckBox saveAnimation=new JCheckBox("Save to animation");
        gameSettings.add(saveAnimation);
        //endregion

        //region Right Panel (rule settings)
        JPanel rulesSettings=new JPanel(new BorderLayout());
        mainMenu.add(rulesSettings);

        //region Rule Settings Buttons
        JPanel ruleSettingButtons=new JPanel(new GridLayout(4,1));
        rulesSettings.add(ruleSettingButtons,BorderLayout.PAGE_START);
        JButton loadSettings=new JButton("Load settings");
        ruleSettingButtons.add(loadSettings);
        JButton changeMusic=new JButton("Upload your own music");
        ruleSettingButtons.add(changeMusic);
        JButton pickColour=new JButton("Pick your own colour scheme");
        ruleSettingButtons.add(pickColour);
        JButton saveSettings=new JButton("Save settings");
        ruleSettingButtons.add(saveSettings);
        //endregion

        //region Rule Checkboxes
        JPanel ruleCheckBoxes=new JPanel(new GridLayout(3,2));
        rulesSettings.add(ruleCheckBoxes,BorderLayout.CENTER);

        JCheckBox addAbilities=new JCheckBox("Additional abilities");
        ruleCheckBoxes.add(addAbilities);

        JCheckBox randFields=new JCheckBox("Randomizing fields");
        ruleCheckBoxes.add(randFields);

        JCheckBox randomPieces=new JCheckBox("Random pieces");
        ruleCheckBoxes.add(randomPieces);

        JCheckBox duels=new JCheckBox("jRPG fights");
        ruleCheckBoxes.add(duels);

        JCheckBox items=new JCheckBox("Items");
        ruleCheckBoxes.add(items);

        JCheckBox obstacles=new JCheckBox("Obstacles");
        ruleCheckBoxes.add(obstacles);
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