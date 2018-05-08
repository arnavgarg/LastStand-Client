public class StateLoader {
    public State.State currentState;

    public StateLoader(State.State s){
        currentState = s;
    }

    public void load(State.State s){
        currentState = s;
    }

    public void render(){
        currentState.render();
    }
    public void tick(){
        currentState.tick();
    }
}
