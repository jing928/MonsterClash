package grp.oozmakappa.monsterclash.model.abstracts;

/**
 * @author Jing Li
 */
public abstract class Piece {

    protected final String id;
    protected double health;
    protected Cell position;
    protected double attackPower;
    protected int attackRange;

    public Piece(String id, Cell position) {
        this.id = id;
        this.position = position;
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
    }
}
