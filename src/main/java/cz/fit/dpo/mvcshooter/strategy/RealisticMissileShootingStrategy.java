package cz.fit.dpo.mvcshooter.strategy;

import cz.fit.dpo.mvcshooter.model.TimeKeeper;
import cz.fit.dpo.mvcshooter.model.entity.Missile;

/**
 * Created by Kuba on 14. 11. 2015.
 */
public class RealisticMissileShootingStrategy implements MissileShootingStrategy {

    public void move(Missile missile) {
        double currentTime = (new Long(TimeKeeper.getTime() - missile.getInitialTime())).doubleValue();
        currentTime /= 100;

        int newMissileXPos = (int) (
                missile.getInitialX() + missile.getForce() * currentTime * Math.cos(missile.getAngleInRadians()));
        int newMissileYPos = (int) (
                missile.getInitialY() + missile.getForce() * currentTime * Math.sin(missile.getAngleInRadians())
                        + (0.5 * missile.getGravity() * Math.pow(currentTime, 2)));

        missile.setX(newMissileXPos);
        missile.setY(newMissileYPos);
    }
}
