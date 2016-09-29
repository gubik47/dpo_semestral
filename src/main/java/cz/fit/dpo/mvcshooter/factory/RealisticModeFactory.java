package cz.fit.dpo.mvcshooter.factory;

import cz.fit.dpo.mvcshooter.model.entity.Collision;
import cz.fit.dpo.mvcshooter.model.entity.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.entity.Missile;
import cz.fit.dpo.mvcshooter.model.entity.enemy.RealisticEnemy;
import cz.fit.dpo.mvcshooter.strategy.RealisticMissileShootingStrategy;

/**
 * Created by Kuba on 15. 11. 2015.
 */
public class RealisticModeFactory implements Factory {

    public Missile makeMissile(int x, int y, int force, int angle, int gravity) {
        return new Missile(x, y, force, angle, gravity, new RealisticMissileShootingStrategy());
    }

    public Enemy makeEnemy() {
        return new RealisticEnemy();
    }

    public Enemy makeEnemy(Enemy enemy) { return new RealisticEnemy(enemy); }

    public Collision makeCollision(int x, int y) {
        return new Collision(x, y);
    }
}
