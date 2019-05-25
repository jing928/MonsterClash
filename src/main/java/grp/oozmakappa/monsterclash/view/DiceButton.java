package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.controller.DiceListener;
import grp.oozmakappa.monsterclash.model.Dice;
import grp.oozmakappa.monsterclash.model.interfaces.DiceObserver;
import grp.oozmakappa.monsterclash.utils.IconFactory;
import grp.oozmakappa.monsterclash.utils.NumberUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * @author Chenglong Ma
 */
public class DiceButton extends JButton implements DiceObserver {

    private static final int DEFAULT_ICON_ID = 1;
    private static final Logger LOG = LogManager.getLogger();
    private static final Dice DICE = Dice.getInstance();
    private static final IconFactory ICONS = IconFactory.getInstance();

    public DiceButton() {
        addActionListener(new DiceListener(this));
        DICE.addObserver(this);

        resetIcon();
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    private void resetIcon() {
        // set default icon
        setIcon(ICONS.getDiceIcon(DEFAULT_ICON_ID, true));
    }

    /**
     * Updates the icon of {@link DiceButton}.
     * <br>
     * Will animate the button before the final value determined.
     *
     * @Requires NumberUtil.between(value, 1, 6)
     */
    private synchronized void updateIcon(final int value) {
        if (!NumberUtil.between(value, 1, 6)) {
            resetIcon();
            return;
        }
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
            LOG.info("Update dice value to: " + value);
            setIcon(ICONS.getDiceIcon(value - 1, false));
        }).start();
    }

    @Override
    public void valueChanged(int value) {
        updateIcon(value);
    }
}
