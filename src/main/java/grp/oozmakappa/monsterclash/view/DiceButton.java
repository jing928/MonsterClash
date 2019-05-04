package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.controller.DiceListener;
import grp.oozmakappa.monsterclash.utils.IconFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * @author Chenglong Ma
 */
public class DiceButton extends JButton {

    private static final int DEFAULT_ICON_ID = 1;
    private static final Logger LOG = LogManager.getLogger();
    private static final IconFactory ICONS = IconFactory.getInstance();

    public DiceButton() {
        addActionListener(new DiceListener(this));
        // set default icon
        setIcon(ICONS.getDiceIcon(DEFAULT_ICON_ID, true));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }


    /**
     * Updates the icon of {@link DiceButton}.
     * <br>
     * Will animate the button before the final value determined.
     */
    public synchronized void updateIcon(final int value) {
        new Thread(() -> {
            // The rolling animation.
            int round = 5;
            for (int i = 0; i < round; i++) {
                setIcon(ICONS.getDiceIcon(i, true));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // ignore this exception.
                }
            }
            // set the final dice.
            setIcon(ICONS.getDiceIcon(value - 1, false));
            LOG.info("Update dice value to: " + value);
        }).start();
    }
}
