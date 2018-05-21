package game.state;

import game.main.Game;
import game.main.GameInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ConnectState extends State{

    private String name = "";
    private boolean highL, highR;
    private Image arrowR, arrow;

    public void render(Graphics g){
        MenuState.drawBackground(g);//TODO put in state

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

        //draw box
        g.setColor(Color.WHITE);
        g.fillRect(Game.WIDTH/2 - 100, Game.HEIGHT/2 - 15, 200, 30);
        g.setColor(Color.BLACK);
        g.drawRect(Game.WIDTH/2 - 100, Game.HEIGHT/2 - 15, 200, 30);

        //draw text
        g.setFont(new Font("Helvetica", Font.BOLD, 20));
        g.drawString(name, Game.WIDTH/2 - (name.length()*5), Game.HEIGHT/2 + 5);

        //Draw arrow labels
        g.setFont(new Font("Silon", Font.PLAIN, 12));
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

    public void processMouseEvent(MouseEvent me){
        if(highL) {
            Game.loadState(new MenuState());
        }
        if(highR) {
            Game.loadState(new GameState());
        }
    }

    public void processKeyEventPress(KeyEvent ke) {
        if(name.length() < 10) {
            if((ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z') || (ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'z')) {
                name += ke.getKeyChar();
            }
        }
        if(ke.getKeyCode() == 8 && name.length()>0) {
            name = name.substring(0, name.length()-1);
        }
    }

    public void processKeyEventRelease(KeyEvent ke) {

    }

}
