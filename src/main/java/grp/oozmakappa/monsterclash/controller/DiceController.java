package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.Dice;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.DiceButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Chenglong Ma
 */
public class DiceController implements ActionListener {
    private final DiceButton diceButton;
    private final Collection<Piece> pieces;

    public DiceController(DiceButton diceButton) {
        this.diceButton = diceButton;
        this.pieces = new ArrayList<>();
        diceButton.addActionListener(this);
    }

    public void addObservers(List<Piece> pieces) {
        this.pieces.addAll(pieces);
    }

    private void setNextMove(int value) {
        for (Piece piece : pieces) {
            piece.setNextMove(value);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int value = Dice.roll();
        diceButton.setValue(value);
        setNextMove(value);
    }
}
