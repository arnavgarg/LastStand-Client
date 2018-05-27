package game.sprites;

import game.map.Location;

public class Rock {

    private Location loc;

    public Rock(Location loc) {
        this.loc = loc;
    }

    public Location getLoc() {
        return loc;
    }

}
