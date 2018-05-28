package game.main;

import javax.sound.sampled.*;
import java.io.IOException;

public class Music {
    private static String[] music = new String[2];
    private static AudioInputStream rickroll;
    private static Clip clip;

    public static void playMusic() {
        System.out.println("playing music");
        try {
            //random generate an index
            int i = (int) (Math.random() * music.length);

            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Music.class.getResource("/songs/" + music[i])));
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
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

    public static boolean isRunning() {
        return clip.isActive();
    }


    public static void initMusic(){
            try {
                rickroll = AudioSystem.getAudioInputStream(Music.class.getResource("/songs/NeverGonnaGiveYouUp.wav"));
                music[0] = "RocketRace.wav";
                music[1] = "Warhead.wav";
            }catch (IOException|UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
    }
}
