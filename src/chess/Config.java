package chess;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Config implements java.io.Serializable {
    // Singleton instance
    private transient static Config instance;

    public static final Color backGroundColor = new Color(255, 174, 201);
    public static final int assetNum = 12;

    public enum Pieces {
        PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING
    };

    public Boolean abilities, randFields, randPieces, items, obstacles, pvp, animation, duels, resourcesLoaded;
    public int playerAmount, boardWidth, boardHeight, maxPlayers, maxPlacements;
    public Color[] colors;
    public Pieces[] pieces;
    transient public File musicFile;
    transient public BufferedImage[] pieceImages, itemImages;
    transient public BufferedImage titleImage, obstacleImage, portalImage;
    public String[] filePaths, urls;

    private Config() {
        // region Booleans
        abilities = false;
        randFields = false;
        randPieces = false;
        items = false;
        obstacles = false;
        pvp = false;
        animation = false;
        duels = false;
        resourcesLoaded = true;
        // endregion

        // region Pieces
        pieces = new Pieces[6];
        pieces[0] = Pieces.PAWN;
        pieces[1] = Pieces.ROOK;
        pieces[2] = Pieces.KNIGHT;
        pieces[3] = Pieces.BISHOP;
        pieces[4] = Pieces.QUEEN;
        pieces[5] = Pieces.KING;
        // endregion

        // region File loading
        urls = new String[assetNum - 1];
        urls[0] = "http://student.fizyka.pw.edu.pl/~janmak/chessmess/titleScreen.png";
        urls[1] = "http://student.fizyka.pw.edu.pl/~janmak/chessmess/obstacle.png";
        urls[2] = "http://student.fizyka.pw.edu.pl/~janmak/chessmess/portal.png";
        urls[3] = "http://student.fizyka.pw.edu.pl/~janmak/chessmess/pawn.png";
        urls[4] = "http://student.fizyka.pw.edu.pl/~janmak/chessmess/rook.png";
        urls[5] = "http://student.fizyka.pw.edu.pl/~janmak/chessmess/knight.png";
        urls[6] = "http://student.fizyka.pw.edu.pl/~janmak/chessmess/bishop.png";
        urls[7] = "http://student.fizyka.pw.edu.pl/~janmak/chessmess/queen.png";
        urls[8] = "http://student.fizyka.pw.edu.pl/~janmak/chessmess/king.png";
        urls[9] = "http://student.fizyka.pw.edu.pl/~janmak/chessmess/bomb.png";
        urls[10] = "http://student.fizyka.pw.edu.pl/~janmak/chessmess/potion.png";
        fileControl();
        // endregion

        // region Other values
        boardWidth = boardHeight = 8;
        playerAmount = 2;
        maxPlayers = 2 * (Math.max(boardWidth / 12, 1) + boardHeight / 12);
        maxPlacements = (boardWidth * boardHeight - 16 * maxPlayers) / 10;
        filePaths = new String[assetNum];
        for (int i = 0; i < assetNum; i++)
            filePaths[i] = "";
        colorSetup();
        // endregion
    }

    // Singleton
    public static Config Instance() {
        if (instance == null)
            instance = new Config();

        return instance;
    }

    // region Forgive me Father, for I have sinned
    public void correctValues() {
        maxPlayers = 2 * (Math.max(Config.Instance().boardWidth / 12, 1) + Config.Instance().boardHeight / 12);
        maxPlacements = (boardWidth * boardHeight - 16 * maxPlayers) / 10;
        if (playerAmount > maxPlayers)
            playerAmount = maxPlayers;
        colorSetup();
        fileControl();
    }

    void colorSetup() {
        int index;

        if (colors == null || colors.length != playerAmount) {
            if (colors == null) {
                colors = new Color[playerAmount];
                colors[0] = Color.WHITE;
                colors[1] = Color.BLACK;
                index = 2;
            }

            else {
                Color[] temp = new Color[playerAmount];
                int len = Math.min(colors.length, temp.length);
                for (int i = 0; i < len; i++) {
                    temp[i] = colors[i];
                }
                colors = temp;
                index = len;
            }
            Random random = new Random();
            while (index < colors.length) {
                boolean isSimilar = false;
                Color c = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                for (int j = 0; j < index; j++) {
                    if (similarTo(c, colors[j]) || c.getRGB() == backGroundColor.getRGB())
                        isSimilar = true;
                }
                if (isSimilar)
                    continue;
                colors[index] = c;
                index++;
            }
        } else {
            for (int i = 0; i < colors.length; i++) {
                if (colors[i].getRGB() == backGroundColor.getRGB())
                    colors[i] = new Color(254, 174, 201);
            }
        }
    }

    void fileControl() {
        pieceImages = new BufferedImage[6];
        itemImages = new BufferedImage[2];
        for (int i = 0; i < assetNum; i++) {
            try {
                if (filePaths != null && filePaths[i] != "") {
                    if (i == 0) {
                        musicFile = new File(filePaths[i]);
                    } else if (i == 1)
                        titleImage = ImageIO.read(new File(filePaths[i]));
                    else if (i == 2)
                        obstacleImage = ImageIO.read(new File(filePaths[i]));
                    else if (i == 3)
                        portalImage = ImageIO.read(new File(filePaths[i]));
                    else if (i < 9)
                        pieceImages[i - 4] = ImageIO.read(new File(filePaths[i]));
                    else
                        itemImages[i - 10] = ImageIO.read(new File(filePaths[i]));
                } else {
                    if (i == 0)
                        ;
                    else if (i == 1)
                        titleImage = ImageIO.read(new URL(urls[i - 1]));
                    else if (i == 2)
                        obstacleImage = ImageIO.read(new URL(urls[i - 1]));
                    else if (i == 3)
                        portalImage = ImageIO.read(new URL(urls[i - 1]));
                    else if (i < 10) {
                        pieceImages[i - 4] = ImageIO.read(new URL(urls[i - 1]));
                    } else {
                        itemImages[i - 10] = ImageIO.read(new URL(urls[i - 1]));
                    }
                }
            } catch (IOException exception) {
                filePaths[i] = "";
                try {
                    if (i == 0)
                        ;
                    else if (i == 1)
                        titleImage = ImageIO.read(new URL(urls[i - 1]));
                    else if (i == 2)
                        obstacleImage = ImageIO.read(new URL(urls[i - 1]));
                    else if (i == 3)
                        portalImage = ImageIO.read(new URL(urls[i - 1]));
                    else if (i < 10) {
                        pieceImages[i - 4] = ImageIO.read(new URL(urls[i - 1]));
                    } else {
                        itemImages[i - 10] = ImageIO.read(new URL(urls[i - 1]));
                    }
                } catch (IOException exception2) {
                    if (resourcesLoaded) {
                        JOptionPane.showMessageDialog(null,
                                "There were problems with loading resources. Check your internet connection", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        resourcesLoaded = false;
                    }
                }
            } catch (NullPointerException exception) {
                try {
                    if (i == 0)
                        ;
                    else if (i == 1)
                        titleImage = ImageIO.read(new URL(urls[i - 1]));
                    else if (i == 2)
                        obstacleImage = ImageIO.read(new URL(urls[i - 1]));
                    else if (i == 3)
                        portalImage = ImageIO.read(new URL(urls[i - 1]));
                    else if (i < 10) {
                        pieceImages[i - 4] = ImageIO.read(new URL(urls[i - 1]));
                    } else {
                        itemImages[i - 10] = ImageIO.read(new URL(urls[i - 1]));
                    }
                } catch (IOException ex) {
                    if (resourcesLoaded) {
                        JOptionPane.showMessageDialog(null,
                                "There were problems with loading resources. Check your internet connection", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        resourcesLoaded = false;
                    }
                }
            }
        }
    }

    boolean similarTo(Color c1, Color c2) {
        double distance = Math.pow((c1.getRed() - c2.getRed()), 2) + Math.pow((c1.getGreen() - c2.getGreen()), 2)
                + Math.pow((c1.getBlue() - c2.getBlue()), 2);
        if (distance < 200) {
            return true;
        } else {
            return false;
        }
    }
    // endregion

    public void saveSettings() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Chess Mess Settings File (.cmsett)", "cmsett");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fileOut = new FileOutputStream(fileChooser.getSelectedFile()
                        + (fileChooser.getSelectedFile().getName().endsWith(".cmsett") ? "" : ".cmsett"));
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(this);
                out.close();
                fileOut.close();
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(null, exception.getStackTrace().toString(), "IOException 1",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    public static boolean loadSettings() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Chess Mess Settings File (.cmsett)", "cmsett");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream fileIn = new FileInputStream(fileChooser.getSelectedFile());
                ObjectInputStream in = new ObjectInputStream(fileIn);
                instance = (Config) in.readObject();
                in.close();
                fileIn.close();
                instance.correctValues();
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(null, exception.getStackTrace().toString(), "IOException 2",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            } catch (ClassNotFoundException exception) {
                JOptionPane.showMessageDialog(null, "Class not found\n" + exception.getStackTrace().toString(), "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        }
        return false;

    }
}