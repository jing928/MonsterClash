package grp.oozmakappa.monsterclash.utils;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;

import java.awt.*;

/**
 * @author Chenglong Ma
 */
public class Distance {

    public static int manhattanDistance(int aX, int aY, int bX, int bY) {
        int dx = Math.abs(aX - bX);
        int dy = Math.abs(aY - bY);
        return dx + dy;
    }

    public static int manhattanDistance(Point a, Point b) {
        return manhattanDistance(a.x, a.y, b.x, b.y);
    }

    public static int manhattanDistance(Cell a, Cell b) {
        return manhattanDistance(a.getX(), a.getY(), b.getX(), b.getY());
    }
}
