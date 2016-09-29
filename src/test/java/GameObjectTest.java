import cz.fit.dpo.mvcshooter.model.entity.GameObject;
import cz.fit.dpo.mvcshooter.model.entity.enemy.Enemy;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Kuba on 20. 12. 2015.
 */
public class GameObjectTest {

    @Test
    public void testCollidesWithObject() {
        GameObject objectA = new Enemy();
        GameObject objectB = new Enemy();
        GameObject objectC = new Enemy();

        objectA.setX(0);
        objectA.setY(0);

        objectB.setX(0);
        objectB.setY(0);

        objectC.setX(75);
        objectC.setY(75);

        assertTrue(objectA.collidesWithObject(objectB));
        assertFalse(objectB.collidesWithObject(objectC));
    }
}
