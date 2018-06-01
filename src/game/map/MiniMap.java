package game.map;

import game.sprites.Rock;
import game.sprites.Player;

import java.awt.*;
import java.util.ArrayList;

public class MiniMap {
    private Player player;
    private Map map;
    private ArrayList<Rock> rocks;

    public MiniMap(Player p, Map m) {
        player = p;
        map = m;
    }

    public void render(Graphics g, Location loc) {//draws a mini map at location, 120 x 90
        g.setColor(Color.WHITE);
        g.fillRect((int) loc.getX(), (int) loc.getY(), 120, 90);
        g.setColor(Color.BLACK);
        g.drawRect((int) loc.getX(), (int) loc.getY(), 120, 90);
        g.setColor(Color.ORANGE);
        g.fillOval((int) loc.getX() + 57, (int) loc.getY() + 42, 6, 6);

        g.setColor(Color.BLACK);
        rocks = map.getRocks();
    }
}
