import cz.fit.dpo.mvcshooter.model.Config;
import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.ModelObserver;
import cz.fit.dpo.mvcshooter.state.DoubleFireMode;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.logging.Level;
import java.util.logging.Logger;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Kuba on 20. 12. 2015.
 */
public class ModelTest {

    @Test
    public void testIncreaseGravity() {
        Model model = new Model();
        model.increaseGravity();
        model.increaseGravity();
        model.increaseGravity();

        assertEquals(Config.MODEL_INIT_GRAVITY + 3 * Config.MODEL_GRAVITY_STEP, model.getGravity());
    }

    @Test
    public void testShootMissile() {
        Model model = new Model();

        model.shootCannon();
        model.changeFireMode();
        model.shootCannon();

        assertEquals(3, model.getMissiles().size());
        assertThat(model.getCannon().getFireMode(), instanceOf(DoubleFireMode.class));
    }

    @Test
    public void testSpawnEnemy() {
        Model model = new Model();

        try {
            Thread.sleep(Config.MODEL_ENEMY_SPAWN_INTERVAL_IN_MILLISECONDS);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(ModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertEquals(4, model.getEnemies().size());
    }

    @Test
    public void testSaveToMemento() {
        Model model = new Model();

        Model.Memento save1 = model.saveToMemento();
        model.increaseGravity();
        Model.Memento save2 = model.saveToMemento();

        assertThat(save1.getGravity(), not(save2.getGravity()));
    }

    @Test
    public void testUpdateCanvas() {
        Model model = new Model();
        ModelObserver mockObs = Mockito.mock(ModelObserver.class);

        model.registerObserver(mockObs);

        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(ModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        Mockito.verify(mockObs, Mockito.atLeast(10)).updateCanvas();
    }
}
