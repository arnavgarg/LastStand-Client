package game.player;

public class PlayerInfo {


    private PlayerInfo(){

    }

    private static PlayerInfo instance;

    public PlayerInfo getInstance(){
        if(instance == null){
            instance = new PlayerInfo();
        }
        return instance;
    }

}
