package grp.oozmakappa.monsterclash.model.abstracts;

import grp.oozmakappa.monsterclash.model.Ability;
import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.interfaces.DiceObserver;
import grp.oozmakappa.monsterclash.model.strategies.Mode;
import grp.oozmakappa.monsterclash.utils.IconFactory;
import grp.oozmakappa.monsterclash.utils.flyweights.IconFlyweight;
import grp.oozmakappa.monsterclash.view.observers.PieceActionObserver;
import grp.oozmakappa.monsterclash.view.observers.PiecePositionObserver;
import grp.oozmakappa.monsterclash.view.observers.PiecePropertyObserver;

import java.util.*;

/**
 * @author Jing Li
 * @Invariant nextMove >= 0
 * @Invariant health >= 0
 * @Invariant posObservers.size() >= 0
 */
public abstract class Piece implements DiceObserver {

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
    private int attackRange;
    private double armor;
    private int nextMove;
    private Mode mode;

    public Piece(Team team, Cell position, double health, double attackPower, int attackRange) {
        this.team = team;
        this.position = position;
        this.health = health;
        this.attackPower = attackPower;
        this.attackRange = attackRange;
        mode = null;
        posObservers = new HashSet<>();
        pptObservers = new HashSet<>();
        actObservers = new HashSet<>();
        abilities = new ArrayList<>();
        abilities.add(Ability.PLAIN_ATTACK);
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
     * @deprecated TODO: use state pattern?
     */
    @Deprecated
    public void setCurrentAbility(Ability ability) {
        if (!abilities.contains(ability)) {
            this.currAbility = null;
        } else {
            this.currAbility = ability;
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
        if (attackRange >= distance) {
            double damage = mode.getAttackPower(attackPower);
            target.decreaseHealth(damage);
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
        double trueDamage = damage - mode.getArmor(this.armor);
        health = Math.max(health - trueDamage, 0);
        notifyHealthChanged(-damage);
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public double getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(double attackPower) {
        double delta = attackPower - this.attackPower;
        this.attackPower = attackPower;
        notifyPowerChanged(delta);
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        int delta = attackRange - this.attackRange;
        this.attackRange = attackRange;
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

    public void notifyAttacking() {
        actObservers.forEach(o -> o.beforeActing(this));
    }

    /**
     * Notifies all observers when the piece has moved to new position.
     */
    private void notifyMoved() {
        posObservers.forEach(o -> o.afterMove(this));
    }

    private void notifyHealthChanged(double deltaHealth) {
        pptObservers.forEach(o -> o.healthChanged(deltaHealth));
    }

    private void notifyPowerChanged(double deltaPower) {
        pptObservers.forEach(o -> o.powerChanged(deltaPower));
    }

    private void notifyRangeChanged(int deltaRange) {
        pptObservers.forEach(o -> o.rangeChanged(deltaRange));
    }

    @Override
    public void valueChanged(int value) {
        this.nextMove = value;
    }

    public double getArmor() {
        return armor;
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
}
