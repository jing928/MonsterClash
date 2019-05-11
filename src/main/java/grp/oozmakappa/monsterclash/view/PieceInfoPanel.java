package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.observers.PiecePropertyObserver;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * @author Chenglong Ma
 */
public class PieceInfoPanel extends JPanel implements PiecePropertyObserver {
    private static final DecimalFormat FORMAT = new DecimalFormat("#.##");
    private final Piece piece;
    private final JLabel health, power, range, armor, move;

    public PieceInfoPanel(Piece piece) {
        this.piece = piece;
        piece.addPropertyObserver(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        health = new JLabel();
        power = new JLabel();
        range = new JLabel();
        armor = new JLabel();
        move = new JLabel();
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
        add(propertyLabel(health, "Health", piece.getHealth()));
        add(propertyLabel(power, "Attack Power", piece.getCurrentAttackPower()));
        add(propertyLabel(armor, "Armor", piece.getArmor()));
        add(propertyLabel(range, "Reachable Range", piece.getCurrentReachableRange()));
        add(propertyLabel(move, "Next move", piece.getNextMove()));
    }

    private JPanel propertyLabel(JLabel valueLabel, String name, Number value) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        JLabel property = new JLabel(name + ": ");
        property.setForeground(Color.WHITE);
        property.setFont(property.getFont().deriveFont(14f));
        valueLabel.setText(FORMAT.format(value));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(valueLabel.getFont().deriveFont(14f));
        panel.add(property);
        panel.add(valueLabel);
        panel.setOpaque(false);
        return panel;
    }

    @Override
    public void healthChanged(double deltaHealth, boolean isUndoing) {
        health.setText(FORMAT.format(piece.getHealth()));
    }

    @Override
    public void powerChanged(double deltaPower, boolean isUndoing) {
        power.setText(FORMAT.format(piece.getCurrentAttackPower()));
    }

    @Override
    public void rangeChanged(int deltaRange, boolean isUndoing) {
        range.setText(String.valueOf(piece.getCurrentReachableRange()));
    }
}
