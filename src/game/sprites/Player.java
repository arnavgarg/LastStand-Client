package game.sprites;

import game.main.Game;
import game.main.GameInfo;
import game.map.Location;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {

    private int id;
    private String name;
    private Location loc;
    private int status;

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
        this.status = 1;
    }

    public Player(int id, String name, Location loc, double angle, int health, int status) {
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
        this.angle = angle;
        this.health = health;
        this.status = status;
    }

    public void tick() {
        angle = Math.atan2(GameInfo.getInstance().getMouseY() - Game.HEIGHT/2, GameInfo.getInstance().getMouseX() - Game.WIDTH/2);
    }

    private void drawGun(Graphics g, Location l) {
        g.translate(Game.WIDTH/2 - (int) (l.diffX(this.loc)), Game.HEIGHT/2 - (int) (l.diffY(this.loc)));
        ((Graphics2D)g).rotate(this.angle);
        g.drawImage(gun, 10, -3, 60, 7, null);
        ((Graphics2D)g).rotate(-this.angle);
        g.translate(-Game.WIDTH/2 + (int) (l.diffX(this.loc)), -Game.HEIGHT/2 + (int) (l.diffY(this.loc)));
    }

    public void render(Graphics g, Location l) {
        if(Math.abs(l.diffX(this.loc)) > 400 || Math.abs(l.diffY(this.loc)) > 300) {
            return;
        }
        g.drawImage(image, Game.WIDTH/2 - 25 - (int) (l.diffX(this.loc)), Game.HEIGHT/2 - 25 - (int) (l.diffY(this.loc)), 50, 50, null);
        drawGun(g, l);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        g.drawString(name, Game.WIDTH/2 - (3*name.length()) - (int) (l.diffX(this.loc)), Game.HEIGHT/2 - 27 - (int) (l.diffY(this.loc)));
    }

    public void set(Player p) {
        this.loc = p.loc;
        this.health = p.health;
        this.status = p.status;
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

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Player && this.id == ((Player) o).id;
    }

}

