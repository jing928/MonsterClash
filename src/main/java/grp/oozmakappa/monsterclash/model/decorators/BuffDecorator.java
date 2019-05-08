package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

import javax.swing.*;

/**
 * @author Chenglong Ma
 */
public abstract class BuffDecorator extends AbstractDecorator {
    protected BuffDecorator(CellEffect toDecorated) {
        super(toDecorated);
    }

    @Override
    protected void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Congrats!", JOptionPane.INFORMATION_MESSAGE);
    }
}
