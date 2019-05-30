package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.controller.UndoListener;
import grp.oozmakappa.monsterclash.model.Team;

import javax.swing.*;

public class UndoButton extends JButton {
    private final Team team;

    public UndoButton(Team team) {
        super("Undo");
        this.team = team;
        addActionListener(new UndoListener());
    }

    public Team getTeam() {
        return team;
    }

    public void setUndoUsed() {
        setEnabled(false);
    }

    public int getChoice() {
        Object[] choices = {"Cancel", 1, 2, 3};
        return JOptionPane.showOptionDialog(null,
                "How many turns do you want to undo?", "Undo?",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, choices, choices[0]);
    }

}
