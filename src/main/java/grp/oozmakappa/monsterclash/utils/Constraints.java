package grp.oozmakappa.monsterclash.utils;

import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.rules.AbstractRuleFactory;

/**
 * Specifies default constraints for this game.
 *
 * @author Chenglong Ma
 */
public class Constraints {
    public static final int MAX_X = 6;
    public static final int MAX_Y = 6;
    public static final int CORNER_X = 4;
    public static final int CORNER_Y = 4;
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 800;

    public static final int CELL_LENGTH = 60;
    public static final int PIECE_DIAMETER = 100;

    public static final int TIME_OUT = 3000;

    public static final String OFFENSIVE_MODE = "offensive";
    public static final String DEFENSIVE_MODE = "defensive";

    public static final AbstractRuleFactory.Rule RULE = AbstractRuleFactory.Rule.A;
    public static final Team INITIATIVE_TEAM = Team.OozmaKappa;
}
