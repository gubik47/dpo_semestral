import cz.fit.dpo.mvcshooter.model.Config;
import cz.fit.dpo.mvcshooter.model.entity.Cannon;
import cz.fit.dpo.mvcshooter.state.DoubleFireMode;
import cz.fit.dpo.mvcshooter.state.FireMode;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by Kuba on 20. 12. 2015.
 */
public class CannonTest {

    @Test
    public void testChangeAngle() {
        Cannon cannon = new Cannon();

        cannon.increaseAngle();
        assertEquals(Config.CANNON_INIT_ANGLE + Config.CANNON_ANGLE_STEP, cannon.getAngle());
        cannon.decreaseAngle();
        assertEquals(Config.CANNON_INIT_ANGLE, cannon.getAngle());
    }

    @Test
    public void testChangeForce() {
        Cannon cannon = new Cannon();

        cannon.increaseForce();
        assertEquals(Config.CANNON_INIT_FORCE + Config.CANNON_FORCE_STEP, cannon.getForce());
        cannon.decreaseForce();
        assertEquals(Config.CANNON_INIT_FORCE, cannon.getForce());
    }

    @Test
    public void testChangeFireMode() {
        Cannon cannon = new Cannon();

        FireMode initFireMode = cannon.getFireMode();
        cannon.changeFireMode();
        assertThat(cannon.getFireMode(), not(instanceOf(initFireMode.getClass())));
    }

    @Test
    public void testMove() {
        Cannon cannon = new Cannon();

        int initPosition = cannon.getY();

        cannon.moveUp();
        cannon.moveUp();
        assertEquals(initPosition - 2*Config.CANNON_MOVE_STEP, cannon.getY());
        cannon.moveDown();
        assertEquals(initPosition - Config.CANNON_MOVE_STEP, cannon.getY());
        cannon.moveDown();
        assertEquals(initPosition, cannon.getY());
    }
}
