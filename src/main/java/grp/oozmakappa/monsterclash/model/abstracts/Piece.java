package grp.oozmakappa.monsterclash.model.abstracts;

import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.command.Command;
import grp.oozmakappa.monsterclash.model.command.CommandManager;
import grp.oozmakappa.monsterclash.model.command.HealthChangeCommand;
import grp.oozmakappa.monsterclash.model.interfaces.DiceObserver;
import grp.oozmakappa.monsterclash.utils.IconFactory;
import grp.oozmakappa.monsterclash.utils.flyweights.IconFlyweight;
import grp.oozmakappa.monsterclash.view.observers.PiecePositionObserver;
import grp.oozmakappa.monsterclash.view.observers.PiecePropertyObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private boolean isUndoing = false;
    protected static final Logger LOG = LogManager.getLogger();

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
            Command healthChangeCmd = new HealthChangeCommand(target, -damage);
            CommandManager manager = CommandManager.getInstance();
            manager.storeAndExecute(healthChangeCmd);
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
        double delta = health - this.health;
        this.health = health;
        notifyHealthChanged(delta);
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
        notifyMoved(isUndoing);
    }

    public int getNextMove() {
        return nextMove;
    }

    public boolean isUndoing() {
        return isUndoing;
    }

    public void setUndoing(boolean undoing) {
        isUndoing = undoing;
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
    private void notifyMoved(boolean isUndoing) {
        posObservers.forEach(o -> o.positionChanged(this, isUndoing));
    }

    private void notifyHealthChanged(double deltaHealth) {
        if (deltaHealth == 0) {
            return;
        }
        pptObservers.forEach(o -> o.healthChanged(deltaHealth, isUndoing));
    }

    private void notifyPowerChanged(double deltaPower) {
        if (deltaPower == 0) {
            return;
        }
        pptObservers.forEach(o -> o.powerChanged(deltaPower, isUndoing));
    }

    private void notifyRangeChanged(int deltaRange) {
        if (deltaRange == 0) {
            return;
        }
        pptObservers.forEach(o -> o.rangeChanged(deltaRange, isUndoing));
    }


    @Override
    public void valueChanged(int value) {
        this.nextMove = value;
    }
}
