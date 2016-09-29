package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.model.Config;
import cz.fit.dpo.mvcshooter.model.TimeKeeper;
import cz.fit.dpo.mvcshooter.model.Visitor;
import cz.fit.dpo.mvcshooter.strategy.MissileShootingStrategy;
import cz.fit.dpo.mvcshooter.strategy.SimpleMissileShootingStrategy;

/**
 * Created by Jakub on 22.10.2015.
 */
public class Missile extends GameObject {
    private int initialX;
    private int initialY;
    private int force;
    private int gravity;
    private double angleInRadians;

    private MissileShootingStrategy shootingStrategy;

    public Missile(int xPos, int yPos, int cannonForce, int angle, int gravity, MissileShootingStrategy newShootingStrategy) {
        super(xPos, yPos);

        this.force = cannonForce;
        this.gravity = gravity;
        this.angleInRadians = degToRadians(angle);

        this.initialX = xPos;
        this.initialY = yPos;
        this.initialTime = TimeKeeper.getTime();

        shootingStrategy = newShootingStrategy;
    }

    public Missile(Missile missile) {
        super(missile.getX(), missile.getY());

        force = missile.getForce();
        gravity = missile.getGravity();
        angleInRadians = missile.getAngleInRadians();

        initialX = missile.getInitialX();
        initialY = missile.getInitialY();
        initialTime = missile.getInitialTime();
        shootingStrategy = missile.getMissileShootingStrategy();
    }

    private static double degToRadians(int angleInDegrees) {
        return angleInDegrees * ((2 * Math.PI) / 360);
    }

    private boolean isOutOfBounds() {
        if (x > (Config.WINDOW_X_SIZE + 50) || y > (Config.WINDOW_Y_SIZE + 50)) {
            return true;
        }
        return false;
    }

    public void move() {
        shootingStrategy.move(this);
        if (isOutOfBounds()) {
            setToBeRemoved(true);
        }
    }

    public int getForce() { return force; }

    public int getInitialX() { return initialX; }

    public int getInitialY() { return initialY; }

    public double getAngleInRadians() { return angleInRadians; }

    public int getGravity() { return gravity; }

    public void setMissileShootingStrategy(MissileShootingStrategy newStrategy) {
        shootingStrategy = newStrategy;
    }

    public MissileShootingStrategy getMissileShootingStrategy() {
        return shootingStrategy;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
