package game.map;

import java.awt.*;

public class MapInfo {
    private Map map;
    private Point boundaryCenter;
    private int boundaryRadius;

    private static MapInfo instance;

    private MapInfo(){

    }

    public static MapInfo getInstance(){
        if(instance == null){
            instance = new MapInfo();
        }
        return instance;
    }

    public void setBoundaryCenter(Point dc) {
        boundaryCenter  = dc;
    }

    public void setBoundaryRadius(int dr) {
        boundaryRadius = dr;
    }

    public Point getBoundaryCenter() {
        return boundaryCenter;
    }

    public int getBoundaryRadius() {
        return boundaryRadius;
    }
}
