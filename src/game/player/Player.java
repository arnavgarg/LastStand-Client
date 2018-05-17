package game.player;

import game.player.inventory.Item;

import java.awt.*;

public class Player {
    private Point loc;
    private Item[] inventory = new Item[5];

    public Player(Point p){
        this.loc = p;
    }
}
