package game.state;

import game.main.Game;
import game.main.GameInfo;
import game.main.Music;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MenuState extends State {

    private boolean hJoin = false;
    private boolean hHelp = false;
    private static Image join, joinH;
    private static Image help, helpH;
    private static Image title;

    public MenuState() {
        Music.initMusic();
    }

    public void render(Graphics g){
        if(join == null){
            try{
                join = ImageIO.read(getClass().getResource("/join.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(help == null) {
            try{
                help = ImageIO.read(getClass().getResource("/help.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(joinH == null) {
            try{
                joinH = ImageIO.read(getClass().getResource("/joinH.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(helpH == null) {
            try{
                helpH = ImageIO.read(getClass().getResource("/helpH.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(title == null) {
            try {
                title = ImageIO.read(getClass().getResource("/title.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        State.drawBackground(g);
        g.drawImage(title, 0, 0, Game.WIDTH, Game.HEIGHT/2 - 10, null);

        if(hJoin) {
            g.drawImage(joinH, Game.WIDTH/2 - 75, Game.HEIGHT/2 + 75, 150, 50, null);
        }else {
            g.drawImage(join, Game.WIDTH/2 - 75, Game.HEIGHT/2 + 75, 150, 50, null);
        }

        if(hHelp) {
            g.drawImage(helpH, Game.WIDTH/2 - 75, Game.HEIGHT/2 + 150, 150, 50, null);
        }else {
            g.drawImage(help, Game.WIDTH/2 - 75, Game.HEIGHT/2 + 150, 150, 50, null);
        }
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
