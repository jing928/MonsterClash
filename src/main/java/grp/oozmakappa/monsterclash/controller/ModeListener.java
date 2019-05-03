package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.Ability;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.ModeDialog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Chenglong Ma
 */
public class ModeListener implements ActionListener {
    private static final Logger LOG = LogManager.getLogger();
    private final Piece piece;
    private final ModeDialog dialog;

    public ModeListener(Piece piece, ModeDialog dialog) {
        this.piece = piece;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        Ability ability = Ability.parse(cmd);
        LOG.info("Selected Ability: " + ability);
        if (ability != null) {
            piece.setCurrentAbility(ability);
        }
        dialog.setVisible(false);
        dialog.dispose();
    }
}
