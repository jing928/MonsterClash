package grp.oozmakappa.monsterclash.model.abstracts;

import grp.oozmakappa.monsterclash.model.Ability;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.interfaces.DiceObserver;
import grp.oozmakappa.monsterclash.utils.IconUtil;
import grp.oozmakappa.monsterclash.view.interfaces.PieceObserver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Jing Li
 * @Invariant nextMove >= 0
 * @Invariant health >= 0
 * @Invariant observers.size() >= 0
 */
public abstract class Piece implements DiceObserver {

    private final Team team;
    private final Collection<PieceObserver> observers;
    private final List<Ability> abilities;
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
        observers = new ArrayList<>();
        abilities = new ArrayList<>();
        abilities.add(Ability.PLAIN_ATTACK);
    }

    public List<Ability> getAbilities() {
        return Collections.unmodifiableList(abilities);
    }

    /**
     * @return
     * @Requires iconName != null
     */
    public ImageIcon getIcon() {
        return IconUtil.getMonsterIcon(iconName);
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

    public void increaseHealth(double health) {
        this.health += health;
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

    public int getAttackRange() {
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

    protected void addSpecialAbility(Ability ability) {
        abilities.add(ability);
    }

    @Override
    public void valueChanged(int value) {
        this.nextMove = value;
    }


}
