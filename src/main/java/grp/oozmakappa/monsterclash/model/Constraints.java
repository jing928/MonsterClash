package grp.oozmakappa.monsterclash.model;

import grp.oozmakappa.monsterclash.model.command.CommandManager;
import grp.oozmakappa.monsterclash.model.command.TurnChangeCommand;
import grp.oozmakappa.monsterclash.model.interfaces.DiceObserver;
import grp.oozmakappa.monsterclash.model.rules.AbstractRuleFactory;

/**
 * Specifies default constraints for this game.
 *
 * @author Chenglong Ma
 */
public class Constraints implements DiceObserver {
    public static final int MAX_X = 6;
    public static final int MAX_Y = 6;
    public static final int CORNER_X = 4;
    public static final int CORNER_Y = 4;
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 800;
    public static final int CELL_LENGTH = 60;
    public static final int PIECE_DIAMETER = 100;
    public static final int TIME_OUT = 30000;
    public static final String OFFENSIVE_MODE = "offensive";
    public static final String DEFENSIVE_MODE = "defensive";
    public static final Team INITIAL_TEAM = Team.OozmaKappa;

    private static Constraints instance;
    private AbstractRuleFactory.Rule currentRule;
    private Team currentTeam;
    private boolean canMove;

    private Constraints() {
        // for singleton pattern
        Dice dice = Dice.getInstance();
        dice.addObserver(this);
        // default settings
        currentRule = AbstractRuleFactory.Rule.A;
        canMove = false;
        currentTeam = INITIAL_TEAM;
    }

    public static synchronized Constraints getInstance() {
        if (instance == null) {
            instance = new Constraints();
        }
        return instance;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(Team currentTeam) {
        this.currentTeam = currentTeam;
    }

    public void changeTurn() {
        // To be called by TurnChangeCommand
        canMove = false;
        currentTeam = currentTeam == null
                ? INITIAL_TEAM
                : currentTeam == Team.OozmaKappa
                ? Team.RoarOmegaRoar
                : Team.OozmaKappa;
    }

    public AbstractRuleFactory.Rule getCurrentRule() {
        return currentRule;
    }

    public void setCurrentRule(AbstractRuleFactory.Rule currentRule) {
        this.currentRule = currentRule;
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    @Override
    public void valueChanged(int value) {
        canMove = true;
    }
}
