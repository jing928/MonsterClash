package grp.oozmakappa.monsterclash.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Chenglong Ma
 */
public abstract class AbstractDialog extends JDialog {

    AbstractDialog() {
        super((Frame) null, true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void display() {
        // set location
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point);

        pack();
        setVisible(true);
    }

    public void close() {
        setVisible(false);
        dispose();
    }

    abstract void initView();

    abstract static class ImageButton extends JButton {

        ImageButton() {
            setVerticalTextPosition(SwingConstants.BOTTOM);
            setHorizontalTextPosition(SwingConstants.CENTER);
            setBorder(BorderFactory.createEmptyBorder());
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
        }
    }
}
