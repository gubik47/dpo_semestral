package cz.fit.dpo.mvcshooter.model.entity.enemy;

import cz.fit.dpo.mvcshooter.model.Config;

import java.util.Random;

/**
 * Created by Jakub on 19.11.2015.
 */
public class SimpleEnemy extends Enemy {

    public SimpleEnemy() {
        super();
    }

    public SimpleEnemy(Enemy enemy) {
        x = enemy.getX();
        y = enemy.getY();
    }

    @Override
    public void move() {
        // simple enemy does not move
    }
}
