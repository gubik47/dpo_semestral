package cz.fit.dpo.mvcshooter.strategy;

import cz.fit.dpo.mvcshooter.model.entity.Missile;

/**
 * Created by Kuba on 14. 11. 2015.
 */
public interface MissileShootingStrategy {

    public void move(Missile missile);
}
