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
        String name = piece.getName();
        Icon icon = piece.getIcon().getResizedIcon(50, 50);
        JLabel title = new JLabel(name, icon, SwingConstants.LEFT);
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16));
        add(title);
        add(propertyLabel(health, "Health", piece.getHealth()));
        add(propertyLabel(power, "Attack Power", piece.getCurrentAttackPower()));
        add(propertyLabel(armor, "Armor", piece.getCurrentArmor()));
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

    /**
     * Creates 1s animation for value changing
     *
     * @param label      the {@link JLabel} with animation
     * @param newValue   the updated value
     * @param deltaValue the delta value
     * @return the animation {@link Thread}
     */
    private Thread animation(final JLabel label, Number newValue, Number deltaValue) {
        final String origText = label.getText();
        final String operation = deltaValue.doubleValue() > 0 ? " + " : " - ";
        final String newText = FORMAT.format(newValue);
        return new Thread(() -> {
            try {
                label.setForeground(Color.RED);
                label.setFont(label.getFont().deriveFont(20f));
                String deltaString = FORMAT.format(Math.abs(deltaValue.doubleValue()));
                label.setText(origText + operation + deltaString);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ignore
            } finally {
                label.setText(newText);
                label.setForeground(Color.WHITE);
                label.setFont(label.getFont().deriveFont(14f));

            }
        });
    }

    @Override
    public void healthChanged(double currValue, double deltaHealth) {
        if (deltaHealth < 0) {
            deltaHealth += piece.getCurrentArmor();
            deltaHealth = Math.min(0, deltaHealth);
        }
        animation(health, currValue, deltaHealth).start();
    }

    @Override
    public void powerChanged(double currValue, double deltaPower) {
        animation(power, currValue, deltaPower).start();
    }

    @Override
    public void armorChanged(double currValue, double deltaArmor) {
        animation(armor, currValue, deltaArmor).start();
    }

    @Override
    public void rangeChanged(int currValue, int deltaRange) {
        animation(range, currValue, deltaRange).start();
    }
}
