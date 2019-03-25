package grp.oozmakappa.monsterclash.model;

import grp.oozmakappa.monsterclash.view.DiceButton;

import javax.swing.*;
import java.util.List;

import static grp.oozmakappa.monsterclash.utils.FileUtil.getSubFiles;

/**
 * @author Chenglong Ma
 */
public class Dice {
    private static final String VALUE_DIR = "img/dice/value/";
    public static final List<String> DICE_ICON_SET = getIconSet();
    private static final String ROLLING_DIR = "img/dice/rolling/";
    public static final List<String> DICE_ROLLING_SET = getRollingSet();
    private static final int MAX_DICE = DICE_ICON_SET.size();
    private final int value;

    /**
     * NB: make this constructor ''private'';
     * use {@link #next()} instead.
     *
     * @param value
     * @see #next()
     */
    private Dice(int value) {
        this.value = value;
    }

    /**
     * Returns the icon path set available for {@link Dice}
     *
     * @return
     */
    private static List<String> getIconSet() {
        return getSubFiles(VALUE_DIR);
    }

    /**
     * Returns the rolling icon path set for {@link Dice}
     *
     * @return
     */
    private static List<String> getRollingSet() {
        return getSubFiles(ROLLING_DIR);
    }

    /**
     * Generates next {@link Dice}
     *
     * @return
     */
    public static Dice next() {
        int next = (int) (Math.random() * MAX_DICE) + 1;
        return new Dice(next);
    }

    /**
     * Updates icons of {@link DiceButton}
     *
     * @param button
     */
    public static void nextIcon(final DiceButton button) {
        new Thread(() -> {
            for (String file : DICE_ROLLING_SET) {
                Icon icon = new ImageIcon(file);
                button.setIcon(icon);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // set the final dice.
            button.setDice(next());
        }).start();
    }

    public int getValue() {
        return value;
    }

    public ImageIcon getIcon() {
        return new ImageIcon(DICE_ICON_SET.get(value - 1));
    }
}
