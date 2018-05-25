package game.state;

import game.log.*;
import game.map.Location;
import game.map.Map;
import game.sprites.player.Player;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GameState extends State {

    private Player player;
    private Log log;

    private final String address = "http://54.201.138.236:8080/";

    public GameState(String name) {
        try {
            player = createPlayer(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        log = new Log(player.getID());
    }
    
    public void render(Graphics g) {
        Map.render(g, player.getLoc(), player.getID());
        drawGUI(g);
    }

    private void drawGUI(Graphics g){

    }

    public void tick(){

    }

    public void processMouseEvent(MouseEvent me) {

    }

    public void processKeyEventPress(KeyEvent ke){

        Entry e;
        int c = ke.getExtendedKeyCode();

        if (c == KeyEvent.VK_W) {
            e = new Entry(0, new String[0]);
            log.addEntry(e);
        }

        if (c == KeyEvent.VK_D) {
            e = new Entry(1, new String[0]);
            log.addEntry(e);
        }

        if (c == KeyEvent.VK_S) {
            e = new Entry(2, new String[0]);
            log.addEntry(e);
        }

        if (c == KeyEvent.VK_A) {
            e = new Entry(3, new String[0]);
            log.addEntry(e);
        }

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
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        JSONObject res = new JSONObject(response.toString());
        return new Player(res.getInt("id"), res.getString("name"), new Location(res.getDouble("x"), res.getDouble("y")));
    }
  
}
