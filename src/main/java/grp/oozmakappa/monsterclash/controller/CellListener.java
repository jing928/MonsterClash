package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.controller.decorators.AbstractDecorator;
import grp.oozmakappa.monsterclash.controller.decorators.EffectDecorator;
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
public class CellListener extends MouseAdapter implements EffectDecorator {
    private static final Logger LOG = LogManager.getLogger();
    private final CellLabel cellLabel;
    private Color defaultColor;

    public CellListener(CellLabel cellLabel) {
        this.cellLabel = cellLabel;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        defaultColor = cellLabel.getBackground();
        cellLabel.setBackground(Color.GREEN);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        if (defaultColor == null) {
            defaultColor = cellLabel.getBackground();
        }
        cellLabel.setBackground(defaultColor);
    }

    @Override
    public void affect(Piece piece) {
        // default behaviour: nothing
        LOG.info("Affect piece");
        EffectDecorator decorator = AbstractDecorator.getDecorator(this);
        if (decorator != null) {
            decorator.affect(piece);
        }
    }
}
