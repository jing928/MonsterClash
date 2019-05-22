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
    public static final String RULE_A = "A";
    public static final String RULE_B = "B";

    /**
     * Create {@link AbstractRuleFactory} by specified Rule.
     *
     * @return concrete rule factory
     */
    public static AbstractRuleFactory getRuleFactory() {
        String rule = Constraints.getInstance().getCurrentRule();
        switch (rule) {
            default:
            case RULE_A:
                return new RuleAFactory();
            case RULE_B:
                return new RuleBFactory();
        }
    }

    public abstract AttackStrategy createAttackStrategy(Piece piece);

    public abstract HealStrategy createHealStrategy(Piece piece);

    public abstract MoveStrategy createMoveStrategy(Piece piece);
}
