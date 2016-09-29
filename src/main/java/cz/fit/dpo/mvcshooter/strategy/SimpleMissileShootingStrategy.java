package cz.fit.dpo.mvcshooter.strategy;

import cz.fit.dpo.mvcshooter.model.Config;
import cz.fit.dpo.mvcshooter.model.TimeKeeper;
import cz.fit.dpo.mvcshooter.model.entity.Missile;

/**
 * Created by Kuba on 14. 11. 2015.
 */
public class SimpleMissileShootingStrategy implements MissileShootingStrategy {

    public void move(Missile missile) {
        double currentTime = (new Long(TimeKeeper.getTime() - missile.getInitialTime())).doubleValue();
        currentTime /= 100;

        int newMissileXPos = (int)(missile.getInitialX() + currentTime * 40);
        missile.setX(newMissileXPos);

        int newMissileYPos = (int)(missile.getInitialY() + missile.getX() * missile.getAngleInRadians());
        missile.setY(newMissileYPos);
    }
}
