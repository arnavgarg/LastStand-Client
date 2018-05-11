package game.main;

public class GameInfo {
    //keeps track of mouse info
    private GameInfo(){
    }

    private static GameInfo instance = null;
    private int mouseX;
    private int mouseY;

    public static GameInfo getInstance(){
        if(instance == null){
            instance = new GameInfo();
        }
        return instance;
    }

    public void setMouseX(int dx){
        this.mouseX = dx;
    }
    public void setMouseY(int dy){
        this.mouseY = dy;
    }

    public int getMouseX(){
        return mouseX;
    }
    public int getMouseY(){
        return mouseY;
    }
}
