package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.*;
import cz.fit.dpo.mvcshooter.model.entity.*;
import cz.fit.dpo.mvcshooter.model.entity.enemy.Enemy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Ondrej Stuchlik
 */
public class GraphicsDrawer implements Visitor {
    private static final int INFO_X = 5;
    private static final int INFO_Y = 15;
    
    private BufferedImage cannonImage;
    private BufferedImage enemyImage1;
    private BufferedImage enemyImage2;
    private BufferedImage missileImage;
    private BufferedImage collisionImage;
 
    private Graphics graphics;

    public GraphicsDrawer() {
        try {
            cannonImage = ImageIO.read(getClass().getResourceAsStream("/images/cannon.png"));
            enemyImage1 = ImageIO.read(getClass().getResourceAsStream("/images/enemy1.png"));
            enemyImage2 = ImageIO.read(getClass().getResourceAsStream("/images/enemy2.png"));
            missileImage = ImageIO.read(getClass().getResourceAsStream("/images/missile.png"));
            collisionImage = ImageIO.read(getClass().getResourceAsStream("/images/explode_29.png"));
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void visit(Cannon cannon) {
        drawCannon(cannon);
    }

    @Override
    public void visit(Enemy enemy) {
        drawEnemy(enemy);
    }

    @Override
    public void visit(Missile missile) {
        drawMissile(missile);
    }

    @Override
    public void visit(Collision collision) {
        drawCollision(collision);
    }

    public void visit(GameInfo gameInfo) { drawGameInfo(gameInfo); }

    public void drawCannon(Cannon cannon) {
        graphics.drawImage(cannonImage,
              cannon.getX() - cannonImage.getWidth()/2,
              cannon.getY() - cannonImage.getHeight()/2, null);
    }
    
    public void drawMissile(Missile missile) {
        graphics.drawImage(missileImage,
                missile.getX() - missileImage.getWidth()/2,
                missile.getY() - missileImage.getHeight()/2, null);
    }
    
    public void drawEnemy(Enemy enemy) {
        graphics.drawImage(enemyImage1,
                enemy.getX() - enemyImage1.getWidth()/2,
                enemy.getY() - enemyImage1.getHeight()/2, null);
    }
    
    public void drawCollision(Collision collision) {
        graphics.drawImage(collisionImage,
                collision.getX() - collisionImage.getWidth()/2,
                collision.getY() - collisionImage.getHeight()/2, null);
    }
    
    public void drawGameInfo(GameInfo gameInfo) {
        graphics.drawString(
                "SCORE: " + gameInfo.getScore() +
                        ", GRAVITY: " + gameInfo.getGravity() +
                        ", ANGLE: " + -gameInfo.getCannonAngle() +
                        ", FORCE: " + gameInfo.getCannonForce() +
                        ", FIRE-MODE: " + gameInfo.getFireMode(), gameInfo.getX(), gameInfo.getY()
        );
    }
}
