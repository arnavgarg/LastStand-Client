package game.state;

import game.main.Game;
import game.main.GameInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GameState extends State {
    Image player;

    public GameState() {

	}

	public void checkImages() {
        if(player == null) {
            try {
                player = ImageIO.read(getClass().getResource("/circle.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void render(Graphics g) {
        checkImages();
        drawMap(g);
        drawPlayer(g);

    }

    private void drawMap(Graphics g) {
//        for(Object o : GameInfo.getInstance().getEntities()) {
//            //
//        }
    }

    private void drawPlayer(Graphics g) {
        g.drawImage(player, Game.WIDTH/2 - 25, Game.HEIGHT/2 - 25, 50, 50, null);
        g.drawLine(Game.WIDTH/2, Game.HEIGHT/2, GameInfo.getInstance().getMouseX(), GameInfo.getInstance().getMouseY());
    }

    public void tick(){

    }

    public void processMouseEvent(MouseEvent me) {

    }

    public void processKeyEventPress(KeyEvent ke){

    }

    public void processKeyEventRelease(KeyEvent ke) {

    }
  
}
