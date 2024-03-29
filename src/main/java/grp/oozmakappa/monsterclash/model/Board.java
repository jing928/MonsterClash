package grp.oozmakappa.monsterclash.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static grp.oozmakappa.monsterclash.utils.NumberUtil.between;

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
 * @Invariant cells.size() >= 0
 * @Invariant maxX > 0 && maxY > 0 && cornerX > 0 && cornerY > 0
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
        return Collections.unmodifiableList(cells);
    }

    /**
     * Creates new {@link Cell}s for this {@link Board}
     *
     * @Ensures cells != null && cells.size() > 0
     */
    private void initializeCells() {
        cells = new ArrayList<>();
        for (int y = -1 * maxY; y <= maxY; y++) {
            for (int x = -1 * maxX; x <= maxX; x++) {
                Cell.Role role;
                boolean isHome = Math.abs(x) == maxX;
                if (inBoard(x, y)) {
                    role = x == 0
                            ? Cell.Role.NEUTRAL
                            : (x < 0 ? Cell.Role.OozmaKappa : Cell.Role.RoarOmegaRoar);
                } else {
                    role = Cell.Role.DISABLE;
                }
                // Board is the creator of cell.
                cells.add(Cell.getCell(x, y, role, isHome));
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


    /**
     * Determines if the specified {@link Cell} in the {@link Board}
     *
     * @param cell
     * @return
     * @see #inBoard(int, int)
     */
    public boolean inBoard(Cell cell) {
        assert cell != null;
        return inBoard(cell.getX(), cell.getY());
    }

    /**
     * Determines if the specified coordinate in the {@link Board}
     *
     * @param x
     * @param y
     * @return
     */
    public boolean inBoard(int x, int y) {
        boolean validX = between(x, -maxX, maxX);
        boolean validY = between(y, -maxY, maxY);

        if (!validX || !validY) {
            return false;
        }
        validX = between(x, -cornerX, cornerX);
        validY = between(y, -cornerY, cornerY);
        return validX || validY;
    }

}
