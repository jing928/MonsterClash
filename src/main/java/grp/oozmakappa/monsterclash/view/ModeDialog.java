package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.controller.ModeListener;
import grp.oozmakappa.monsterclash.model.Ability;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

import javax.swing.*;
import java.awt.*;

/**
 * @author Chenglong Ma
 */
public class ModeDialog extends JDialog {
    private final Piece piece;
    private final ModeListener listener;

    public ModeDialog(Piece piece) {
        super((Frame) null, true);
        this.piece = piece;
        listener = new ModeListener(piece, this);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        initView();
        setUndecorated(true);
    }

    public void display() {
        // set location
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point);

        pack();
        setVisible(true);
    }

    private void initView() {
        JPanel modePanel = new JPanel();
        int cols = piece.getAbilities().size();
        modePanel.setLayout(new GridLayout(1, cols, 2, 2));
        // 1. add buttons
        for (Ability ability : piece.getAbilities()) {
            JButton button = new AbilityButton(ability);
            button.addActionListener(listener);
            modePanel.add(button);
        }
        modePanel.setPreferredSize(new Dimension(100 * cols, 120));
        add(modePanel);

        // 2. add cancel buttons
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(listener);
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(cancelButton);
    }

    private class AbilityButton extends JButton {

        AbilityButton(Ability ability) {
            super(ability.toString(), ability.getIcon());
            setPressedIcon(ability.getPressedIcon());
            setVerticalTextPosition(SwingConstants.BOTTOM);
            setHorizontalTextPosition(SwingConstants.CENTER);
            setBorder(BorderFactory.createEmptyBorder());
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
        }
    }
}
