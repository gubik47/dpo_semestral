import cz.fit.dpo.mvcshooter.model.Config;
import cz.fit.dpo.mvcshooter.model.TimeKeeper;
import cz.fit.dpo.mvcshooter.model.entity.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.entity.enemy.RealisticEnemy;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;

/**
 * Created by Kuba on 20. 12. 2015.
 */
public class EnemyTest {

    @Test
    public void testMove() {
        Enemy enemy = new RealisticEnemy();

        int initX = enemy.getX();
        int initY = enemy.getY();

        try {
            Thread.sleep(Config.ENEMY_REALISTIC_MOVEMENT_INTERVAL_IN_SECONDS * 1000 + 100);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(ModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        TimeKeeper.setTime(Config.ENEMY_REALISTIC_MOVEMENT_INTERVAL_IN_SECONDS * 1000 + 100);

        enemy.move();

        int diffX = Math.abs(initX - enemy.getX());
        int diffY = Math.abs(initY - enemy.getY());

        assertTrue(diffX > 0 || diffY > 0);
    }
}
