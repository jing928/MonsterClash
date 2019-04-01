package grp.oozmakappa.monsterclash.model.abstracts;


import java.awt.*;

/**
 * The element of {@link grp.oozmakappa.monsterclash.model.Board}
 * NB: the concrete classes inherit from this must be Immutable.
 *
 * @author Chenglong Ma
 */
public abstract class Cell {
    /**
     * The coordinate of cell will not be changed once set.
     */
    protected final int x;
    protected final int y;
    protected final Role role;
    /**
     * The order in {@link grp.oozmakappa.monsterclash.model.Board}
     */
    @Deprecated
    protected final int order;
    private Point location;

    protected Cell(int x, int y) {
        this.x = x;
        this.y = y;
        order = -1;
        role = Role.DISABLE;
    }

    protected Cell(int x, int y, int order) {
        this(x, y, order, Role.NEUTRAL);
    }

    protected Cell(int x, int y, int order, Role role) {
        this.x = x;
        this.y = y;
        this.order = order;
        this.role = role;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getOrder() {
        return order;
    }

    public Role getRole() {
        return role;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    /**
     * @param other another {@link Cell}
     * @return the Manhattan distance
     * @see #distance(int, int)
     */
    public int distance(Cell other) {
        return distance(other.x, other.y);
    }

    /**
     * Returns the Manhattan distance to another {@link Cell}
     *
     * @param x
     * @param y
     * @return the Manhattan distance
     */
    public int distance(int x, int y) {
        x -= this.x;
        y -= this.y;
        return Math.abs(x) + Math.abs(y);
    }

    public int hashCode() {
        long code = java.lang.Double.doubleToLongBits(this.getX());
        code ^= java.lang.Double.doubleToLongBits(this.getY()) * 31L;
        return (int) code ^ (int) (code >> 32);
    }

    public boolean equals(Object other) {
        if (!(other instanceof Cell)) {
            return super.equals(other);
        } else {
            Cell cell = (Cell) other;
            return equals(cell.x, cell.y);
        }
    }

    public boolean equals(int x, int y) {
        return this.x == x && this.y == y;
    }

    public enum Role {
        TEAM_A,
        TEAM_B,
        NEUTRAL,
        DISABLE,
    }
}
