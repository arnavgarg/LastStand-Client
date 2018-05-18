package game.state;

import game.main.GameInfo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;


public class ConnectState extends State{

    public void render(Graphics g){

    }

    public void tick(){
        //TODO - pull information from the server, and initialize variables
        int x = GameInfo.getInstance().getMouseX();
        int y = GameInfo.getInstance().getMouseY();

    }

    public void processMouseEvent(MouseEvent me){

    }
    
    public void processKeyEvent(KeyEvent ke) {}

    public void processKeyEventPress(KeyEvent ke) {

    }

    public void processKeyEventRelease(KeyEvent ke) {

    }
}
