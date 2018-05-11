package game.main;

import java.awt.*;

public class GameInfo {
    //keeps track of mouse info
    private GameInfo(){
    }

    private static GameInfo instance = null;
    private Point point;

    public static GameInfo getInstance(){
        if(instance == null){
            instance = new GameInfo();
        }
        return instance;
    }

    public void setMouseP(Point p){
        this.point = p;
    }

    public int getMouseX(){
        return (int)point.getX();
    }
    public int getMouseY(){
        return (int)point.getY();
    }
}
