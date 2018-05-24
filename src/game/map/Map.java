package game.map;

import game.sprites.player.Player;

import java.awt.*;
import java.util.ArrayList;

public class Map {
    private Point boundaryCenter;
    private static ArrayList<Player> players = new ArrayList<>();

    public Map(){

    }

    public static void addPlayer(Player p) {
        players.add(p);
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void render(Graphics g, Location loc) {
        for(Player p : players) {
            p.render(g, loc);
        }
    }
}
