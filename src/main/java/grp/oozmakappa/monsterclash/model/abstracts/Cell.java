package grp.oozmakappa.monsterclash.model.abstracts;


/**
 * @author Chenglong Ma
 */
public abstract class Cell {
    protected int x;
    protected int y;

    public Cell(Cell cell) {
        this(cell.x, cell.y);
    }

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

    public void setPosition(Cell cell) {
        this.setPosition(cell.x, cell.y);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
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
