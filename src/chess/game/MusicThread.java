package chess.game;

import javax.swing.JFrame;

import chess.Config;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicThread extends Thread implements LineListener {
    JFrame frame;
    Clip audioClip=null;
    AudioInputStream audioStream = null;
    boolean playCompleted=false;

    public MusicThread(JFrame frame) {
        super();
        this.frame=frame;
        if(Config.Instance().musicFile==null)
            return;
        
        play();
    }

    public void run() {
        audioClip.start();
        boolean running=true;
        while(running) {
            if(!playCompleted) {
                if(!frame.isDisplayable()) {
                    audioClip.stop();
                    audioClip.close();
                    try {
                        audioStream.close();
                    } catch(IOException e) {}
                    running=false;
                }
            }
            else {
                if(frame.isDisplayable()) {
                    audioClip.close();
                    try {
                        audioStream.close();
                    } catch(IOException e) {}
                    play();
                    audioClip.start();
                    playCompleted=false;
                }
            }
        }
    }

    void play() {
        try {
            audioStream=AudioSystem.getAudioInputStream(Config.Instance().musicFile);
            AudioFormat format=audioStream.getFormat();
            DataLine.Info info=new DataLine.Info(Clip.class,format);
            audioClip=(Clip)AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException e1) {
            System.out.println("Error playing the audio file.");
            e1.printStackTrace();
        }
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
         
        if (type == LineEvent.Type.STOP) {
            playCompleted = true;
        }
 
    }
}