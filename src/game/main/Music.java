package game.main;

import javax.sound.sampled.*;
import java.io.IOException;

public class Music {
    private static String[] music = new String[4];
    private static AudioInputStream rickroll, spy;
    private static Clip clip;

    public static void playMusic() {
        System.out.println("playing music");
        try {
            //random generate an index
            int i = (int) (Math.random() * music.length);

            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Music.class.getResource("/songs/" + music[i])));
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public static void playSpecial(String s) {
        try {
            clip = AudioSystem.getClip();
            switch (s){
                case "rickroll":
                    clip.open(rickroll);
                    break;
                case "spy":
                    clip.open(spy);
                    break;
            }
            clip.start();
        }catch (LineUnavailableException|IOException e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        clip.stop();
        clip = null;
    }

    public static void shoot() {
        try {
            Clip c = AudioSystem.getClip();
            c.open(AudioSystem.getAudioInputStream(Music.class.getResource("/songs/shot.wav")));
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f); // Reduce volume by 10 decibels.
            c.start();
        }catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public static boolean isRunning() {
        return clip.isActive();
    }


    public static void initMusic(){
            try {
                rickroll = AudioSystem.getAudioInputStream(Music.class.getResource("/songs/NeverGonnaGiveYouUp.wav"));
                spy = AudioSystem.getAudioInputStream(Music.class.getResource("/songs/Spy.wav"));
                music[0] = "RocketRace.wav";
                music[1] = "Warhead.wav";
                music[2] = "Chase.wav";
                music[3] = "Nightfall.wav";
            }catch (IOException|UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
    }

    public static void turnOff() {
        clip.stop();
    }
}
