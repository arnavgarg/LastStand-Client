package game.state;

import game.log.*;
import game.main.Game;
import game.map.Location;
import game.map.Map;
import game.sprites.Rock;
import game.sprites.player.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GameState extends State {

    private Map map;
    private Player player;
    private Log log;

    public GameState(Player player, ArrayList<Rock> rocks) {
        this.player = player;
        log = new Log(player.getId());
        map = new Map(rocks);
    }
    
    public void render(Graphics g) {
        map.render(g, player.getLoc(), player.getId());
        drawGUI(g);
    }

    private void drawGUI(Graphics g){

    }

    public void tick(){
        try {
            updateServer();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Connecting to Server");
            System.exit(0);
        }
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

    private void updateServer() throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(Game.ADDRESS).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoInput(true);
        con.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(log.marshal().toString());
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            System.out.println("[  ERROR  ] Response code of " + responseCode + " after GET request");
            System.exit(1);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        JSONObject res = new JSONObject(response.toString());
        JSONArray playerArr = res.getJSONArray("players");
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < playerArr.length(); i++) {
            JSONObject playerObj = playerArr.getJSONObject(i);
            players.add(new Player(playerObj.getInt("id"), playerObj.getString("name"), new Location(playerObj.getDouble("x"), playerObj.getDouble("y"))));
        }
        map.applyUpdates(players);
    }
  
}
