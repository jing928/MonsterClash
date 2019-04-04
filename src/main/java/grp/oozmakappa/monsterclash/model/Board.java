package grp.oozmakappa.monsterclash.model;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import org.apache.commons.lang3.Range;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link Board} consists of some {@link Cell}s.
 * The illustration of {@link Board} like:
 * <pre>
 *
 *                     |
 *                     |
 *                 ----|----
 *                |    |    |
 *                |    |    |
 *         -------     |     -------
 *        |            |            | (maxX, 0)
 * ------------------- |------------o-------->
 *        |            |            |
 *         -------     |    o-------
 *                |    |    | (cornerX, cornerY)
 *                |    |    |
 *                 ----o----
 *                     | (0, maxY)
 *                     |
 *                     V
 *
 * </pre>
 * The shape of the board can be determined by coordinates of only three points
 * ({@link #maxX}, {@link #maxY}, {@link #cornerX} and {@link #cornerY})
 * as it's perfectly symmetric.
 *
 * @author Chenglong Ma
 */
public class Board {
    private static final Logger LOG = LogManager.getLogger();
    private final int maxX, maxY, cornerX, cornerY;
    private List<Cell> cells;

    public Board(int maxX, int maxY, int cornerX, int cornerY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.cornerX = cornerX;
        this.cornerY = cornerY;
        initializeCells();
    }

    public List<Cell> getCells() {
        return cells;
    }

    public Cell getCell(int x, int y) {
        return cells
                .stream()
                .filter(c -> c.equals(x, y) && c.getRole() != Cell.Role.DISABLE)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Invalid coordinate"));
    }

    private void initializeCells() {
        cells = new ArrayList<>();
        int i = 0;
        for (int y = -1 * maxY; y <= maxY; y++) {
            for (int x = -1 * maxX; x <= maxX; x++) {
                int order = i++;
                Cell.Role role;
                if (inBoard(x, y)) {
                    role = x == 0
                            ? Cell.Role.NEUTRAL
                            : (x > 0 ? Cell.Role.TEAM_A : Cell.Role.TEAM_B);
                } else {
                    role = Cell.Role.DISABLE;
                }
                cells.add(new NormalCell(x, y, order, role));
            }
        }
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getWidth() {
        return maxX * 2 + 1;
    }

    public int getHeight() {
        return maxY * 2 + 1;
    }


    public boolean inBoard(Cell cell) {
        return inBoard(cell.getX(), cell.getY());
    }

    public boolean inBoard(int x, int y) {
        Range<Integer> xRange = Range.between(-1 * getMaxX(), getMaxX());
        Range<Integer> yRange = Range.between(-1 * getMaxY(), getMaxY());

        if (!xRange.contains(x) || !yRange.contains(y)) {
            return false;
        }

        xRange = Range.between(-1 * cornerX, cornerX);
        yRange = Range.between(-1 * cornerY, cornerY);
        if (!xRange.contains(x)) {
            return yRange.contains(y);
        }
        return true;
    }

}
