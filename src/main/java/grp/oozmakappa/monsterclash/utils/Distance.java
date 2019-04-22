package grp.oozmakappa.monsterclash.utils;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;

import java.awt.*;

/**
 * @author Chenglong Ma
 */
public class Distance {

    /**
     * Calculates the Manhattan Distance between two points.
     *
     * @param aX
     * @param aY
     * @param bX
     * @param bY
     * @return
     * @post dx + dy >= 0
     */
    public static int manhattanDistance(int aX, int aY, int bX, int bY) {
        int dx = Math.abs(aX - bX);
        int dy = Math.abs(aY - bY);
        return dx + dy;
    }

    /**
     * @param a
     * @param b
     * @return
     * @see #manhattanDistance(int, int, int, int)
     */
    public static int manhattanDistance(Point a, Point b) {
        assert a != null && b != null;
        return manhattanDistance(a.x, a.y, b.x, b.y);
    }

    /**
     * @param a the start point
     * @param b the target point
     * @return the manhattan distance
     * @see #manhattanDistance(int, int, int, int)
     */
    public static int manhattanDistance(Cell a, Cell b) {
        assert a != null && b != null;
        return manhattanDistance(a.getX(), a.getY(), b.getX(), b.getY());
    }
}
