package game.state;

import game.main.Game;
import game.main.GameInfo;
import game.map.Location;
import game.sprites.Rock;
import game.sprites.player.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ConnectState extends State{

    private String name = "";
    private boolean highL, highR;
    private Image arrowR, arrow;

    public void render(Graphics g){
        MenuState.drawBackground(g);//TODO put in state

        if(arrow == null) {
            try {
                arrow = ImageIO.read(getClass().getResource("/arrow.gif"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(arrowR == null) {
            try {
                arrowR = ImageIO.read(getClass().getResource("/arrowRed.gif"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        //draw box
        g.setColor(Color.WHITE);
        g.fillRect(Game.WIDTH/2 - 100, Game.HEIGHT/2 - 15, 200, 30);
        g.setColor(Color.BLACK);
        g.drawRect(Game.WIDTH/2 - 100, Game.HEIGHT/2 - 15, 200, 30);

        //draw text
        g.setFont(new Font("Helvetica", Font.BOLD, 20));
        g.drawString(name, Game.WIDTH/2 - (name.length()*5), Game.HEIGHT/2 + 5);

        //Draw arrow labels
        g.setFont(new Font("Silon", Font.PLAIN, 12));
        g.drawString("Back", 6, 40);
        g.drawString("Play", Game.WIDTH-35, 40);

        //Draw arrows & highlight
        if(highL) {
            g.drawImage(arrowR, 0, 0, 36, 30, null);
        }else {
            g.drawImage(arrow, 0, 0, 36, 30, null);
        }

        if(highR) {
            g.drawImage(arrowR, Game.WIDTH, 0, -36, 30, null);
        }else {
            g.drawImage(arrow, Game.WIDTH, 0, -36, 30, null);
        }
    }

    public void tick(){
        int x = GameInfo.getInstance().getMouseX();
        int y = GameInfo.getInstance().getMouseY();

        if(y >= 0 && y <= 30){
            highL = x >= 0 && x <= 36;
            highR = x >= Game.WIDTH - 36 && x <= Game.WIDTH;
        }
    }

    public void processMouseEvent(MouseEvent me){
        if(highL) {
            Game.loadState(new MenuState());
        }
        if(highR && name.length() != 0) {
            try {
                startGame(name);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error Connecting to Game Server");
            }
        }
    }

    public void processKeyEventPress(KeyEvent ke) {
        if(name.length() < 10) {
            if((ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z') || (ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'z')) {
                name += ke.getKeyChar();
            }
        }
        if(ke.getKeyCode() == 8 && name.length()>0) {
            name = name.substring(0, name.length()-1);
        }
    }

    public void processKeyEventRelease(KeyEvent ke) {}

    private void startGame(String name) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(Game.ADDRESS).openConnection();
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
        JSONObject pObj = res.getJSONObject("player");
        Player p = new Player(pObj.getInt("id"), pObj.getString("name"), new Location(pObj.getDouble("x"), pObj.getDouble("y")));

        JSONArray rArr = res.getJSONArray("rocks");
        ArrayList<Rock> rocks = new ArrayList<>();
        for (int i = 0; i < rArr.length(); i++) {
            JSONObject o = rArr.getJSONObject(i);
            rocks.add(new Rock(new Location(o.getDouble("x"), o.getDouble("y"))));
        }

        Game.loadState(new GameState(p, rocks));
    }

}
