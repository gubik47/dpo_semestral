package cz.fit.dpo.mvcshooter.state;

import cz.fit.dpo.mvcshooter.factory.Factory;
import cz.fit.dpo.mvcshooter.model.entity.Cannon;
import cz.fit.dpo.mvcshooter.model.entity.Missile;

import java.util.List;

/**
 * Created by Kuba on 15. 11. 2015.
 */
public interface FireMode {
    public List<Missile> shoot(Cannon cannon, int gravity, Factory factory);
    public String getName();
}
