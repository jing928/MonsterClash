package grp.oozmakappa.monsterclash.model.abstracts;

/**
 * @author Chenglong Ma
 */
public abstract class Cell extends Point {

    public Cell(Point point) {
        super(point);
    }

    public Cell(int x, int y) {
        super(x, y);
    }
}
