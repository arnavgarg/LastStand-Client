package game.state;

import game.main.Game;
import game.main.GameInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MenuState extends State {

    private Image join;
    private Image help;
    
    public MenuState() {
        try {
            join = ImageIO.read(getClass().getResource("/res/joinbutton.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        
        try {
            help = ImageIO.read(getClass().getResource("/res/helpbutton.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        
        Image join = join.getScaledInstance(160, 55, Image.SCALE_DEFAULT);
        Image help = help.getScaledInstance(55, 55, Image.SCALE_DEFAULT);
    }

    public void render(Graphics g){
        g.drawImage(join, Game.WIDTH/2 - 80, Game.HEIGHT/2 - 55, null);
        g.drawImage(help, Game.WIDTH/2 - 23, Game.HEIGHT/2 + 55, null);
    }

    public void tick(){
        // don't know what we should put in here
    }

    public void processMouseEvent(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        
        if(x > Game.WIDTH/2 - 80 && x < Game.WIDTH/2 + 80 && y > Game.HEIGHT/2 - 55 && y < Game.HEIGHT/2){
            Game.loadState(new ConnectState());
        }
        if(x > Game.WIDTH/2 - 23 && x < Game.WIDTH/2 + 22 && y > Game.HEIGHT/2 + 55 && y < Game.HEIGHT/2 + 110){
            Game.loadState(new HelpState());
        }
    }

}
