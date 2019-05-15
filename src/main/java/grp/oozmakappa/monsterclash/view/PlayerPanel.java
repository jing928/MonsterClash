package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.CommandManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Chenglong Ma
 */
public class PlayerPanel extends JPanel implements ActionListener {
    private final Team team;

    public PlayerPanel(List<Piece> pieces, Team team) {
        this.team = team;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Color backgroundColor = team == Team.OozmaKappa ?
                new Color(0X45A5FF) :
                new Color(0XEC691E);
        setBackground(backgroundColor);
        setPreferredSize(new Dimension(300, 60));
        for (Piece piece : pieces) {
            if (piece.getTeam() == team) {
                add(new PieceInfoPanel(piece));
            }
        }
        JButton undoBtn = new UndoButton(team);
        add(undoBtn);
        undoBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UndoButton button = (UndoButton) e.getSource();
        Team currTeam = Constraints.getInstance().getCurrentTeam();
        if (button.getTeam() != currTeam) {
            JOptionPane.showMessageDialog(this, "Not your turn!", "Oops...",
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
