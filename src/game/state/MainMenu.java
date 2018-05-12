package game.state;

import game.main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MainMenu extends State{
    private boolean hJoin = false;
    private boolean hHelp = false;
    private Image start;
    private Image help;

    public void render(Graphics g){
        if(start==null) {
            try {
                start = ImageIO.read(getClass().getResource("/start.png"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        if(help == null) {
            try {
                help = ImageIO.read(getClass().getResource("/help.png"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        g.drawImage(start, Game.WIDTH/2 - 75, Game.HEIGHT/2 + 75, 150, 50, null);
        g.drawImage(help, Game.WIDTH/2 - 75, Game.HEIGHT/2 + 150, 150, 50, null);
    }

    public void tick(){
        //TODO - if the mouse is in bounding boxes, highlight buttons
    }

    public void processMouseEvent(MouseEvent me) {

    }
}
