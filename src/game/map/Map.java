package game.map;

import game.sprites.Rock;
import game.sprites.Player;

import java.awt.*;
import java.util.ArrayList;

public class Map {

    private Player mainPlayer;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Rock> rocks;

    public Map(Player p, ArrayList<Rock> rocks) {
        this.mainPlayer = p;
        this.rocks = rocks;
    }

    public void tick() {
        for (Player p : players) {
            p.tick();
        }
    }

    public void applyUpdates(ArrayList<Player> playersUpdated) {
        for (Player player : playersUpdated) {
            if (player.equals(mainPlayer)) {
                mainPlayer.set(player);
            } else {
                int i = players.indexOf(player);
                if (i != -1) {
                    players.set(i, player);
                } else {
                    players.add(player);
                }
            }
        }
    }

    private void renderRocks(Graphics g, Location l) {
        for(Rock r: rocks) {
            r.render(g, l);
        }
    }
  
    public void addPlayer(Player p) {
        players.add(p); 
    }

    public ArrayList<Rock> getRocks() {
        return rocks;
    }

    public void render(Graphics g, Location loc) {
        for(Player p : players) {
            p.render(g, loc);
        }
        renderRocks(g, loc);
        g.drawString(loc.toString(), 10, 15);
    }
}
