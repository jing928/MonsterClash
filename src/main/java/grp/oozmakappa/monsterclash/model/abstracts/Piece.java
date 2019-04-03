package grp.oozmakappa.monsterclash.model.abstracts;

import grp.oozmakappa.monsterclash.model.interfaces.DiceObserver;
import grp.oozmakappa.monsterclash.utils.IconUtil;
import grp.oozmakappa.monsterclash.view.interfaces.PieceObserver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jing Li
 */
public abstract class Piece implements DiceObserver {

    private final String id;
    private String iconName;
    private double health;
    private Cell position;
    private double attackPower;
    private int attackRange;
    private int nextMove;
    private Collection<PieceObserver> observers;

    public Piece(String id, Cell position, double health, double attackPower, int attackRange) {
        this.id = id;
        this.position = position;
        this.health = health;
        this.attackPower = attackPower;
        this.attackRange = attackRange;
        observers = new ArrayList<>();
    }

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

    public String getId() {
        return id;
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

    public void setPosition(Cell position) {
        this.position = position;
        notifyMoved();
    }

    public int getNextMove() {
        return nextMove;
    }

    @Deprecated
    public void setNextMove(int nextMove) {
        this.nextMove = nextMove;
    }

    public void addObserver(PieceObserver observer) {
        observers.add(observer);
    }

    public void notifyMoving() {
        for (PieceObserver observer : observers) {
            observer.positionChanging(this);
        }
    }

    public void notifyMoved() {
        for (PieceObserver observer : observers) {
            observer.positionChanged();
        }
        nextMove = 0;
    }

    @Override
    public void valueChanged(int value) {
        this.nextMove = value;
    }
//    private void notifyNextPosition(Point nextPosition) {
//        for (PieceObserver observer : observers) {
//            observer.positionVerified(this, nextPosition);
//        }
//    }

}
