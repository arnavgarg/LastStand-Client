package game.state;

import game.main.Game;
import game.main.GameInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class HelpState extends State{
    private Image up, left, right, down, e, mouse, arrow, arrowR, f;
    private boolean highL, highR = false;

    public void render(Graphics g){
        if(up == null) {
            try {
                up = ImageIO.read(getClass().getResource("/w.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(left == null) {
            try {
                left = ImageIO.read(getClass().getResource("/a.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(right == null) {
            try {
                right = ImageIO.read(getClass().getResource("/d.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(down == null) {
            try {
                down = ImageIO.read(getClass().getResource("/s.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(e == null) {
            try {
                e = ImageIO.read(getClass().getResource("/e.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(mouse == null) {
            try {
                mouse = ImageIO.read(getClass().getResource("/mouse.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

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

        if(f == null) {
            try {
                f = ImageIO.read(getClass().getResource("/f.png"));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Draw keyboard keys
        g.drawImage(left, Game.WIDTH/5 - 40, Game.HEIGHT/2, 40, 40, null);
        g.drawImage(down, Game.WIDTH/5, Game.HEIGHT/2, 40, 40, null);
        g.drawImage(right, Game.WIDTH/5 + 40, Game.HEIGHT/2, 40, 40, null);
        g.drawImage(up, Game.WIDTH/5, Game.HEIGHT/2 - 40, 40, 40, null);
        g.drawImage(e, Game.WIDTH/5 + 90, Game.HEIGHT/2 - 70, 40, 40, null);
        g.drawImage(f, Game.WIDTH/5 + + 130, Game.HEIGHT/2, 40, 40, null);

        //Draw key labels
        g.setFont(new Font("Silon", Font.BOLD, 20));
        g.drawString("Movement", Game.WIDTH/5 - 33, Game.HEIGHT/2 + 59);
        g.drawString("Interact", Game.WIDTH/5 + 70, Game.HEIGHT/2 - 75);
        g.drawString("Use", Game.WIDTH/5 + 130, Game.HEIGHT/2 + 59);

        //Draw mouse labels
        g.drawString("Move Cursor", Game.WIDTH - (Game.WIDTH/5) - 65, Game.HEIGHT/2 + 125);
        g.drawString("Fire", Game.WIDTH - (Game.WIDTH/5) - 85, Game.HEIGHT/2);
        g.drawString("Reload", Game.WIDTH - (Game.WIDTH/5) + 35, Game.HEIGHT/2);

        //Draw arrow labels
        g.setFont(new Font("Silon", Font.PLAIN, 12));
        g.drawString("Back", 6, 40);
        g.drawString("Play", Game.WIDTH-35, 40);

        //Draw Mouse
        g.drawImage(mouse, Game.WIDTH - (Game.WIDTH/5) - 50, Game.HEIGHT/2 - 105, 100, 210, null);

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
