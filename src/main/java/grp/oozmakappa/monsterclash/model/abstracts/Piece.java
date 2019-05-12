package grp.oozmakappa.monsterclash.model.abstracts;

import grp.oozmakappa.monsterclash.model.Ability;
import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.interfaces.DiceObserver;
import grp.oozmakappa.monsterclash.model.rules.AbstractRuleFactory;
import grp.oozmakappa.monsterclash.model.strategies.modes.DefaultMode;
import grp.oozmakappa.monsterclash.model.strategies.modes.Mode;
import grp.oozmakappa.monsterclash.utils.IconFactory;
import grp.oozmakappa.monsterclash.utils.flyweights.IconFlyweight;
import grp.oozmakappa.monsterclash.view.observers.PieceActionObserver;
import grp.oozmakappa.monsterclash.view.observers.PiecePositionObserver;
import grp.oozmakappa.monsterclash.view.observers.PiecePropertyObserver;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;

/**
 * @author Jing Li
 * @Invariant nextMove >= 0
 * @Invariant health >= 0
 * @Invariant posObservers.size() >= 0
 */
public abstract class Piece implements DiceObserver {

    private static final AbstractRuleFactory RULE = AbstractRuleFactory.getRuleFactory();
    private final Team team;
    private final Set<PiecePositionObserver> posObservers;
    private final Set<PiecePropertyObserver> pptObservers;
    private final Set<PieceActionObserver> actObservers;
    private final List<Ability> abilities;
    private Ability currAbility;
    private String iconName;
    private double health;
    private Cell position;
    private double attackPower;
    private int reachableRange;
    private double armor;
    private int nextMove;
    private Mode mode;
    private boolean shouldNotify = true;
    protected static final Logger LOG = LogManager.getLogger();

    public Piece(Team team, Cell position, double health, double attackPower, int reachableRange) {
        this.team = team;
        this.position = position;
        this.health = health;
        this.attackPower = attackPower;
        this.reachableRange = reachableRange;
        mode = DefaultMode.getInstance();
        posObservers = new HashSet<>();
        pptObservers = new HashSet<>();
        actObservers = new HashSet<>();
        abilities = new ArrayList<>();
        abilities.add(Ability.PLAIN_ATTACK);
    }

    public int getOriginalRange() {
        return reachableRange;
    }

