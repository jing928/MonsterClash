package grp.oozmakappa.monsterclash.model;

import org.junit.Before;
import org.junit.Test;

import static grp.oozmakappa.monsterclash.utils.NumberUtil.between;
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
        for (int i = 0; i < 100; i++) {
            int next = (int) (Math.random() * 6) + 1;
            assertTrue(between(next, 1, 6));
        }

    }
}