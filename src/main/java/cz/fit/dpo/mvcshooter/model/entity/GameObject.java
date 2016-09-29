package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.model.Config;
import cz.fit.dpo.mvcshooter.model.TimeKeeper;
import cz.fit.dpo.mvcshooter.model.Visitable;
import cz.fit.dpo.mvcshooter.model.Visitor;

/**
 * Created by Jakub on 22.10.2015.
 */
public abstract class GameObject implements Visitable {
    protected int x;
    protected int y;
    protected int size;
    protected boolean toBeRemoved = false;
    protected long initialTime;

    public GameObject() {
        x = 0;
        y = 0;
        size = 0;
        initialTime = TimeKeeper.getTime();
    }

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int xPos) {
        this.x = xPos;
    }

    public int getY() {
        return y;
    }

    public void setY(int yPos) {
        this.y = yPos;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean collidesWithObject(GameObject target) {
        return Math.abs(x - target.getX()) < Config.MODEL_COLLISION_AREA &&
                Math.abs(y - target.getY()) < Config.MODEL_COLLISION_AREA;
    }

    public void accept(Visitor visitor) {
    }

    public boolean isToBeRemoved() {
        return toBeRemoved;
    }

    public void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }

    public long getInitialTime() { return initialTime; }
}
