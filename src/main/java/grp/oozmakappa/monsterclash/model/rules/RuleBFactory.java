package grp.oozmakappa.monsterclash.model.rules;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.decorators.*;

/**
 * @author Chenglong Ma
 */
public class RuleBFactory extends AbstractRuleFactory {
    @Override
    AttackDecorator createAttackDecorator(Piece piece) {
        return new FartherAttack(piece);
    }

    @Override
    HealDecorator createHealDecorator(Piece piece) {
        return new OtherHealing(piece);
    }

    @Override
    MoveDecorator createMoveDecorator(Piece piece) {
        return new FartherMove(piece);
    }
}
