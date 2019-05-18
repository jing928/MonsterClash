package grp.oozmakappa.monsterclash.model;


import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.decorators.AbstractDecorator;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

import java.awt.*;
import java.util.Map;

import static grp.oozmakappa.monsterclash.utils.Distance.manhattanDistance;

/**
 * The element of {@link grp.oozmakappa.monsterclash.model.Board}
 * NB: the concrete classes inherit from this must be Immutable.
 *
 * @author Chenglong Ma
 */
public final class Cell implements CellEffect {
    private static Map<String, Cell> cellMap;
    /**
     * The coordinate of cell will not be changed once set.
     */
    private final int x;
    private final int y;
    private final Role role;
    private final boolean isHome;
    private Point location;

    private Cell(int x, int y) {
        this(x, y, Role.NEUTRAL, false);
    }

    private Cell(int x, int y, Role role) {
        this(x, y, role, false);
    }

    /**
     * The {@link Cell} can only be created in
     * {@link grp.oozmakappa.monsterclash.model.Board}
     * <br>
     * Make the constructor is `protected` to prevent being instantiated.
     *
     * @param x
     * @param y
     * @param role
     * @param isHome
     */
    private Cell(int x, int y, Role role, boolean isHome) {
        this.x = x;
        this.y = y;
        this.role = role;
        this.isHome = isHome;
    }

    public static Cell getCell(int x, int y) {
        return getCell(x, y, Role.NEUTRAL, false);
    }

    static Cell getCell(int x, int y, Role role, boolean isHome) {
        String key = String.format("%d,%d", x, y);
        Cell cell = cellMap.get(key);
        if (cell == null) {
            cell = new Cell(x, y, role, isHome);
            cellMap.put(key, cell);
        }
        return cell;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Role getRole() {
        return role;
    }

    public Team getTeam() {
        switch (role) {

            default:
            case NEUTRAL:
            case DISABLE:
                return null;
            case OozmaKappa:
                return Team.OozmaKappa;
            case RoarOmegaRoar:
                return Team.RoarOmegaRoar;
        }
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

    @Override
    public String toString() {
        return String.format("Cell: [ %d , %d ]", getX(), getY());
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

    public boolean isHome() {
        return isHome;
    }

    /**
     * The role of the cell.
     * <p>
     * To be used to determine the cell color.
     * </p>
     */
    public enum Role {
        OozmaKappa,
        RoarOmegaRoar,
        NEUTRAL,
        DISABLE,
    }
}
