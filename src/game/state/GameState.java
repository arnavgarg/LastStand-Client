package game.state;

import game.main.Game;
import game.map.Location;
import game.map.Map;
import game.sprites.player.Player;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.json.JSONObject;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GameState extends State {

    private Player player;

    private final String address = "http://54.201.138.236:8080/";//http://localhost:8080

    public GameState(String name) {
        try {
            player = createPlayer(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map.addPlayer(player);

        playMusic();
    }

    private void playMusic() {
//        Media song = new Media(getClass().getResource("/Rick Astley - Never Gonna Give You Up.mp3").toURI());
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource("/songs/" +
                    "NeverGonnaGiveYouUp.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        }catch (IOException e) {

        }catch (LineUnavailableException e) {

        }catch (UnsupportedAudioFileException e) {

        }
    }
    
    public void render(Graphics g) {
        player.updateAngle();
        Map.render(g, player.getLoc());
        drawGUI(g);
    }

    private void drawGUI(Graphics g){
        //draw health bar
        g.setColor(Color.BLACK);
        g.drawRect(Game.WIDTH/2 - 201, Game.HEIGHT - 31, 402, 22);
        g.setColor(Color.CYAN);
        g.fillRect(Game.WIDTH/2 - 201, Game.HEIGHT - 30, player.getHealth()*4, 20);
        g.setColor(Color.WHITE);
        g.fillRect(Game.WIDTH/2 - 201 + player.getHealth()*4, Game.HEIGHT - 30, 400 - (player.getHealth()*4), 20);

        //draw inventory squares
        g.setColor(Color.BLACK);

    }

    public void tick(){

    }

    public void processMouseEvent(MouseEvent me) {

    }

    public void processKeyEventPress(KeyEvent ke){

    }

    public void processKeyEventRelease(KeyEvent ke) {

    }

    private Player createPlayer(String name) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(address).openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoInput(true);
        con.setDoOutput(true);

        JSONObject json = new JSONObject();
        json.put("name", name);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(json.toString());
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            System.out.println("[  ERROR  ] Response code of " + responseCode);
            System.exit(1);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        JSONObject res = new JSONObject(response.toString());
        return new Player(res.getInt("id"), res.getString("name"), new Location(res.getDouble("x"), res.getDouble("y")));
    }
  
}
