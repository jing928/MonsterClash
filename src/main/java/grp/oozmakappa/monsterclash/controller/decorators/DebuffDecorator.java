package grp.oozmakappa.monsterclash.controller.decorators;

import javax.swing.*;

/**
 * @author Chenglong Ma
 */
public abstract class DebuffDecorator extends AbstractDecorator {
    protected DebuffDecorator(EffectDecorator toDecorated) {
        super(toDecorated);
    }

    @Override
    protected void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Sorry...", JOptionPane.WARNING_MESSAGE);
    }
}
