package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.command.CommandManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Chenglong Ma
 */
public class PlayerPanel extends JPanel implements ActionListener {
    public PlayerPanel() {
        setPreferredSize(new Dimension(300, 60));
        JButton undoBtn = new JButton("Undo");
        add(undoBtn);
        undoBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CommandManager cmdManager = CommandManager.getInstance();
        cmdManager.undoTurn();
    }
}
