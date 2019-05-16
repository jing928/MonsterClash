package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.CommandManager;
import grp.oozmakappa.monsterclash.view.observers.PiecePropertyObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Chenglong Ma
 */
public class PlayerPanel extends JPanel implements PiecePropertyObserver, ActionListener {
    private final Team team;
    private int numOfDeadPiece = 0;

    public PlayerPanel(List<Piece> pieces, Team team) {
        this.team = team;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Color backgroundColor = team == Team.OozmaKappa ?
                new Color(0X45A5FF) :
                new Color(0XF7C527);
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

    @Override
    public void healthChanged(double deltaHealth, boolean shouldNotify) {
        if (deltaHealth <= 0) {
            numOfDeadPiece++;
        } else {
            numOfDeadPiece--;
        }
        if (numOfDeadPiece >= 3) {
            Team rival = Team.getRivalTeam(team);
            String msg = rival + " Win!!";
            JOptionPane.showMessageDialog(null, msg, "Congrats!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void powerChanged(double deltaPower, boolean shouldNotify) {
        // do nothing
    }

    @Override
    public void armorChanged(double deltaArmor, boolean shouldNotify) {
        // do nothing
    }

    @Override
    public void rangeChanged(int deltaRange, boolean shouldNotify) {
        // do nothing
    }
}
