package chess.game;

import javax.swing.JFrame;

import chess.Config;

import java.io.FileNotFoundException;
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
    boolean playCompleted=false, running=true;

    public MusicThread(JFrame frame) {
        super();
        this.frame=frame;
        play();
    }

    public void run() {
        if(audioClip==null)
            return;
        audioClip.start();
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
        } catch (Exception ex) {
            running=false;
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