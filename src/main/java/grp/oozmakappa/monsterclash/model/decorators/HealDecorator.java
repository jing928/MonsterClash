package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public interface HealDecorator {
    // TODO: what is the default health value? pass new value or set a const in Piece class?
    void heal(Piece target, double health);
}
