package game.log;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Log {

    private ArrayList<Entry> entries;
    private int playerID;

    public Log(int playerID) {
        entries = new ArrayList<Entry>();
        this.playerID = playerID;
    }

    public void addEntry(Entry e) {
        entries.add(e);
    }

    public JSONObject marshal() throws ProtocolException {
//        HttpURLConnection con = (HttpURLConnection) new URL(address).openConnection();
//        con.setRequestMethod("PUT");
//        con.setRequestProperty("Content-Type", "application/json");
//        con.setDoInput(true);
//        con.setDoOutput(true);

        JSONObject json = new JSONObject();
        JSONArray log = new JSONArray();

        for (int i = 0; i < entries.size(); i++) {
            JSONObject entry = new JSONObject();
            entry.put("id", entries.get(i).getID());
            entry.put("extras", entries.get(i).getExtras());
            log.put(entry);
        }

        json.put("player", playerID);
        json.put("log", log);
        return json;
    }


}
