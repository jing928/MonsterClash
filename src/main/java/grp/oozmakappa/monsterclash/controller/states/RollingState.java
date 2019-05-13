package grp.oozmakappa.monsterclash.controller.states;

import grp.oozmakappa.monsterclash.controller.PieceListener;

import javax.swing.*;

/**
 * @author Chenglong Ma
 */
public class RollingState extends PieceButtonState {
    private static RollingState instance;

    private RollingState(PieceListener ctrl) {
        super(ctrl);
        // for singleton pattern
    }

    public static synchronized RollingState getInstance(PieceListener ctrl) {
        if (instance == null) {
            instance = new RollingState(ctrl);
        }
        return instance;
    }

    @Override
    public void todo() {
        JButton button = ctrl.getButton();
        button.removeMouseMotionListener(ctrl);
        JOptionPane.showMessageDialog(button, "Please roll dice first");
    }

    @Override
    public void done() {
        ctrl.setState(this);
    }
}
