package game.map;

import game.sprites.Rock;
import game.sprites.Player;
import game.state.GameState;

import java.awt.*;
import java.util.ArrayList;

public class Map {

    private Player mainPlayer;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Rock> rocks;
    private GameState game;

    public Map(Player p, ArrayList<Rock> rocks, GameState game) {
        this.mainPlayer = p;
        this.rocks = rocks;
        this.game = game;
    }

    public void tick() {
        for (Player p : players) {
            p.tick();
        }
    }

    public void applyUpdates(ArrayList<Player> playersUpdated) {
        for (Player player : playersUpdated) {
            if (player.equals(mainPlayer)) {
                if (player.getStatus() == 0) {
                    game.death();
                }
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

    public ArrayList<Player> getPlayers() {return players;}

    public void render(Graphics g, Location loc) {
        for(Player p : players) {
            if (p.getStatus() != 0) {
                p.render(g, loc);
            }
        }
        renderRocks(g, loc);
        g.drawString(loc.toString(), 10, 15);
    }
}
