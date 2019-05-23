package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.CommandManager;
import grp.oozmakappa.monsterclash.view.observers.PiecePropertyObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Chenglong Ma
 */
public class PlayerPanel extends JPanel implements PiecePropertyObserver, ActionListener {
    private static final Logger LOG = LogManager.getLogger();
    private final Team team;
    private final int totalPieces;
    private int numOfDeadPieces = 0;

    public PlayerPanel(List<Piece> pieces, Team team) {
        this.team = team;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Color backgroundColor = team == Team.OozmaKappa ?
                new Color(0X45A5FF) :
                new Color(0XF7C527);
        setBackground(backgroundColor);
        setPreferredSize(new Dimension(300, 60));
        int total = 0;
        for (Piece piece : pieces) {
            if (piece.getTeam() == team) {
                add(new PieceInfoPanel(piece));
                piece.addPropertyObserver(this);
                total++;
            }
        }
        totalPieces = total;
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
    public void healthChanged(double currValue, double deltaHealth) {
        // for normal progress
        if (numOfDeadPieces < totalPieces && currValue <= 0) {
            numOfDeadPieces++;
        }

        // for `undo` operation
        if (numOfDeadPieces >= totalPieces && currValue > 0) {
            numOfDeadPieces--;
        }
        if (numOfDeadPieces >= totalPieces) {
            Team rival = Team.getRivalTeam(team);
            String msg = rival + " Win!!";
            JOptionPane.showMessageDialog(null, msg, "Congrats!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void powerChanged(double currValue, double deltaPower) {
        // do nothing
    }

    @Override
    public void armorChanged(double currValue, double deltaArmor) {
        // do nothing
    }

    @Override
    public void rangeChanged(int currValue, int deltaRange) {
        // do nothing
    }
}
