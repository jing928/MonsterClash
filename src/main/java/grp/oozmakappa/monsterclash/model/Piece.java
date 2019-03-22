package grp.oozmakappa.monsterclash.model;

public abstract class Piece {

    private String id;
    private int health;
    private int xPos;
    private int yPos;
    private int attackPower;
    private double attackRange;

    public Piece(String id, int health, int xPos, int yPos, int attackPower, int attackRange) {
        this.id = id;
        this.health = health;
        this.xPos = xPos;
        this.yPos = yPos;
        this.attackPower = attackPower;
        this.attackRange = attackRange;
    }

    public void move(int newX, int newY) {
        xPos = newX;
        yPos = newY;
    }

    public Boolean attack(Piece target) {
        double distance = getTargetDistance(target.getXPos(), target.getYPos());
        if (attackRange >= distance) {
            int damage = attackPower;
            target.decreaseHealth(damage);
            return true;
        }
        return false;
    }

    private double getTargetDistance(int targetX, int targetY) {
        double deltaX = targetX - xPos;
        double deltaY = targetY - yPos;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return distance;
    }

    public String getId() {
        return id;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void decreaseHealth(int damage) {
        health = damage > health ? 0 : health - damage;
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public double getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(double attackRange) {
        this.attackRange = attackRange;
    }

}
