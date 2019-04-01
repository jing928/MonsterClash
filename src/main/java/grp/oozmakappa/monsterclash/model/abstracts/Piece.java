package grp.oozmakappa.monsterclash.model.abstracts;

/**
 * @author Jing Li
 */
public abstract class Piece {

    private final String id;
    private double health;
    private Cell position;
    private double attackPower;
    private int attackRange;

    private int nextMove;
    private Collection<PieceObserver> observers;
    private String iconName;

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
        notifyNextPosition(position.getLocation());
    }

    public int getNextMove() {
        return nextMove;
    }

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

    public void notifyMoved(Point newPosition) {
        for (PieceObserver observer : observers) {
            observer.positionChanged(this, newPosition);
        }
        nextMove = 0;
    }

    private void notifyNextPosition(Point nextPosition) {
        for (PieceObserver observer : observers) {
            observer.positionVerified(this, nextPosition);
        }
    }

}
