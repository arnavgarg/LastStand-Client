package game.map;

import game.sprites.player.Player;

import java.awt.*;
import java.util.ArrayList;

public class Map {
    private Point boundaryCenter;
    private static ArrayList<Player> players = new ArrayList<>();

    public Map(){

    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void render(Graphics g, Location loc, int id) {
        for(Player p : players) {
            if(p.getId() == id) {
                p.render(g);
            }else if(p.getLoc().diffX(loc) <= 400 && p.getLoc().diffY(loc) <= 300) {
                p.render(g, loc);
            }
        }
    }
}
