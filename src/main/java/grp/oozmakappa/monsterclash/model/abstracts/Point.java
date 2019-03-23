package grp.oozmakappa.monsterclash.model.abstracts;


/**
 * @author Chenglong Ma
 */
public abstract class Point {
    protected int x;
    protected int y;

    public Point(Point point) {
        this(point.x, point.y);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setPosition(Point point) {
        this.setPosition(point.x, point.y);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public int distance(Point other) {
        return distance(other.x, other.y);
    }

    public int distance(int x, int y) {
        x -= this.x;
        y -= this.y;
        return Math.abs(x + y);
    }

    public int hashCode() {
        long var1 = java.lang.Double.doubleToLongBits(this.getX());
        var1 ^= java.lang.Double.doubleToLongBits(this.getY()) * 31L;
        return (int) var1 ^ (int) (var1 >> 32);
    }

    public boolean equals(Object other) {
        if (!(other instanceof Point)) {
            return super.equals(other);
        } else {
            Point var2 = (Point) other;
            return this.x == var2.x && this.y == var2.y;
        }
    }

    public String toString() {
        return this.getClass().getName() + "[x=" + this.x + ",y=" + this.y + "]";
    }
}
