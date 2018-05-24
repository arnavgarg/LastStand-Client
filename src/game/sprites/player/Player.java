package game.sprites.player;

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

    private double angle;
    private int health; //0-100

    private static BufferedImage image;
    private static BufferedImage gun;

    public Player(int id, String name, Location loc) {
        if (image == null) {
            try {
                image = ImageIO.read(getClass().getResource("/circle.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (gun == null) {
            try {
                gun = ImageIO.read(getClass().getResource("/ClipGun.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.id = id;
        this.name = name;
        this.loc = loc;
        this.angle = 1.57;
        this.health = 100;
    }

    public void updateAngle() {
        angle = Math.atan2(GameInfo.getInstance().getMouseY() - Game.HEIGHT/2, GameInfo.getInstance().getMouseX() -
                Game.WIDTH/2);
    }

    private void drawGun(Graphics g, Location l) {
        g.translate(Game.WIDTH/2 - (int) (l.diffX(this.loc)), Game.HEIGHT/2 - (int) (l.diffY(this.loc)));
        ((Graphics2D)g).rotate(this.angle);
        g.drawImage(gun, 10, -2, 50, 5, null);
        ((Graphics2D)g).rotate(-this.angle);
        g.translate(-Game.WIDTH/2, -Game.HEIGHT/2);
    }

    public void render(Graphics g, Location l) {
        g.drawImage(image, Game.WIDTH/2 - 25 - (int) (l.diffX(this.loc)),
                Game.HEIGHT/2 - 25 - (int) (l.diffY(this.loc)), 50, 50, null);
        drawGun(g, l);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        g.drawString(name, Game.WIDTH/2 - (3*name.length()) - (int) (l.diffX(this.loc)),
                Game.HEIGHT/2 - 27 - (int) (l.diffY(this.loc)));
    }

    public Location getLoc() {
        return loc;
    }

    public int getId() {
        return id;
    }

    public double getAngle() {
        return angle;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Player){
            return this.id == ((Player) o).id;
        }
        return false;
    }
}
