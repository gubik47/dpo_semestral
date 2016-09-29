package cz.fit.dpo.mvcshooter.state;

import cz.fit.dpo.mvcshooter.factory.Factory;
import cz.fit.dpo.mvcshooter.model.Config;
import cz.fit.dpo.mvcshooter.model.entity.Cannon;
import cz.fit.dpo.mvcshooter.model.entity.Missile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 15. 11. 2015.
 */
public class SingleFireMode implements FireMode {
    public List<Missile> shoot(Cannon cannon, int gravity, Factory factory) {
        List<Missile> missiles = new ArrayList<Missile>();

        missiles.add(factory.makeMissile(cannon.getX() + Config.MISSILE_INIT_DISTANCE_FROM_CANNON, cannon.getY(), cannon.getForce(), cannon.getAngle(), gravity));

        return missiles;
    }

    public String getName() { return "Single"; }
}
