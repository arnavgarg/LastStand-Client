package game.map;

import java.awt.*;

public class MapInfo {
    private Map map;
    private Point boundaryCenter;
    private int boundaryRaduis;



    private static MapInfo instance;

    public static MapInfo getInstance(){
        if(instance == null){
            instance = new MapInfo();
        }
        return instance;
    }
}
