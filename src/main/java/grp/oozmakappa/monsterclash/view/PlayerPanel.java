package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Chenglong Ma
 */
public class PlayerPanel extends JPanel {
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
        add(new JButton("Undo"));
    }
}
