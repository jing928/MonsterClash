package grp.oozmakappa.monsterclash.model;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
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
    public static final int NUM_OF_PIECES = 6;
    private static Constraints instance;
    private String currentRule;
    private Team currentTeam;
    private boolean canMove;
    private Piece activePiece;
    private boolean enableObstacle;
    private int numOfPieces;
    private Team initialTeam;
    private int timeOut;
    private int maxX;
    private int maxY;
    private int cornerX;
    private int cornerY;

    private Constraints() {
        // for singleton pattern
        Dice dice = Dice.getInstance();
        dice.addObserver(this);

        // default settings
        currentRule = AbstractRuleFactory.RULE_A;
        initialTeam = Team.OozmaKappa;
        timeOut = TIME_OUT;
        numOfPieces = NUM_OF_PIECES;
        enableObstacle = true;
        canMove = false;
        maxX = MAX_X;
        maxY = MAX_Y;
        cornerX = CORNER_X;
        cornerY = CORNER_Y;
    }

    public static synchronized Constraints getInstance() {
        if (instance == null) {
            instance = new Constraints();
        }
        return instance;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getCornerX() {
        return cornerX;
    }

    public void setCornerX(int cornerX) {
        this.cornerX = cornerX;
    }

    public int getCornerY() {
        return cornerY;
    }

    public void setCornerY(int cornerY) {
        this.cornerY = cornerY;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int time) {
        this.timeOut = time;
    }

    public int getNumOfPieces() {
        return numOfPieces;
    }

    public void setNumOfPieces(int number) {
        this.numOfPieces = number;
    }

    public boolean isEnableObstacle() {
        return enableObstacle;
    }

    public Team getCurrentTeam() {
        if (currentTeam == null) {
            return initialTeam;
        }
        return currentTeam;
    }

    public void setCurrentTeam(Team currentTeam) {
        this.currentTeam = currentTeam;
    }

    public void changeTurn() {
        // To be called by TurnChangeCommand
        canMove = false;
        currentTeam = currentTeam == null
                ? initialTeam
                : Team.getRivalTeam(currentTeam);
        Dice.getInstance().setCanRoll(true);
    }

    public String getCurrentRule() {
        return currentRule;
    }

    public void setCurrentRule(String currentRule) {
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

    public Piece getActivePiece() {
        return activePiece;
    }

    /**
     * @param activePiece
     * @Requires activePiece == null || activePiece.getTeam() == this.currentTeam
     */
    public void setActivePiece(Piece activePiece) {
        assert activePiece == null || activePiece.getTeam() == this.currentTeam;
        this.activePiece = activePiece;
    }

    public void enableObstacle(boolean enable) {
        this.enableObstacle = enable;
    }

    public void setInitialTeam(Team initTeam) {
        this.initialTeam = initTeam;
    }
}
