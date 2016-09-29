package cz.fit.dpo.mvcshooter.model.entity.enemy;

import cz.fit.dpo.mvcshooter.model.Config;
import cz.fit.dpo.mvcshooter.model.TimeKeeper;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jakub on 19.11.2015.
 */
public class RealisticEnemy extends Enemy {

    private int currentTimeInSeconds;

    public RealisticEnemy() {
        super();
        currentTimeInSeconds = 0;
    }

    public RealisticEnemy(Enemy enemy) {
        x = enemy.getX();
        y = enemy.getY();

        initialTime = enemy.getInitialTime();
        currentTimeInSeconds = enemy.getCurrentTimeInSeconds();
    }

    @Override
    public int getCurrentTimeInSeconds() { return currentTimeInSeconds; }

    @Override
    public void move() {
        // move every x(ENEMY_REALISTIC_MOVEMENT_INTERVAL_IN_SECONDS) seconds
        int newTimeInSeconds = (int)(TimeKeeper.getTime() - this.getInitialTime()) / 1000;
        if (newTimeInSeconds > currentTimeInSeconds) {
            currentTimeInSeconds = newTimeInSeconds;
            if (currentTimeInSeconds % Config.ENEMY_REALISTIC_MOVEMENT_INTERVAL_IN_SECONDS == 0) {
                setNewRandomPosition();
            }
        }
    }
}
