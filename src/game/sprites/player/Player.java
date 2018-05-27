package game.sprites.player;

import game.main.Game;
import game.map.Location;
import game.sprites.inventory.Item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {

    private int id;
    private String name;
    private Location loc;
    private Item[] inventory = new Item[5];

    private static BufferedImage image;

    public Player(int id, String name, Location loc) {
        if (image == null) {
            try {
                image = ImageIO.read(getClass().getResource("/circle.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.id = id;
        this.name = name;
        this.loc = loc;
    }
    
    public void render(Graphics g, Location l) {
        g.drawImage(image, Game.WIDTH/2 - 25 - (int) (l.diffX(this.loc)), Game.HEIGHT/2 - 25 - (int) (l.diffY(this.loc)), 50, 50, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        g.drawString(name, Game.WIDTH/2 - (3*name.length()) - (int) (l.diffX(this.loc)), Game.HEIGHT/2 - 27 - (int) (l.diffY(this.loc)));
    }
    
    public int getId() {return id;}

    public Location getLoc() {return loc;}

    @Override
    public boolean equals(Object o) {
        if(o instanceof Player){
            return this.id == ((Player) o).id;
        }
        return false;
    }
}

