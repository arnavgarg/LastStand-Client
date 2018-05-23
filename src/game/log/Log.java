package game.log;

import java.util.ArrayList;

public class Log {

    private ArrayList<Entry> entries;
    private final int playerID;

    public Log(int playerID) {
        entries = new ArrayList<Entry>();
        this.playerID = playerID;
    }

    public void addEntry(Entry e) {
        entries.add(e);
    }

}
