package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.entity.Cannon;
import cz.fit.dpo.mvcshooter.model.entity.Collision;
import cz.fit.dpo.mvcshooter.model.entity.GameInfo;
import cz.fit.dpo.mvcshooter.model.entity.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.entity.Missile;

/**
 * Created by Kuba on 29. 10. 2015.
 */
public interface Visitor {
    void visit(Cannon cannon);
    void visit(Enemy enemy);
    void visit(Missile missile);
    void visit(Collision collision);
    void visit(GameInfo gameInfo);
}
