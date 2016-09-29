import cz.fit.dpo.mvcshooter.model.Config;
import cz.fit.dpo.mvcshooter.model.entity.Missile;
import cz.fit.dpo.mvcshooter.strategy.MissileShootingStrategy;
import cz.fit.dpo.mvcshooter.strategy.RealisticMissileShootingStrategy;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by Kuba on 20. 12. 2015.
 */
public class MissileTest {

    @Test
    public void testMove() {
        MissileShootingStrategy strategyMock = Mockito.mock(RealisticMissileShootingStrategy.class);
        Missile missile = new Missile(0, 0,
                Config.CANNON_INIT_FORCE,
                Config.CANNON_INIT_ANGLE,
                Config.MODEL_INIT_GRAVITY, strategyMock);

        missile.move();
        Mockito.verify(strategyMock, Mockito.times(1)).move(missile);
    }
}
