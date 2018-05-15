package game.state;

import game.main.Game;
import game.main.GameInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MenuState extends State {

    private boolean hJoin = false;
    private boolean hHelp = false;
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
        int x = GameInfo.getInstance().getMouseX();
        int y = GameInfo.getInstance().getMouseY();

        if(x >= Game.WIDTH/2 - 75 && x <= Game.WIDTH/2 + 75 && y >= Game.HEIGHT/2 + 75 && y <= Game.HEIGHT/2 + 125){
            hJoin = true;
        }else {
            hJoin = false;
        }

        if(x >= Game.WIDTH/2 - 75 && x <= Game.WIDTH/2 + 75 && y >= Game.HEIGHT/2 + 150 && y <= Game.HEIGHT/2 + 200){
            hHelp = true;
        }else {
            hHelp = false;
        }
    }

    public void processMouseEvent(MouseEvent me) {
        if(hJoin){
            Game.loadState(new ConnectState());
        }
        if(hHelp){
            Game.loadState(new HelpState());
        }
    }

}
