package game.main;

import java.awt.*;

public class GameInfo {
    private Point mouseLoc, playerLoc;
    private boolean up, left, right, down; //whether the keys are pressed, to move the player
    private String playerName;
//    private Object[] entities;

    //keeps track of mouse info
    private GameInfo() {}

    private static GameInfo instance = null;

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

//    public Object[] getEntities() {
//        return entities;
//    }

}
