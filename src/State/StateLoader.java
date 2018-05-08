package State;

public class StateLoader {

    public State currentState;

    public StateLoader(State s){
        currentState = s;
    }

    public void load(State s){
        currentState = s;
    }

    public void render(){
        currentState.render();
    }

    public void tick(){
        currentState.tick();
    }

}
