package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

import javax.swing.*;

/**
 * @author Chenglong Ma
 */
public abstract class DebuffDecorator extends AbstractDecorator {
    protected DebuffDecorator(CellEffect toDecorated) {
        super(toDecorated);
    }

    @Override
    protected void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Sorry...", JOptionPane.WARNING_MESSAGE);
    }
}
