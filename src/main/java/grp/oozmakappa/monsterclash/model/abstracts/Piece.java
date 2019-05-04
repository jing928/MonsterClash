package grp.oozmakappa.monsterclash.model.abstracts;

import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.interfaces.DiceObserver;
import grp.oozmakappa.monsterclash.utils.IconFactory;
import grp.oozmakappa.monsterclash.utils.flyweights.IconFlyweight;
import grp.oozmakappa.monsterclash.view.interfaces.PieceObserver;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jing Li
 * @Invariant nextMove >= 0
 * @Invariant health >= 0
 * @Invariant observers.size() >= 0
 */
public abstract class Piece implements DiceObserver {

    private final Team team;
    private String iconName;
    private double health;
    private Cell position;
    private double attackPower;
    private int attackRange;
    private int nextMove;
    private Collection<PieceObserver> observers;

    public Piece(Team team, Cell position, double health, double attackPower, int attackRange) {
        this.team = team;
        this.position = position;
        this.health = health;
        this.attackPower = attackPower;
        this.attackRange = attackRange;
        observers = new ArrayList<>();
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

    public void decreaseHealth(double damage) {
        health = damage > health ? 0 : health - damage;
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
    }

    public double getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
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


    public void addObserver(PieceObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all observers that this piece is ready to move.
     */
    public void notifyMoving() {
        observers.forEach(o -> o.positionChanging(this));
    }

    /**
     * Notifies all observers when the piece has moved to new position.
     */
    private void notifyMoved() {
        observers.forEach(PieceObserver::positionChanged);
    }

    @Override
    public void valueChanged(int value) {
        this.nextMove = value;
    }

}
