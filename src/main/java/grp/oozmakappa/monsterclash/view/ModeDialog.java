package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.controller.ModeListener;
import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.IconFactory;
import grp.oozmakappa.monsterclash.utils.StringUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author Chenglong Ma
 */
public class ModeDialog extends AbstractDialog {
    private final ModeListener listener;

    public ModeDialog(Piece piece) {
        super();
        this.listener = new ModeListener(piece, this);
        initView();
    }

    @Override
    void initView() {
        JPanel modePanel = new JPanel();
        modePanel.setLayout(new GridLayout(1, 2, 2, 2));
        // 1. add buttons
        modePanel.add(new ModeButton(Constraints.OFFENSIVE_MODE));
        modePanel.add(new ModeButton(Constraints.DEFENSIVE_MODE));
        modePanel.setPreferredSize(new Dimension(100 * 2, 120));
        add(modePanel);

        // 2. add cancel buttons
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(listener);
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(cancelButton);
    }

    private class ModeButton extends ImageButton {
        ModeButton(String mode) {
            super();
            IconFactory icons = IconFactory.getInstance();
            Icon icon = icons.getModeIcon(mode.toLowerCase());
            Icon pressedIcon = icons.getModeIcon((mode + "_armed").toLowerCase());
            setText(StringUtil.capitalizeFirstLetter(mode));
            setIcon(icon);
            setPressedIcon(pressedIcon);
            addActionListener(listener);
        }
    }
}
