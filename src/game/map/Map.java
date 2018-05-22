package game.map;

import game.sprites.player.Player;

import java.awt.*;
import java.util.ArrayList;

public class Map {
    private Tile[][] map = new Tile[10][10];
    private Point boundaryCenter;
    private ArrayList<Player> players = new ArrayList<>();

    public Map(){

    }
}
