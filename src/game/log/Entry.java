package game.log;

public class Entry {

    private final int id;
    private final String[] extras;

    public Entry(int id, String[] extras) {
        this.id = id;
        this.extras = extras;
    }

    public int getID() { return id; }
    public String[] getExtras() { return extras; }

}
