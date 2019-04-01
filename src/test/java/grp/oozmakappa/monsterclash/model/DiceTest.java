package grp.oozmakappa.monsterclash.model;

import org.apache.commons.lang3.Range;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Chenglong Ma
 */
public class DiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void textRandom() {
        Range<Integer> diceRange = Range.between(1, 6);
        for (int i = 0; i < 100; i++) {
            int next = (int) (Math.random() * 6) + 1;
            assertTrue(diceRange.contains(next));
        }

    }
}