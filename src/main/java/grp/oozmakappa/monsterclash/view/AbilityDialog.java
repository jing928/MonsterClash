package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.controller.AbilityListener;
import grp.oozmakappa.monsterclash.model.Ability;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

import javax.swing.*;
import java.awt.*;

/**
 * @author Chenglong Ma
 */
public class AbilityDialog extends AbstractDialog {
    private final Piece piece;
    private final AbilityListener listener;

    public AbilityDialog(Piece piece) {
        super();
        this.piece = piece;
        listener = new AbilityListener(piece, this);
        initView();
    }

    @Override
    void initView() {
        JPanel mainPanel = new JPanel();
        int cols = piece.getAbilities().size();
        mainPanel.setLayout(new GridLayout(1, cols, 2, 2));
        // 1. add buttons
        for (Ability ability : piece.getAbilities()) {
            JButton button = new AbilityButton(ability);
            button.addActionListener(listener);
            mainPanel.add(button);
        }
        mainPanel.setPreferredSize(new Dimension(100 * cols, 120));
        add(mainPanel);

        // 2. add cancel buttons
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(listener);
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(cancelButton);
    }

    private static class AbilityButton extends ImageButton {

        AbilityButton(Ability ability) {
            super();
            setText(ability.toString());
            setIcon(ability.getIcon());
            setPressedIcon(ability.getPressedIcon());
        }
    }
}
