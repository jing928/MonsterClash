package grp.oozmakappa.monsterclash.controller.decorators;

import javax.swing.*;

/**
 * @author Chenglong Ma
 */
public abstract class BuffDecorator extends AbstractDecorator {
    protected BuffDecorator(EffectDecorator toDecorated) {
        super(toDecorated);
    }

    @Override
    protected void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Congrats!", JOptionPane.INFORMATION_MESSAGE);
    }
}
