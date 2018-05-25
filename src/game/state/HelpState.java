package game.state;

import game.main.Game;
import game.main.GameInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class HelpState extends State{

    private Image arrow, arrowR, helpScreen;
    private boolean highL, highR = false;

    public void render(Graphics g){
        if(arrow == null) {
            try {
                arrow = ImageIO.read(getClass().getResource("/arrow.gif"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(arrowR == null) {
            try {
                arrowR = ImageIO.read(getClass().getResource("/arrowRed.gif"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(helpScreen == null) {
            try {
                helpScreen = ImageIO.read(getClass().getResource("/helpScreen.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        State.drawBackground(g);

        g.drawImage(helpScreen, 0, 0, Game.WIDTH, Game.HEIGHT, null);

        //Draw arrow labels
        g.setFont(new Font("Silon", Font.PLAIN, 12));
        g.setColor(Color.BLACK);
        g.drawString("Back", 6, 40);
        g.drawString("Play", Game.WIDTH-35, 40);

        //Draw arrows & highlight
        if(highL) {
            g.drawImage(arrowR, 0, 0, 36, 30, null);
        }else {
            g.drawImage(arrow, 0, 0, 36, 30, null);
        }

        if(highR) {
            g.drawImage(arrowR, Game.WIDTH, 0, -36, 30, null);
        }else {
            g.drawImage(arrow, Game.WIDTH, 0, -36, 30, null);
        }
    }

    public void tick(){
        int x = GameInfo.getInstance().getMouseX();
        int y = GameInfo.getInstance().getMouseY();

        if(y >= 0 && y <= 30){
            highL = x >= 0 && x <= 36;
            highR = x >= Game.WIDTH - 36 && x <= Game.WIDTH;
        }
    }

    public void processMouseEvent(MouseEvent me) {
        if(highL) {
            Game.loadState(new MenuState());
        }
        if(highR) {
            Game.loadState(new ConnectState());
        }
    }

    public void processKeyEventPress(KeyEvent ke) {

    }

    public void processKeyEventRelease(KeyEvent ke) {

    }

}
