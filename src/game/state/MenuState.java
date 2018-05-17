package game.state;

import game.main.Game;
import game.main.GameInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MenuState extends State {

    private boolean hJoin = false;
    private boolean hHelp = false;
    private Image start;
    private Image help;

    public void render(Graphics g){
        if(start==null) {
            try {
                start = ImageIO.read(getClass().getResource("/start.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(help == null) {
            try {
                help = ImageIO.read(getClass().getResource("/help.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        g.drawImage(start, Game.WIDTH/2 - 75, Game.HEIGHT/2 + 75, 150, 50, null);
        g.drawImage(help, Game.WIDTH/2 - 75, Game.HEIGHT/2 + 150, 150, 50, null);
    }

    public void tick(){
        int x = GameInfo.getInstance().getMouseX();
        int y = GameInfo.getInstance().getMouseY();

        hJoin = x >= Game.WIDTH / 2 - 75 && x <= Game.WIDTH / 2 + 75 && y >= Game.HEIGHT / 2 + 75 && y <= Game.HEIGHT / 2 + 125;
        hHelp = x >= Game.WIDTH / 2 - 75 && x <= Game.WIDTH / 2 + 75 && y >= Game.HEIGHT / 2 + 150 && y <= Game.HEIGHT / 2 + 200;
    }

    public void processMouseEvent(MouseEvent me) {
        if(hJoin){
            Game.loadState(new ConnectState());
        }
        if(hHelp){
            Game.loadState(new HelpState());
        }
    }

    public void processKeyEventPress(KeyEvent ke) {

    }

    public void processKeyEventRelease(KeyEvent ke) {

    }
}
