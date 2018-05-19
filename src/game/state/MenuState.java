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

    private void drawBackground(Graphics g) {
        g.setColor(new Color(0, 180, 0));
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
    }

    public void render(Graphics g){
        drawBackground(g);

        g.setColor(Color.BLACK);
        g.drawRect(Game.WIDTH/2 - 75, Game.HEIGHT/2 + 75, 150, 50);
        g.drawRect(Game.WIDTH/2 - 75, Game.HEIGHT/2 + 150, 150, 50);

        g.setFont(new Font("Helvetica", Font.BOLD, 25));

        if(hJoin) {
            g.setColor(new Color(34, 73, 34));
            g.fillRect(Game.WIDTH/2 - 75, Game.HEIGHT/2 + 75, 150, 50);
            g.setColor(Color.WHITE);
            g.drawString("Join", Game.WIDTH/2 - 28, Game.HEIGHT/2 + 108);
        }else {
            g.setColor(Color.BLACK);
            g.drawString("Join", Game.WIDTH/2 - 28, Game.HEIGHT/2 + 108);
        }

        if(hHelp) {
            g.setColor(new Color(34, 73, 34));
            g.fillRect(Game.WIDTH/2 - 75, Game.HEIGHT/2 + 150, 150, 50);
            g.setColor(Color.WHITE);
            g.drawString("Help", Game.WIDTH/2 - 28, Game.HEIGHT/2 + 183);
        }else {
            g.setColor(Color.BLACK);
            g.drawString("Help", Game.WIDTH/2 - 28, Game.HEIGHT/2 + 183);
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
