package grp.oozmakappa.monsterclash.model;


import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.decorators.AbstractDecorator;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

import static grp.oozmakappa.monsterclash.utils.Distance.manhattanDistance;

/**
 * The element of {@link grp.oozmakappa.monsterclash.model.Board}
 * NB: the concrete classes inherit from this must be Immutable.
 *
 * @author Chenglong Ma
 */
public final class Cell implements CellEffect {
    /**
     * The coordinate of cell will not be changed once set.
     */
    private final int x;
    private final int y;
    private final Role role;
    /**
     * The order in {@link grp.oozmakappa.monsterclash.model.Board}
     */
    @Deprecated
    // TODO remove this
    private final int order;

    protected Cell(int x, int y) {
        this.x = x;
        this.y = y;
        order = -1;
        role = Role.DISABLE;
    }

    protected Cell(int x, int y, int order) {
        this(x, y, order, Role.NEUTRAL);
    }

    /**
     * The {@link Cell} can only be created in
     * {@link grp.oozmakappa.monsterclash.model.Board}
     * <br>
     * Make the constructor is `protected` to prevent being instantiated.
     *
     * @param x
     * @param y
     * @param order
     * @param role
     */
    protected Cell(int x, int y, int order, Role role) {
        this.x = x;
        this.y = y;
        this.order = order;
        this.role = role;
    }

    @Deprecated
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
     * Returns the Manhattan distance to another {@link Cell},
     * or some other object that has coordinates.
     *
     * @param x
     * @param y
     * @return the Manhattan distance
     */
    public int distance(int x, int y) {
        return manhattanDistance(this.x, this.y, x, y);
    }

    public int hashCode() {
        long code = java.lang.Double.doubleToLongBits(x);
        code ^= java.lang.Double.doubleToLongBits(y) * 31L;
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

    /**
     * Returns if the specified location is same as this cell
     *
     * @param x
     * @param y
     * @return
     */
    public boolean equals(int x, int y) {
        return this.x == x && this.y == y;
    }

    /**
     * Invoked when {@link Piece}s located on this {@link Cell}.
     * <p>
     * Recursively called because multiple effects may happen.
     * </p>
     *
     * @param pieceLocated
     * @Requires cell.distance(pieceLocated.getPosition ()) == 0
     */
    @Override
    public void affect(Piece pieceLocated) {
        CellEffect decorator = AbstractDecorator.getDecorator(this);
        if (decorator != null) {
            decorator.affect(pieceLocated);
        }
    }

    // TODO: to be checked.

    /**
     * The role of the cell.
     * <p>
     * To be used to determine the cell color.
     * </p>
     */
    public enum Role {
        TEAM_A,
        TEAM_B,
        NEUTRAL,
        DISABLE,
    }
}
