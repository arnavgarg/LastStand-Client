package game.sprites.player;

import game.sprites.inventory.Item;

import java.awt.*;

public class Player {
    private Point loc;
    private Item[] inventory = new Item[5];

    public Player(Point p){
        this.loc = p;
    }
}
