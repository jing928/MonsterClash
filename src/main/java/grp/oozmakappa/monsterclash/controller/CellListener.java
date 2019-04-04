package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.view.CellLabel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Chenglong Ma
 */
public class CellListener extends MouseAdapter {
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
}
