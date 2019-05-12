package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.strategies.modes.Mode;
import grp.oozmakappa.monsterclash.view.AbstractDialog;
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
    private final AbstractDialog dialog;

    public ModeListener(Piece piece, AbstractDialog dialog) {
        this.piece = piece;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand().toLowerCase();
        Mode mode = Mode.getMode(cmd);
        piece.setMode(mode);
        LOG.info("Selected Mode: " + cmd);
        dialog.close();
    }
}
