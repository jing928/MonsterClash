package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.Dice;
import grp.oozmakappa.monsterclash.view.DiceButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Chenglong Ma
 */
public class DiceListener implements ActionListener {
    private final DiceButton diceButton;

    public DiceListener(DiceButton diceButton) {
        this.diceButton = diceButton;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Dice.nextIcon(diceButton);
    }
}
