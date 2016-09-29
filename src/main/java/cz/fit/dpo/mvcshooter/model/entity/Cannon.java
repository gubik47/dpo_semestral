package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.factory.Factory;
import cz.fit.dpo.mvcshooter.model.Config;
import cz.fit.dpo.mvcshooter.model.Visitor;
import cz.fit.dpo.mvcshooter.state.DoubleFireMode;
import cz.fit.dpo.mvcshooter.state.FireMode;
import cz.fit.dpo.mvcshooter.state.SingleFireMode;

import java.util.List;

/**
 * Created by Jakub on 22.10.2015.
 */
public class Cannon extends GameObject {
    private int force;
    private int angle;

    private FireMode currentFireMode;

    private FireMode doubleFireMode;
    private FireMode singleFireMode;

    public Cannon() {
        super(Config.CANNON_INIT_X_POSITION, Config.CANNON_INIT_Y_POSITION);
        this.size = Config.CANNON_SIZE;
        this.angle = Config.CANNON_INIT_ANGLE;
        this.force = Config.CANNON_INIT_FORCE;

        doubleFireMode = new DoubleFireMode();
        singleFireMode = new SingleFireMode();

        currentFireMode = singleFireMode;
    }

    public Cannon(Cannon oldCannon) {
        this.x = oldCannon.getX();
        this.y = oldCannon.getY();

        this.size = oldCannon.getSize();
        this.angle = oldCannon.getAngle();
        this.force = oldCannon.getForce();

        doubleFireMode = new DoubleFireMode();
        singleFireMode = new SingleFireMode();

        currentFireMode = oldCannon.getFireMode();
    }

    public void moveUp() {
        this.y -= Config.CANNON_MOVE_STEP;
    }

    public void moveDown() {
        this.y += Config.CANNON_MOVE_STEP;
    }

    public List<Missile> shootMissile(int gravity, Factory factory) {
        return currentFireMode.shoot(this, gravity, factory);
    }

    public int getForce() { return this.force; }

    public int getAngle() { return this.angle; }

    public void increaseAngle() {
        angle += Config.CANNON_ANGLE_STEP;
        if (angle > Config.CANNON_ANGLE_MAX) {
            angle = Config.CANNON_ANGLE_MAX;
        }
    }

    public void decreaseAngle() {
        angle -= Config.CANNON_ANGLE_STEP;
        if (angle < Config.CANNON_ANGLE_MIN) {
            angle = Config.CANNON_ANGLE_MIN;
        }
    }

    public void increaseForce() {
        this.force += Config.CANNON_FORCE_STEP;
        if (this.force > Config.CANNON_FORCE_MAX) {
            this.force = Config.CANNON_FORCE_MAX;
        }
    }

    public void decreaseForce() {
        this.force -= Config.CANNON_FORCE_STEP;
        if (this.force < Config.CANNON_FORCE_MIN) {
            this.force = Config.CANNON_FORCE_MIN;
        }
    }

    public void setFireMode(FireMode newFireMode) {
        currentFireMode = newFireMode;
    }

    public void changeFireMode() {
        if (currentFireMode == singleFireMode) {
            setFireMode(doubleFireMode);
        } else {
            setFireMode(singleFireMode);
        }
    }

    public FireMode getFireMode() { return currentFireMode; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
