package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.command.CommandManager;
import grp.oozmakappa.monsterclash.view.UndoButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Chenglong Ma
 */
public class UndoListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        UndoButton button = (UndoButton) e.getSource();
        Team currTeam = Constraints.getInstance().getCurrentTeam();
        if (button.getTeam() != currTeam) {
            JOptionPane.showMessageDialog(button, "Not your turn!", "Oops...",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int numOfUndos = button.getChoice();
        if (numOfUndos == 0) {
            return;
        }
        CommandManager cmdManager = CommandManager.getInstance();
        cmdManager.undoTurns(numOfUndos);
        button.setUndoUsed();
    }
}
