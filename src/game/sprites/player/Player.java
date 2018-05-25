

import game.main.Game;
import game.main.GameInfo;
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
    
    public int getID() {return id;}

    @Override
    public boolean equals(Object o) {
        if(o instanceof Player){
            return this.id == ((Player) o).id;
        }
        return false;
    }
}


import game.main.Game;
import game.main.GameInfo;
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

    public int getID() {return id;}

    public void render(Graphics g, Location l) {
        g.drawImage(image, Game.WIDTH/2 - 25, Game.HEIGHT/2 - 25, 50, 50, null);
        double angle = Math.atan2(GameInfo.getInstance().getMouseY() - Game.HEIGHT/2,
                GameInfo.getInstance().getMouseX() - Game.WIDTH/2);
        g.drawLine(Game.WIDTH/2, Game.HEIGHT/2, (int) (Game.WIDTH/2 + (50 * Math.cos(angle))),
                (int) (Game.HEIGHT/2 + (50 * Math.sin(angle))));
        g.setColor(Color.BLACK);
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        g.drawString(name, Game.WIDTH/2 - (3*name.length()), Game.HEIGHT/2 - 27);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Player){
            return this.id == ((Player) o).id;
        }
        return false;
    }
}
