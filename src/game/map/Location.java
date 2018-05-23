package game.map;

public class Location {

    private double x;
    private double y;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double diffX(Location loc) {
        return this.x - loc.getX();
    }

    public double diffY(Location loc) {
        return this.y - loc.getY();
    }

}
