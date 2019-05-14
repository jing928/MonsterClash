package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

import javax.swing.*;
import java.awt.*;

/**
 * @author Chenglong Ma
 */
public class PieceInfoPanel extends JPanel {
    private final Piece piece;

    public PieceInfoPanel(Piece piece) {
        this.piece = piece;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initView();
        setOpaque(false);
    }

    private void initView() {
        String name = piece.getClass().getSimpleName();
        Icon icon = piece.getIcon().getResizedIcon(50, 50);
        JLabel title = new JLabel(name, icon, SwingConstants.LEFT);
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16));
        add(title);
        add(propertyLabel("Health", piece.getHealth()));
        add(propertyLabel("Attack Power", piece.getCurrentAttackPower()));
        add(propertyLabel("Armor", piece.getArmor()));
        add(propertyLabel("Reachable Range", piece.getCurrentReachableRange()));
        add(propertyLabel("Next move", piece.getNextMove()));
    }

    private JPanel propertyLabel(String name, Number value) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        JLabel property = new JLabel(name + ": ");
        property.setForeground(Color.WHITE);
        property.setFont(property.getFont().deriveFont(14f));
        JLabel valLabel = new JLabel(String.valueOf(value));
        valLabel.setForeground(Color.WHITE);
        valLabel.setFont(property.getFont().deriveFont(14f));
        panel.add(property);
        panel.add(valLabel);
        panel.setOpaque(false);
        return panel;
    }
}