    public double getOriginalAttackPower() {
        return attackPower;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Ability getCurrAbility() {
        return currAbility;
    }

    public List<Ability> getAbilities() {
        return Collections.unmodifiableList(abilities);
    }

    /**
     * @param ability
     * @Requires !abilities.contains(ability)
     */
    protected void addSpecialAbility(Ability ability) {
        if (!abilities.contains(ability)) {
            abilities.add(ability);
        }
    }

    /**
     * @param ability
     * @Requires abilities.contains(ability)
     */
    public void setCurrentAbility(Ability ability) {
        if (!abilities.contains(ability)) {
            this.currAbility = null;
        } else {
            this.currAbility = ability;
            notifyAbilityChanged(ability);
        }
    }

    private void notifyAbilityChanged(Ability ability) {
        switch (ability) {

            case PLAIN_ATTACK:
            case SPECIAL_ATTACK:
            case SPECIAL_HEALING:
                notifyActing();
                break;
            case SPECIAL_MOVE:
                notifyMoving();
                break;
        }
    }

    /**
     * @return
     * @Requires iconName != null
     */
    public IconFlyweight getIcon() {
        return IconFactory.getInstance().getMonsterIcon(iconName);
    }

    protected void setIcon(String iconName) {
        this.iconName = iconName;
    }

    public void move(Cell newPos) {
        setPosition(newPos);
    }

    public boolean attack(Piece target) {
        double distance = getTargetDistance(target);
        if (getCurrentReachableRange() >= distance) {
            double damage = getCurrentAttackPower();
            target.decreaseHealth(damage);
            notifyActed();
            return true;
        }
        return false;
    }

    public int getTargetDistance(Piece target) {
        return getTargetDistance(target.position);
    }

    public int getTargetDistance(Cell targetPosition) {
        return position.distance(targetPosition);
    }

    public Team getTeam() {
        return team;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    /**
     * @param healthGained
     * @Requires healthGained > 0
     */
    public void increaseHealth(double healthGained) {
        assert healthGained > 0;
        this.health += healthGained;
        notifyHealthChanged(healthGained);
    }

    /**
     * @param damage
     * @Requires damage > 0
     */
    public void decreaseHealth(double damage) {
        double trueDamage = damage - getArmor();
        health = Math.max(health - trueDamage, 0);
        notifyHealthChanged(-damage);
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public double getCurrentAttackPower() {
        double currPower = mode.getAttackPower(attackPower);
        if (currAbility == null || currAbility == Ability.PLAIN_ATTACK) {
            return currPower;
        }
        return RULE.createAttackStrategy(this).getAttackPower(currPower);
    }

    public void setAttackPower(double attackPower) {
        double delta = attackPower - this.attackPower;
        this.attackPower = attackPower;
        notifyPowerChanged(delta);
    }

    public int getCurrentReachableRange() {
        if (currAbility == null) {
            return reachableRange;
        }
        switch (currAbility) {
            default:
            case PLAIN_ATTACK:
            case SPECIAL_MOVE:
                return reachableRange;
            case SPECIAL_ATTACK:
                return RULE.createAttackStrategy(this).getAttackRange(reachableRange);
            case SPECIAL_HEALING:
                return RULE.createHealStrategy(this).getHealRange(reachableRange);
        }
    }

    public void setReachableRange(int reachableRange) {
        int delta = reachableRange - this.reachableRange;
        this.reachableRange = reachableRange;
        notifyRangeChanged(delta);
    }

    public Cell getPosition() {
        return position;
    }

    /**
     * Change the position of this piece and notify all observers.
     *
     * @param position
     */
    public void setPosition(Cell position) {
        if (!position.equals(this.position)) {
            // reset `nextMove` for next round.
            nextMove = 0;
        }
        this.position = position;
        notifyMoved();
    }

    public int getNextMove() {
        return nextMove;
    }

    public boolean getShouldNotify() {
        return shouldNotify;
    }

    public void setShouldNotify(boolean shouldNotify) {
        this.shouldNotify = shouldNotify;
    }

    public void addPositionObserver(PiecePositionObserver observer) {
        posObservers.add(observer);
    }

    public void removeObserver(PiecePositionObserver observer) {
        posObservers.remove(observer);
    }

    public void addPropertyObserver(PiecePropertyObserver observer) {
        pptObservers.add(observer);
    }

    public void addActionObserver(PieceActionObserver observer) {
        actObservers.add(observer);
    }

    public void removeObserver(PiecePropertyObserver observer) {
        pptObservers.remove(observer);
    }

    /**
     * Notifies all observers that this piece is ready to move.
     */
    public void notifyMoving() {
        posObservers.forEach(o -> o.beforeMove(this));
    }

    public void notifyActing() {
        actObservers.forEach(o -> o.beforeActing(this));
    }

    public void notifyActed() {
        actObservers.forEach(o -> o.afterActing(this));
    }

    /**
     * Notifies all observers when the piece has moved to new position.
     */
    private void notifyMoved() {
        posObservers.forEach(o -> o.afterMove(this, shouldNotify));
    }

    /**
     * @Requires deltaHealth != 0
     */
    private void notifyHealthChanged(double deltaHealth) {
        if (deltaHealth == 0) {
            return;
        }
        pptObservers.forEach(o -> o.healthChanged(deltaHealth, shouldNotify));
    }

    /**
     * @Requires deltaPower != 0
     */
    private void notifyPowerChanged(double deltaPower) {
        if (deltaPower == 0) {
            return;
        }
        pptObservers.forEach(o -> o.powerChanged(deltaPower, shouldNotify));
    }

    /**
     * @Requires deltaRange != 0
     */
    private void notifyRangeChanged(int deltaRange) {
        if (deltaRange == 0) {
            return;
        }
        pptObservers.forEach(o -> o.rangeChanged(deltaRange, shouldNotify));
    }

    @Override
    public void valueChanged(int value) {
        this.nextMove = value;
    }

    public double getArmor() {
        return mode.getArmor(armor);
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public Mode getCurrMode() {
        return mode;
    }

    public int distance(Piece other) {
        return position.distance(other.position);
    }

    public void act(Piece target) {
        switch (currAbility) {

            case PLAIN_ATTACK:
                attack(target);
                break;
            case SPECIAL_ATTACK:
                RULE.createAttackStrategy(this).attack(target);
                break;
            case SPECIAL_HEALING:
                RULE.createHealStrategy(this).heal(target);
                break;
            case SPECIAL_MOVE:
                RULE.createMoveStrategy(this).move();
                break;
        }
    }

    @Override
    public String toString() {
        return String.format("%s: Team: %s, Position: %s", getClass().getSimpleName(), team, getPosition());
    }
}
