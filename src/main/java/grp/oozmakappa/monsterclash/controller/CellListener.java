package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.CellLabel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Chenglong Ma
 */
public class CellListener extends MouseAdapter {
    private static final Logger LOG = LogManager.getLogger();
    private final CellLabel cellLabel;
    private Color defaultColor;

    public CellListener(CellLabel cellLabel) {
        this.cellLabel = cellLabel;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        defaultColor = cellLabel.getBackground();
        cellLabel.setBackground(Color.CYAN);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        if (defaultColor == null) {
            defaultColor = cellLabel.getBackground();
        }
        cellLabel.setBackground(defaultColor);
    }

    /**
     * @param cell
     * @param pieceLocated
     * @Requires cell.distance(pieceLocated.getPosition ()) == 0
     */
    public boolean affect(Cell cell, Piece pieceLocated) {
        if (Constraints.getInstance().isEnableObstacle()) {
            return cell.affect(pieceLocated);
        }
        return false;
    }
}
