package grp.oozmakappa.monsterclash.model.rules;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.strategies.abilities.*;

/**
 * @author Chenglong Ma
 */
public class RuleBFactory extends AbstractRuleFactory {
    @Override
    public AttackStrategy createAttackStrategy(Piece piece) {
        return new FartherAttack(piece);
    }

    @Override
    public HealStrategy createHealStrategy(Piece piece) {
        return new OtherHealing(piece);
    }

    @Override
    public MoveStrategy createMoveStrategy(Piece piece) {
        return new FartherMove(piece);
    }
}
