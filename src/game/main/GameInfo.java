package game.main;

import java.awt.*;

public class GameInfo {

    //keeps track of mouse info
    private GameInfo() {}

    private static GameInfo instance = null;
    private Point mouseLoc;

    public static GameInfo getInstance(){
        if(instance == null){
            instance = new GameInfo();
        }
        return instance;
    }

    public void setMouseP(Point loc){
        mouseLoc = loc;
    }

    public int getMouseX(){
        return (int)mouseLoc.getX();
    }

    public int getMouseY(){
        return (int)mouseLoc.getY();
    }

}
