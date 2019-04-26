package grp.oozmakappa.monsterclash.model.rules;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.decorators.AttackDecorator;
import grp.oozmakappa.monsterclash.model.decorators.HealDecorator;
import grp.oozmakappa.monsterclash.model.decorators.MoveDecorator;

/**
 * @author Chenglong Ma
 */
public abstract class AbstractRuleFactory {

    /**
     * Create {@link AbstractRuleFactory} by specified {@link Rule}.
     *
     * @param rule see {@link Rule}
     * @return concrete rule factory
     */
    public static AbstractRuleFactory getRuleFactory(Rule rule) {
        switch (rule) {
            default:
            case A:
                return new RuleAFactory();
            case B:
                return new RuleBFactory();
        }
    }

    abstract AttackDecorator createAttackDecorator(Piece piece);

    abstract HealDecorator createHealDecorator(Piece piece);

    abstract MoveDecorator createMoveDecorator(Piece piece);

    public enum Rule {
        A,
        B
    }
}
