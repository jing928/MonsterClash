package grp.oozmakappa.monsterclash.model.abstracts;

import grp.oozmakappa.monsterclash.model.Ability;
import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.command.AttackCommand;
import grp.oozmakappa.monsterclash.model.interfaces.DiceObserver;
import grp.oozmakappa.monsterclash.model.rules.AbstractRuleFactory;
import grp.oozmakappa.monsterclash.model.strategies.modes.DefaultMode;
import grp.oozmakappa.monsterclash.model.strategies.modes.Mode;
import grp.oozmakappa.monsterclash.utils.IconFactory;
import grp.oozmakappa.monsterclash.utils.flyweights.IconFlyweight;
import grp.oozmakappa.monsterclash.view.observers.PieceActionObserver;
import grp.oozmakappa.monsterclash.view.observers.PiecePositionObserver;
import grp.oozmakappa.monsterclash.view.observers.PiecePropertyObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * @author Jing Li
 * @Invariant nextMove >= 0
 * @Invariant health >= 0
 * @Invariant posObservers.size() >= 0
 */
public abstract class Piece implements DiceObserver {

    protected static final Logger LOG = LogManager.getLogger();
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

    public Piece(Team team, Cell position, double health, double attackPower, double armor, int reachableRange) {
        this.team = team;
        this.position = position;
        this.health = health;
        this.attackPower = attackPower;
        this.armor = armor;
        this.reachableRange = reachableRange;
        mode = DefaultMode.getInstance();
        posObservers = new HashSet<>();
        pptObservers = new HashSet<>();
        actObservers = new HashSet<>();
        abilities = new ArrayList<>();
        abilities.add(Ability.PLAIN_ATTACK);
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public int getOriginalRange() {
        return reachableRange;
    }

    public double getOriginalAttackPower() {
        return attackPower;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        double delta = mode.getAttackPower(attackPower) - attackPower;
        notifyPowerChanged(delta);
        delta = mode.getArmor(armor) - armor;
        notifyArmorChanged(delta);
    }

    public void setMode() {
        double deltaPower = attackPower - mode.getAttackPower(attackPower);
        double deltaArmor = armor - mode.getArmor(armor);
        this.mode = DefaultMode.getInstance();
        notifyPowerChanged(deltaPower);
        notifyArmorChanged(deltaArmor);
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
        resetCurrentAbility(ability);
        notifyAbilityChanged();
        double deltaPower = getCurrentAttackPower() - getOriginalAttackPower();
        notifyPowerChanged(deltaPower);
        int deltaRange = getCurrentReachableRange() - getOriginalRange();
        notifyRangeChanged(deltaRange);
    }

    public void resetCurrentAbility(Ability ability) {
        if (!abilities.contains(ability)) {
            this.currAbility = null;
        } else {
            this.currAbility = ability;
        }
    }

    private void notifyAbilityChanged() {
        if (currAbility == null) {
            return;
        }
        switch (currAbility) {

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

    public void attack(Piece target) {
        double distance = getTargetDistance(target);
        if (getCurrentReachableRange() >= distance) {
            double damage = getCurrentAttackPower();
            target.decreaseHealth(damage);
            notifyActed();
        }
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

    /**
     * @Requires health >= 0
     */
    public void setHealth(double health) {
        double prevHealth = this.health;
        double delta = health - this.health;
        this.health = health;
        notifyHealthChanged(delta, prevHealth);
    }

    /**
     * @param healthGained
     * @Requires healthGained > 0
     */
    public void increaseHealth(double healthGained) {
        assert healthGained > 0;
        double prevHealth = health;
        this.health += healthGained;
        notifyHealthChanged(healthGained, prevHealth);
    }

    /**
     * @param damage
     * @Requires damage > 0
     */
    public void decreaseHealth(double damage) {
        double prevHealth = health;
        double trueDamage = damage - getArmor();
        health = Math.max(health - trueDamage, 0);
        notifyHealthChanged(-damage, prevHealth);
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

    /**
     * @Requires attackPower >= 0
     */
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

    /**
     * @Requires reachableRange >= 0
     */
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
        this.position = position;
        notifyMoved();
    }

    public int getNextMove() {
        return nextMove;
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
        posObservers.forEach(o -> o.afterMove(this));
    }

    /**
     * @Requires deltaHealth != 0
     */
    private void notifyHealthChanged(double deltaHealth, double prevHealth) {
        if (deltaHealth == 0) {
            return;
        }
        pptObservers.forEach(o -> o.healthChanged(getHealth(), deltaHealth, prevHealth));
    }

    /**
     * @Requires deltaPower != 0
     */
    private void notifyPowerChanged(double deltaPower) {
        if (deltaPower == 0) {
            return;
        }
        pptObservers.forEach(o -> o.powerChanged(getCurrentAttackPower(), deltaPower));
    }

    /**
     * @param deltaArmor
     * @Requires deltaArmor != 0
     */
    private void notifyArmorChanged(double deltaArmor) {
        if (deltaArmor == 0) {
            return;
        }
        pptObservers.forEach(o -> o.armorChanged(getCurrentArmor(), deltaArmor));
    }

    /**
     * @Requires deltaRange != 0
     */
    private void notifyRangeChanged(int deltaRange) {
        if (deltaRange == 0) {
            return;
        }
        pptObservers.forEach(o -> o.rangeChanged(getCurrentReachableRange(), deltaRange));
    }

    public boolean isWin() {
        return position.isHome() && this.team != position.getTeam();
    }

    @Override
    public void valueChanged(int value) {
        this.nextMove = value;
    }

    public double getArmor() {
        return mode.getArmor(armor);
    }

    /**
     * @param armor
     * @Requires armor >= 0
     */
    public void setArmor(double armor) {
        double delta = armor - this.armor;
        this.armor = armor;
        notifyArmorChanged(delta);
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
                AttackCommand.attack(this, target);
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
        return String.format("%s: Team: %s, Position: %s", getName(), team, getPosition());
    }

    public double getCurrentArmor() {
        return mode.getArmor(armor);
    }

}
