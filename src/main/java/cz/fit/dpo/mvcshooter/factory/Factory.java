package cz.fit.dpo.mvcshooter.factory;

import cz.fit.dpo.mvcshooter.model.entity.Collision;
import cz.fit.dpo.mvcshooter.model.entity.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.entity.Missile;

/**
 * Created by Kuba on 15. 11. 2015.
 */
public interface Factory {
    public Missile makeMissile(int x, int y, int force, int angle, int gravity);
    public Enemy makeEnemy();
    public Enemy makeEnemy(Enemy enemy);
    public Collision makeCollision(int x, int y);
}
