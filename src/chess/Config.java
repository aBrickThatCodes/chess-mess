package chess;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Config implements java.io.Serializable {
    //Singleton instance
    private transient static Config instance;

    public Boolean abilities,randFields,randPieces,items,obstacles,pvp,animation;
    public int playerAmount,boardWidth,boardHeight;
    public Color [] colors;

    private Config() {
        abilities=false;
        randFields=false;
        randPieces=false;
        items=false;
        obstacles=false;
        pvp=false;
        animation=false;

        colors=new Color[12];
        colors[0]=Color.WHITE;
        colors[1]=Color.BLACK;
        colors[2]=Color.BLUE;
        colors[3]=Color.GRAY;
        colors[4]=Color.GREEN;
        colors[5]=Color.MAGENTA;
        colors[6]=Color.CYAN;
        colors[7]=Color.ORANGE;
        colors[8]=Color.RED;
        colors[9]=Color.YELLOW;
        colors[10]=Color.PINK;
        colors[11]=Color.DARK_GRAY;
    }

    //Singleton
    public static Config Instance() {
        if(instance==null)
            instance=new Config();

        return instance;
    }

    public void saveSettings() {
        JFileChooser fileChooser=new JFileChooser();
        FileNameExtensionFilter filter=new FileNameExtensionFilter("Chess Mess Settings File (.cmsett)", "cmsett");
        fileChooser.setFileFilter(filter);
        int result=fileChooser.showSaveDialog(null);
        if(result==JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fileOut=new FileOutputStream(fileChooser.getSelectedFile()+".cmsett");
                ObjectOutputStream out=new ObjectOutputStream(fileOut);
                out.writeObject(this);
                out.close();
                fileOut.close();
            }
            catch(IOException exception) {
                JOptionPane.showMessageDialog(null, exception.getStackTrace().toString(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    public static void loadSettings() {
        JFileChooser fileChooser=new JFileChooser();
        FileNameExtensionFilter filter=new FileNameExtensionFilter("Chess Mess Settings File (.cmsett)", "cmsett");
        fileChooser.setFileFilter(filter);
        int result=fileChooser.showOpenDialog(null);
        if(result==JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream fileIn=new FileInputStream(fileChooser.getSelectedFile()+".cmsett");
                ObjectInputStream in=new ObjectInputStream(fileIn);
                instance=(Config)in.readObject();
                in.close();
                fileIn.close();
            }
            catch(IOException exception) {
                JOptionPane.showMessageDialog(null, exception.getStackTrace().toString(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            catch(ClassNotFoundException exception) {
                JOptionPane.showMessageDialog(null, "Class not found\n"+exception.getStackTrace().toString(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
}