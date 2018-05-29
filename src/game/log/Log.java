package game.log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import game.main.Game;
import org.json.JSONArray;
import org.json.JSONObject;

public class Log {

    private ArrayList<Entry> entries;
    private int playerID;

    public Log(int playerID) {
        entries = new ArrayList<>();
        this.playerID = playerID;
    }

    public void addEntry(Entry e) {
        entries.add(e);
    }
  
    public JSONObject marshal() {
        JSONObject json = new JSONObject();
        JSONArray logObj = new JSONArray();
        for (int i = 0; i < entries.size(); i++) {
            JSONObject entryObj = new JSONObject();
            JSONArray extrasArr = new JSONArray();
            for (String entry : entries.get(i).getExtras()) {
                extrasArr.put(entry);
            }
            entryObj.put("extras", extrasArr);
            entryObj.put("id", entries.get(i).getID());
            logObj.put(entryObj);
        }
        json.put("log", logObj);
        json.put("player", playerID);
        return json;
    }

}
