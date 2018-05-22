package game.sprites.player;

import game.main.Game;
import game.main.GameInfo;
import game.map.Location;
import game.sprites.inventory.Item;

import javax.imageio.ImageIO;
import java.awt.Graphics;
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

    public void render(Graphics g) {
        g.drawImage(image, Game.WIDTH/2 - 25, Game.HEIGHT/2 - 25, 50, 50, null);
        g.drawLine(Game.WIDTH/2, Game.HEIGHT/2, GameInfo.getInstance().getMouseX(), GameInfo.getInstance().getMouseY());
    }

}
