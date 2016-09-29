package cz.fit.dpo.mvcshooter.model;

/**
 * Created by Kuba on 29. 10. 2015.
 */
public abstract class Config {
    public static final int CANNON_MOVE_STEP = 10;
    public static final int CANNON_INIT_X_POSITION = 15;
    public static final int CANNON_INIT_Y_POSITION = 50;
    public static final int CANNON_SIZE = 0;
    public static final int CANNON_INIT_ANGLE = -30;
    public static final int CANNON_ANGLE_STEP = 5;
    public static final int CANNON_ANGLE_MIN = -75;
    public static final int CANNON_ANGLE_MAX = 0;
    public static final int CANNON_INIT_FORCE = 50;
    public static final int CANNON_FORCE_STEP = 1;
    public static final int CANNON_FORCE_MAX = 100;
    public static final int CANNON_FORCE_MIN = 1;

    public static final int MISSILE_INIT_DISTANCE_FROM_CANNON = 30;

    public static final int ENEMY_REALISTIC_MOVEMENT_INTERVAL_IN_SECONDS = 4;

    public static final int MODEL_TIME_TICK_IN_MILLISECONDS = 10;
    public static final int MODEL_DEFAULT_ENEMY_COUNT = 3;
    public static final int MODEL_ENEMY_SPAWN_INTERVAL_IN_MILLISECONDS = 5000;
    public static final int MODEL_COLLISION_AREA = 20;
    public static final int MODEL_INIT_GRAVITY = 7;
    public static final int MODEL_GRAVITY_STEP = 1;
    public static final int MODEL_GRAVITY_MIN = 4;
    public static final int MODEL_GRAVITY_MAX = 12;

    public static final int GAME_INFO_X = 35;
    public static final int GAME_INFO_Y = 20;

    public static final String GAME_MODE = "REALISTIC";

    public static final int WINDOW_X_SIZE = 500;
    public static final int WINDOW_Y_SIZE = 500;
    public static final int WINDOW_TOP_PADDING = 50;
    public static final int WINDOW_RIGHT_PADDING = 50;
    public static final int WINDOW_BOTTOM_PADDING = 50;
    public static final int WINDOW_LEFT_PADDING = 50;
}
