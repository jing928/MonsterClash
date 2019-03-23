package grp.oozmakappa.monsterclash.model.abstracts;


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

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
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
        return Math.abs(x + y);
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
            return this.x == cell.x && this.y == cell.y;
        }
    }
}
