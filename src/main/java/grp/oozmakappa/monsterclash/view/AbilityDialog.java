package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.Ability;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Chenglong Ma
 */
public class AbilityDialog extends JDialog implements ActionListener {
    private final Piece piece;

    public AbilityDialog(Frame owner, Piece piece) {
        super(owner, true);
        int cols = piece.getAbilities().size();
        setLayout(new GridLayout(1, cols, 2, 2));
        Point point = MouseInfo.getPointerInfo().getLocation();
        setBounds(point.x, point.y, 100 * cols, 120);
        this.piece = piece;
        initView();
        setUndecorated(true);
    }

    private void initView() {
        for (Ability ability : piece.getAbilities()) {
            JButton button = new AbilityButton(ability);
            button.addActionListener(this);
            add(button);
        }
    }

    /**
     * @param e
     * @deprecated to be further improved
     */
    @Override
    @Deprecated
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
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
