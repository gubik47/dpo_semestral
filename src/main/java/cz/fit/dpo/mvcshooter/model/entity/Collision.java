package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.model.Config;
import cz.fit.dpo.mvcshooter.model.Visitor;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jakub on 22.10.2015.
 */
public class Collision extends GameObject {

    Timer duration;

    public Collision(int x, int y) {
        super(x, y);

        duration = new Timer();
        duration.schedule(new TimerTask() {
            @Override
            public void run() {
                setToBeRemoved(true);
            }
        }, 2000, 2000);
    }

    public Collision(Collision collision) {
        super(collision.getX(), collision.getY());

        duration = new Timer();
        duration.schedule(new TimerTask() {
            @Override
            public void run() {
                setToBeRemoved(true);
            }
        }, 2000, 2000);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
