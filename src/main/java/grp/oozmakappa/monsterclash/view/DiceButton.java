package grp.oozmakappa.monsterclash.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

import static grp.oozmakappa.monsterclash.utils.IconUtil.DICE_ICONS;
import static grp.oozmakappa.monsterclash.utils.IconUtil.DICE_ROLLING_ICONS;

/**
 * @author Chenglong Ma
 */
public class DiceButton extends JButton {

    private static final int DEFAULT_ICON_ID = 1;
    private static final Logger LOG = LogManager.getLogger();
    private int value;

    public DiceButton() {
        // set default icon
        setIcon(DICE_ROLLING_ICONS.get(DEFAULT_ICON_ID));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setContentAreaFilled(false);
        setBorderPainted(false);
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
            LOG.info("Update dice value to: " + value);
        }).start();
    }


//    @Override
//    public void positionChanging(Piece pieceToMove) {
////        pieceToMove.setNextMove(value);
//    }
//
//    @Override
//    public void positionChanged(Piece pieceMoved) {
////        pieceMoved.resetMovingDistance();
//    }
}
