package grp.oozmakappa.monsterclash.model.rules;

import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.strategies.abilities.AttackStrategy;
import grp.oozmakappa.monsterclash.model.strategies.abilities.HealStrategy;
import grp.oozmakappa.monsterclash.model.strategies.abilities.MoveStrategy;

/**
 * @author Chenglong Ma
 */
public abstract class AbstractRuleFactory {

    /**
     * Create {@link AbstractRuleFactory} by specified {@link Rule}.
     *
     * @return concrete rule factory
     */
    public static AbstractRuleFactory getRuleFactory() {
        Rule rule = Constraints.getInstance().getCurrentRule();
        switch (rule) {
            default:
            case A:
                return new RuleAFactory();
            case B:
                return new RuleBFactory();
        }
    }

    public abstract AttackStrategy createAttackStrategy(Piece piece);

    public abstract HealStrategy createHealStrategy(Piece piece);

    public abstract MoveStrategy createMoveStrategy(Piece piece);

    public enum Rule {
        A,
        B
    }
}
