package grp.oozmakappa.monsterclash.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Chenglong Ma
 */
public class AbilityTest {

    @Test
    public void testParse() {
        for (Ability expected : Ability.values()) {
            Ability actual = Ability.parse(expected.toString());
            assertEquals(expected, actual);
        }
        assertNull(Ability.parse("fake value"));
    }

    @Test
    public void toString1() {
    }

    @Test
    public void getIcon() {
    }

    @Test
    public void getPressedIcon() {
    }
}