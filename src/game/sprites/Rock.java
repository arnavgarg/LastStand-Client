package game.sprites;

import game.main.Game;
import game.map.Location;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Rock {

    private Location loc;
    private static Image rock;

    public Rock(Location loc) {
        this.loc = loc;
        if(rock == null) {
            try {
                rock = ImageIO.read(Rock.class.getResource("/rock.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Location getLoc() {
        return loc;
    }

    public void render(Graphics g, Location l) {
        if(Math.abs(l.diffX(this.loc)) > 400 || Math.abs(l.diffY(this.loc)) > 400) {
            return;
        }
        g.drawImage(rock, Game.WIDTH/2 - 20 - (int) (l.diffX(this.loc)), Game.WIDTH/2 - 20 - (int) (l.diffY(this.loc)),40, 40, null);
    }
}
