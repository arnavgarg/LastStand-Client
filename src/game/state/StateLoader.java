package game.state;

import java.awt.*;

public class StateLoader {

    private State currentState;

    public StateLoader(State s){
        currentState = s;
    }

    public void load(State s){
        currentState = s;
    }

    public void render(Graphics g){
        currentState.render(g);
    }

    public void tick(){
        currentState.tick();
    }

}
