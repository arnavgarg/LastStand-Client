package game.main;

import javax.sound.sampled.*;
import java.io.IOException;

public class Music {
    private static AudioInputStream[] music = new AudioInputStream[2];
    private static AudioInputStream rickroll;

    public static void playMusic() {
        //random generate an index
        int i= (int) (Math.random()*music.length);
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(music[i]);
            clip.start();
        }catch (LineUnavailableException|IOException e) {
            e.printStackTrace();
        }
    }

    public static void rickRoll() {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(rickroll);
            clip.start();
        }catch (LineUnavailableException|IOException e) {
            e.printStackTrace();
        }
    }

    public static void initMusic(){
            try {
                rickroll = AudioSystem.getAudioInputStream(Music.class.getResource("/songs/NeverGonnaGiveYouUp.wav"));
                music[0] = AudioSystem.getAudioInputStream(Music.class.getResource("/songs/RocketRace.wav"));
                music[1] = AudioSystem.getAudioInputStream(Music.class.getResource("/songs/Warhead.wav"));
            }catch (IOException|UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
    }
}
