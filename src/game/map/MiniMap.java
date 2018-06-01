package game.map;

import game.sprites.Rock;
import game.sprites.Player;

import java.awt.*;
import java.util.ArrayList;

public class MiniMap {
    private Player player;
    private Map map;
    private ArrayList<Rock> rocks;
    private ArrayList<Player> players;

    public MiniMap(Player p, Map m) {
        player = p;
        map = m;
    }

    public void render(Graphics g, Location loc) {//draws a mini map at location, 120 x 90
        //draw rectangle
        g.setColor(Color.WHITE);
        g.fillRect((int) loc.getX(), (int) loc.getY(), 120, 90);
        g.setColor(Color.BLACK);
        g.drawRect((int) loc.getX(), (int) loc.getY(), 120, 90);

        //draw player
        g.setColor(Color.ORANGE);
        g.fillOval((int) loc.getX() + 57, (int) loc.getY() + 42, 6, 6);

        //draw rocks
        g.setColor(Color.BLACK);
        rocks = map.getRocks();
        for(Rock r:rocks) {
            Location temp = r.getLoc().shiftLoc(15, 85);
            if(Math.abs(temp.diffX(player.getLoc())) > 720 || Math.abs(temp.diffY(player.getLoc())) > 540) {
                continue;
            }
            g.fillRect((int) loc.getX() + 60 - 1 + (int) (temp.diffX(player.getLoc())/12),
                    (int) loc.getY() + 45 - 1 + (int) (temp.diffY(player.getLoc())/12), 2, 2);
        }

        //draw enemies
        g.setColor(Color.RED);
        players = map.getPlayers();
        for(Player p:players) {
            if(Math.abs(p.getLoc().diffX(player.getLoc())) > 720 || Math.abs(p.getLoc().diffY(player.getLoc())) > 540) {
                continue;
            }
            if(p.getLoc().equals(player.getLoc())) continue;
            g.fillOval((int) loc.getX() + 60 - 2 + (int) (p.getLoc().diffX(player.getLoc())/12),
                    (int) loc.getY() + 45 - 2 + (int) (p.getLoc().diffY(player.getLoc())/12), 4, 4);
        }
    }
}
