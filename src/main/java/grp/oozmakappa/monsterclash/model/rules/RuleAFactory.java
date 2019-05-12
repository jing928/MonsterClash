package grp.oozmakappa.monsterclash.model.rules;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.strategies.abilities.*;

/**
 * @author Chenglong Ma
 */
public class RuleAFactory extends AbstractRuleFactory {
    @Override
    public AttackStrategy createAttackStrategy(Piece piece) {
        return new StrongerAttack(piece);
    }

    @Override
    public HealStrategy createHealStrategy(Piece piece) {
        return new SelfHealing(piece);
    }

    @Override
    public MoveStrategy createMoveStrategy(Piece piece) {
        return new MultipleMove(piece);
    }
}
