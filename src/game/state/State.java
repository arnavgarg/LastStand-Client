package game.state;

import game.main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class State {

    public abstract void render(Graphics g);

    public abstract void tick();

    public abstract void processMouseEvent(MouseEvent me);

    public abstract void processKeyEventPress(KeyEvent ke);

    public abstract void processKeyEventRelease(KeyEvent ke);

    static void drawBackground(Graphics g) {
        g.setColor(new Color(0, 180, 0));
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
    }
}
