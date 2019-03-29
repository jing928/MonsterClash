package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.controller.DiceListener;
import grp.oozmakappa.monsterclash.model.Dice;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

import static grp.oozmakappa.monsterclash.utils.FileUtil.getSubFiles;

/**
 * @author Chenglong Ma
 */
public class DiceButton extends JButton {
    private static final String VALUE_DIR = "img/dice/value/";
    public static final List<Icon> DICE_ICONS = getIcons(VALUE_DIR);
    private static final String ROLLING_DIR = "img/dice/rolling/";
    public static final List<Icon> DICE_ROLLING_ICONS = getIcons(ROLLING_DIR);
    private static final int DEFAULT_ICON_ID = 1;
    private int value;

    public DiceButton() {
        addActionListener(new DiceListener(this));
        // set default icon
        setIcon(DICE_ROLLING_ICONS.get(DEFAULT_ICON_ID));
    }


    /**
     * Returns {@link Dice} icon set available for {@link DiceButton}
     *
     * @param dir
     * @return The icons under specific dir.
     */
    private static List<Icon> getIcons(String dir) {
        return getSubFiles(dir).stream()
                .map(ImageIcon::new).collect(Collectors.toList());
    }

    public int getValue() {
        return value;
    }


    /**
     * Updates the value of {@link DiceButton}.
     * Will animate the button before the final value determined.
     */
    public synchronized void setValue(int value) {
        new Thread(() -> {
            // The rolling animation.
            for (Icon icon : DICE_ROLLING_ICONS) {
                setIcon(icon);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // ignore this exception.
                }
            }
            // set the final dice.
            this.value = value;
            setIcon(DICE_ICONS.get(value - 1));
        }).start();
    }
}
