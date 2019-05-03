package grp.oozmakappa.monsterclash.controller.states;

import grp.oozmakappa.monsterclash.controller.PieceListener;

import javax.swing.*;

/**
 * @author Chenglong Ma
 */
public class RollingState implements PieceButtonState {
    private static RollingState instance;

    private RollingState() {
        // for singleton pattern
    }

    public static RollingState getInstance() {
        if (instance == null) {
            instance = new RollingState();
        }
        return instance;
    }

    @Override
    public void todo(PieceListener ctrl) {
        JButton button = ctrl.getCurrButton();
        button.removeMouseMotionListener(ctrl);
        JOptionPane.showMessageDialog(button, "Please roll dice first");
    }

    @Override
    public void done(PieceListener ctrl) {
        ctrl.setState(this);
    }
}
