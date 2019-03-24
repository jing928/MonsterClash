package grp.oozmakappa.monsterclash.view;

import grp.oozmakappa.monsterclash.controller.DiceListener;
import grp.oozmakappa.monsterclash.model.Dice;

import javax.swing.*;

/**
 * @author Chenglong Ma
 */
public class DiceButton extends JButton {
    private Dice dice;

    public DiceButton() {
        setDice(Dice.next());
        addActionListener(new DiceListener(this));
    }

    public void setDice(Dice dice) {
        this.dice = dice;
        setIcon(dice.getIcon());
    }

    public int getValue() {
        return dice.getValue();
    }
}
