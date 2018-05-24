package game.log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

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

    public void marshal() {
        HttpURLConnection con = (HttpURLConnection) new URL(address).openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoInput(true);
        con.setDoOutput(true);

        String player = Integer.toString(playerID);
        JSONObject json = new JSONObject();
        JSONArray log = new JSONArray();
        JSONObject entry = new JSONObject();

        for (int i = 0; i < entries.size(); i++) {
            entry.put("id", Integer.toString(entries.get(i).getID()));
            entry.put("extras", entries.get(i).getExtras());
            log.put(entry);
        }

        json.put("player", player);
        json.put("log", log);

    }


}
