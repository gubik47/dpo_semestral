package cz.fit.dpo.mvcshooter.model.entity;

import cz.fit.dpo.mvcshooter.model.Config;
import cz.fit.dpo.mvcshooter.model.Visitor;
import cz.fit.dpo.mvcshooter.state.FireMode;

/**
 * Created by Kuba on 29. 10. 2015.
 */
public class GameInfo extends GameObject {
    private int score;
    private int gravity;
    private int cannonAngle;
    private int cannonForce;
    private String fireMode;

    public GameInfo(String fireModeName) {
        super(Config.GAME_INFO_X, Config.GAME_INFO_Y);

        score = 0;
        gravity = Config.MODEL_INIT_GRAVITY;
        cannonAngle = Config.CANNON_INIT_ANGLE;
        cannonForce = Config.CANNON_INIT_FORCE;
        fireMode = fireModeName;
    }

    public GameInfo(GameInfo gameInfo) {
        super(gameInfo.getX(), gameInfo.getY());

        score = gameInfo.getScore();
        gravity = gameInfo.getGravity();
        cannonAngle = gameInfo.getCannonAngle();
        cannonForce = gameInfo.getCannonForce();
        fireMode = gameInfo.getFireMode();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getCannonAngle() {
        return cannonAngle;
    }

    public void setCannonAngle(int cannonAngle) {
        this.cannonAngle = cannonAngle;
    }

    public int getCannonForce() {
        return cannonForce;
    }

    public void setCannonForce(int cannonForce) {
        this.cannonForce = cannonForce;
    }

    public String getFireMode() { return fireMode; }

    public void setFireMode(String fireMode) {
        this.fireMode = fireMode;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
