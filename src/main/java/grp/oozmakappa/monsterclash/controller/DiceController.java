package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.Dice;
import grp.oozmakappa.monsterclash.view.DiceButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Chenglong Ma
 */
public class DiceController implements ActionListener {

    private final DiceButton diceButton;

    public DiceController(DiceButton diceButton) {

        this.diceButton = diceButton;
        this.diceButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        diceButton.updateIcon(Dice.roll());
    }
}
