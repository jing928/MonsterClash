package grp.oozmakappa.monsterclash.model.abstracts;

import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.interfaces.DiceObserver;
import grp.oozmakappa.monsterclash.utils.IconFactory;
import grp.oozmakappa.monsterclash.utils.flyweights.IconFlyweight;
import grp.oozmakappa.monsterclash.view.observers.PiecePositionObserver;
import grp.oozmakappa.monsterclash.view.observers.PiecePropertyObserver;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jing Li
 * @Invariant nextMove >= 0
 * @Invariant health >= 0
 * @Invariant posObservers.size() >= 0
 */
public abstract class Piece implements DiceObserver {

    private final Team team;
    private final Collection<PiecePositionObserver> posObservers;
    private final Collection<PiecePropertyObserver> pptObservers;
    private String iconName;
    private double health;
    private Cell position;
    private double attackPower;
    private int attackRange;
    private int nextMove;

    public Piece(Team team, Cell position, double health, double attackPower, int attackRange) {
        this.team = team;
        this.position = position;
        this.health = health;
        this.attackPower = attackPower;
        this.attackRange = attackRange;
        posObservers = new ArrayList<>();
        pptObservers = new ArrayList<>();
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
            double damage = attackPower;
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
        health = damage > health ? 0 : health - damage;
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
        this.attackPower = attackPower;
        notifyPowerChanged(attackPower - this.attackPower);
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
        notifyRangeChanged(attackRange - this.attackRange);
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


    public void addObserver(PiecePositionObserver observer) {
        posObservers.add(observer);
    }

    public void removeObserver(PiecePositionObserver observer) {
        posObservers.remove(observer);
    }

    public void addObserver(PiecePropertyObserver observer) {
        pptObservers.add(observer);
    }

    public void removeObserver(PiecePropertyObserver observer) {
        pptObservers.remove(observer);
    }

    /**
     * Notifies all observers that this piece is ready to move.
     */
    public void notifyMoving() {
        posObservers.forEach(o -> o.positionChanging(this));
    }

    /**
     * Notifies all posObservers when the piece has moved to new position.
     */
    private void notifyMoved() {
        posObservers.forEach(o -> o.positionChanged(this));
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
}
