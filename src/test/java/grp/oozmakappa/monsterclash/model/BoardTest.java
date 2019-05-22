package grp.oozmakappa.monsterclash.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author Chenglong Ma
 */
public class BoardTest {

    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board(6, 5, 4, 4);
    }

    @Test
    public void getCells() {
        List<Cell> cells = board.getCells();
        int y = -5;
        for (Cell cell : cells) {
            if (cell.getY() > y) {
                System.out.println();
                y++;
            }
            Cell.Role role = cell.getRole();
            switch (role) {

                case OozmaKappa:
                    System.out.print("-");
                    break;
                case RoarOmegaRoar:
                    System.out.print("=");
                    break;
                case NEUTRAL:
                    System.out.print("|");
                    break;
                case DISABLE:
                    System.out.print("+");
                    break;
            }
        }
    }
}