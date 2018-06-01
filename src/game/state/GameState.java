package game.state;

import game.main.Game;
import game.main.Music;
import game.log.*;
import game.map.Location;
import game.map.Map;
import game.map.MiniMap;
import game.sprites.Rock;

import game.sprites.Player;
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
    private MiniMap mm;
  
    private boolean up, left, right, down;
    private int tempCounter = 0;

    public GameState(Player player, ArrayList<Rock> rocks) {
        this.player = player;
        map = new Map(player, rocks, this);
        map.addPlayer(player);
        mm = new MiniMap(player, map);
        if(player.getName().equals("RickAstley")) {
            Music.playSpecial("rickroll");
        }else if(player.getName().equals("JamesBond")){
            Music.playSpecial("spy");
        }else {
            Music.playMusic();
        }
        log = new Log(player.getId());
    }
  
    public void render(Graphics g) {
        player.render(g, player.getLoc());
        map.render(g, player.getLoc());
        drawHUD(g);
        mm.render(g, new Location(Game.WIDTH - 140, 20));
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
        if(up){
            log.addEntry(new Entry(0, new String[0]));
        }
        if(right){
            log.addEntry(new Entry(1, new String[0]));
        }
        if(down){
            log.addEntry(new Entry(2, new String[0]));
        }
        if(left){
            log.addEntry(new Entry(3, new String[0]));
        }

        map.tick();
        player.tick();

        try {
            updateServer();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Communicating with Server");
            System.exit(0);
        }
        log.clear();

        if(!Music.isRunning()) {
            Music.playMusic();
        }
    }

    public void processMouseEvent(MouseEvent me) {
        if(me.getButton() == 1) {
            Music.shoot();
            String[] temp = {player.getAngle() + ""};
            log.addEntry(new Entry(4, temp));
        }
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
            System.out.println("[  ERROR  ] Response code of " + responseCode + " after attempting to get data");
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
            players.add(new Player(playerObj.getInt("id"), playerObj.getString("name"), new Location(playerObj.getDouble("x"), playerObj.getDouble("y")), playerObj.getDouble("angle"), playerObj.getInt("health"), playerObj.getInt("status")));
        }

        map.applyUpdates(players);
    }

    public void death() {
        int deathButton = JOptionPane.YES_NO_OPTION;
        deathButton = JOptionPane.showConfirmDialog (null, "Keep playing?","YOU DIED", deathButton);

        if(deathButton == JOptionPane.YES_OPTION) {
            Music.turnOff();
            ConnectState restart = new ConnectState();
            try {
                restart.startGame(player.getName());
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "ERROR CONNECTING TO SERVER");
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }
  
}
