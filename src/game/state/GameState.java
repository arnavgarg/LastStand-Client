package game.state;

import game.main.Game;
import game.main.Music;
import game.log.*;
import game.map.Location;
import game.map.Map;
import game.sprites.player.Player;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GameState extends State {

    private Player player;
    private Log log;

    private final String address = "http://localhost:8080";//"http://54.201.138.236:8080/";//http://localhost:8080
    private boolean up, left, right, down;
    private int tempCounter = 0;

    private boolean drawBack = true;

    public GameState(String name) {
        try {
            player = createPlayer(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map.addPlayer(player);
        if(player.getName().equals("RickAstley")) {
            Music.rickRoll();
        }else {
            Music.playMusic();
        }
        Map m = new Map();
        log = new Log(player.getId());
    }
    
    public void render(Graphics g) {
        if(drawBack){
            System.out.println("here");
            Color c = g.getColor();
            g.setColor(new Color(0,100,0));
            g.fillRect(0,0,4000, 4000);
            g.setColor(c);
            drawBack = false;
        }
        player.updateAngle();
        Map.render(g, player.getLoc());
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

        Entry e;
        if(up){
            e = new Entry(0, new String[0]);
            log.addEntry(e);
        }
        if(right){
            e = new Entry(1, new String[0]);
            log.addEntry(e);
        }
        if(down){
            e = new Entry(2, new String[0]);
            log.addEntry(e);
        }
        if(left){
            e = new Entry(3, new String[0]);
            log.addEntry(e);
        }

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
        return new Player(res.getInt("id"), res.getString("name"),
                new Location(res.getDouble("x"), res.getDouble("y")));
    }
  
}
