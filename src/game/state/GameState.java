package game.state;

import game.main.Game;
import game.main.Music;
import game.log.*;
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
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GameState extends State {

    private Map map;
    private Player player;
    private Log log;
  
    private boolean up, left, right, down;
    private int tempCounter = 0;

    private boolean drawBack = true;
  
    public GameState(Player player, ArrayList<Rock> rocks) {
        this.player = player;
        map = new Map(rocks);
        map.addPlayer(player);
        if(player.getName().equals("RickAstley")) {
            Music.rickRoll();
        }else {
            Music.playMusic();
        }
        log = new Log(player.getId());
    }
  
    public void render(Graphics g) {
        if(drawBack){
            Color c = g.getColor();
            g.setColor(new Color(0,100,0));
            g.fillRect(0,0,4000, 4000);
            g.setColor(c);
            drawBack = false;
        }
        map.render(g, player.getLoc());
        drawHUD(g);
    }

    private void drawHUD(Graphics g){
        //draw health bar
        g.setColor(Color.BLACK);
        g.drawRect(Game.WIDTH/2 - 151, Game.HEIGHT - 26, 302, 12);
        g.setColor(Color.CYAN);
        g.fillRect(Game.WIDTH/2 - 150, Game.HEIGHT - 25, player.getHealth()*3, 10);
        g.setColor(Color.WHITE);
        g.fillRect(Game.WIDTH/2 - 150 + player.getHealth()*3, Game.HEIGHT - 25, 300 -
                (player.getHealth()*3), 10);

        //draw inventory squares
        g.setColor(Color.BLACK);
        for(int i=0; i<5; i++) {
            g.drawRect(Game.WIDTH - 200 + (i*40), Game.HEIGHT-40, 40, 40);
        }
    }

    public void tick(){
        //temporary local movement
        tempCounter++;
        if(tempCounter/7 >=1) {
            if (left) {
                player.getLoc().setX(player.getLoc().getX()-10);
            }
            if (right) {
                player.getLoc().setX(player.getLoc().getX()+10);
            }
            if (up) {
                player.getLoc().setY(player.getLoc().getY()-10);
            }
            if (down) {
                player.getLoc().setY(player.getLoc().getY()+10);
            }
            tempCounter%=7;
        }

        if(up){
            log.addEntry(new Entry(0, new String[0]));
        }
        if(right){
            log.addEntry(new Entry(0, new String[0]));
        }
        if(down){
            log.addEntry(new Entry(0, new String[0]));
        }
        if(left){
            log.addEntry(new Entry(0, new String[0]));
        }

        map.tick();
      
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
        int code = ke.getKeyCode();
        switch (code){
            case 65:
                left = true;
                break;
            case 87:
                up = true;
                break;
            case 68:
                right = true;
                break;
            case 83:
                down = true;
                break;
        }
    }

    public void processKeyEventRelease(KeyEvent ke) {
        int code = ke.getKeyCode();
        switch (code){
            case 65:
                left = false;
                break;
            case 87:
                up = false;
                break;
            case 68:
                right = false;
                break;
            case 83:
                down = false;
                break;
        }
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
