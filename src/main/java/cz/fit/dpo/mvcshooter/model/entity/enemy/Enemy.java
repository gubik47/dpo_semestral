package cz.fit.dpo.mvcshooter.model.entity.enemy;

import cz.fit.dpo.mvcshooter.model.Config;
import cz.fit.dpo.mvcshooter.model.Visitor;
import cz.fit.dpo.mvcshooter.model.entity.GameObject;

import java.util.Random;

/**
 * Created by Jakub on 22.10.2015.
 */
public class Enemy extends GameObject {

    protected void setNewRandomPosition() {
        Random r = new Random();

        int x = r.nextInt(
                Config.WINDOW_X_SIZE - Config.WINDOW_LEFT_PADDING - Config.WINDOW_RIGHT_PADDING) +
                Config.WINDOW_RIGHT_PADDING;
        int y = r.nextInt(Config.WINDOW_Y_SIZE - Config.WINDOW_TOP_PADDING - Config.WINDOW_BOTTOM_PADDING) +
                Config.WINDOW_BOTTOM_PADDING;

        this.setX(x);
        this.setY(y);
    }

    public Enemy() {
        setNewRandomPosition();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void move() {}

    public int getCurrentTimeInSeconds() { return 0; }
}
