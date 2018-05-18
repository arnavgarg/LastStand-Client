package game.state;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class State {

    public abstract void render(Graphics g);

    public abstract void tick();

    public abstract void processMouseEvent(MouseEvent me);
    
    public abstract void processKeyEvent(KeyEvent ke);

    public abstract void processKeyEventPress(KeyEvent ke);

    public abstract void processKeyEventRelease(KeyEvent ke);
}
