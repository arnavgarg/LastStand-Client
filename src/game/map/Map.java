package game.map;

import game.sprites.player.Player;

import java.awt.*;
import java.util.ArrayList;

public class Map {
    private Point boundaryCenter;
    private static ArrayList<Player> players = new ArrayList<>();

    public Map(){
        //testing temporary players
        players.add(new Player(2, "Number 1", new Location(0,0)));
        players.add(new Player(3, "Number 2", new Location(1000,1000)));
        players.add(new Player(4, "Number 3", new Location(2000,2000)));
        players.add(new Player(5, "Number 4", new Location(3000,3000)));
        players.add(new Player(6, "Number 5", new Location(500,500)));
        players.add(new Player(7, "Number 6", new Location(1500,1500)));
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
