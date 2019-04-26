package grp.oozmakappa.monsterclash.model.rules;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.decorators.*;

/**
 * @author Chenglong Ma
 */
public class RuleAFactory extends AbstractRuleFactory {
    @Override
    AttackDecorator createAttackDecorator(Piece piece) {
        return new StrongerAttack(piece);
    }

    @Override
    HealDecorator createHealDecorator(Piece piece) {
        return new SelfHealing(piece);
    }

    @Override
    MoveDecorator createMoveDecorator(Piece piece) {
        return new MultipleMove(piece);
    }
}
