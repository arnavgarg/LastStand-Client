package game.map;

import game.sprites.Rock;
import game.sprites.player.Player;

import java.awt.*;
import java.util.ArrayList;

public class Map {

    private Point boundaryCenter;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Rock> rocks;

    public Map(ArrayList<Rock> rocks) {
        this.rocks = rocks;
    }

    public void tick() {
        for (Player p : players) {
            p.tick();
        }
    }

    public void applyUpdates(ArrayList<Player> playersUpdated) {
        for (Player player : playersUpdated) {
            int i = players.indexOf(player);
            if (i != -1) {
                players.set(i, player);
            } else {
                players.add(player);
            }
        }
    }

    public void renderRocks(Graphics g, Location l) {
        for(Rock r: rocks) {

        }
    }
  
    public void addPlayer(Player p) {
        players.add(p); 
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void render(Graphics g, Location loc) {
        for(Player p : players) {
            p.render(g, loc);
        }
    }
}
