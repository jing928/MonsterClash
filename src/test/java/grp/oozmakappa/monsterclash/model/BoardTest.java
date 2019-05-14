package grp.oozmakappa.monsterclash.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Chenglong Ma
 */
public class BoardTest {

    private Board board;
    private int width = 12, height = 12, corSq = 4;

    @Before
    public void setUp() {
        board = new Board(width, height, corSq, corSq);
    }

    @Test
    public void inBoard() {
        for (int y = height + 1; y >= -1 - height; y--) {
            for (int x = -1 - width; x <= width + 1; x++) {
                String s = board.inBoard(x, y) ? "#" : "-";
                System.out.print(s);
            }
            System.out.println();
        }
    }

    @Test
    public void testBitCalculation() {
        boolean value = false;
        boolean falseVal = false;
        System.out.println(1 & 1);
        Assert.assertTrue(value & falseVal);
    }
}