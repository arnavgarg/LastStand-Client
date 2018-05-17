package game.main;

import java.awt.*;

public class GameInfo {

    //keeps track of mouse info
    private GameInfo() {}

    private static GameInfo instance = null;
    private Point mouseLoc;

    private String playerName;

    public static GameInfo getInstance(){
        if(instance == null){
            instance = new GameInfo();
        }
        return instance;
    }

    public void setMouseP(Point loc){
        mouseLoc = loc;
    }
    public void setPlayerName(String name) {
        playerName = name;
    }

    public int getMouseX(){
        if(mouseLoc == null) {
            mouseLoc = new Point(0,0);
        }
        return (int)mouseLoc.getX();
    }

    public int getMouseY(){
        if(mouseLoc == null) {
            mouseLoc = new Point(0,0);
        }
        return (int)mouseLoc.getY();
    }

}
