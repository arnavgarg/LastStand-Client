package game.state;

import game.main.GameInfo;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Connect extends State{
    public void render(Graphics g){

    }

    public void tick(){
        //TODO - pull information from the server, and initialize variables
        int x = GameInfo.getInstance().getMouseX();
        int y = GameInfo.getInstance().getMouseY();

    }

    public void processMouseEvent(MouseEvent me){

    }
}
