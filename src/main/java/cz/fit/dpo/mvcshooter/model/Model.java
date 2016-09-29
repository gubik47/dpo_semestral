package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.factory.Factory;
import cz.fit.dpo.mvcshooter.factory.RealisticModeFactory;
import cz.fit.dpo.mvcshooter.factory.SimpleModeFactory;
import cz.fit.dpo.mvcshooter.model.entity.*;
import cz.fit.dpo.mvcshooter.model.entity.enemy.Enemy;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jakub on 22.10.2015.
 */
public class Model {
    private Timer timer;

    private List<Enemy> enemies;
    private List<Missile> missiles;
    private List<Collision> collisions;
    private Cannon cannon;
    private GameInfo gameInfo;
    private int gravity;

    private List<ModelObserver> observers;
    private Factory factory;

    public Model() {
        cannon = new Cannon();
        gameInfo = new GameInfo(cannon.getFireMode().getName());
        enemies = new ArrayList<Enemy>();
        missiles = new ArrayList<Missile>();
        observers = new ArrayList<ModelObserver>();
        collisions = new ArrayList<Collision>();

        gravity = Config.MODEL_INIT_GRAVITY;

        // game mode selection
        if (Config.GAME_MODE.equals("REALISTIC")) {
            factory = new RealisticModeFactory();
        } else {
            factory = new SimpleModeFactory();
        }

        // spawn default enemies
        for (int i = 0; i < Config.MODEL_DEFAULT_ENEMY_COUNT; i++) {
            spawnEnemy();
        }

        startGameTimers();
    }

    public void registerObserver(ModelObserver o) {
        observers.add(o);
    }

    public void notifyObservers() {
        for (ModelObserver o : observers) {
            o.updateCanvas();
        }
    }

    private void removeDestroyedObjects() {
        for (int i = 0; i < missiles.size(); i++) {
            Missile missile = missiles.get(i);
            if (missile.isToBeRemoved()) {
                missiles.remove(missile);
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (enemy.isToBeRemoved()) {
                enemies.remove(enemy);
            }
        }

        for (int i = 0; i < collisions.size(); i++) {
            Collision collision = collisions.get(i);
            if (collision.isToBeRemoved()) {
                collisions.remove(collision);
            }
        }
    }

    private void checkCollisions() {
        for (Missile missile : missiles) {
            for (Enemy enemy : enemies) {
                if (missile.collidesWithObject(enemy)) {
                    enemy.setToBeRemoved(true);
                    missile.setToBeRemoved(true);
                    collisions.add(factory.makeCollision(enemy.getX(), enemy.getY()));
                    gameInfo.setScore(gameInfo.getScore() + 1);
                }
            }
        }
    }

    private void moveObjects() {
        for (Missile missile : missiles) {
            missile.move();
        }
        // TODO concurrent exception
        for (Enemy enemy : enemies) {
            enemy.move();
        }
    }

    private void spawnEnemy() {
        enemies.add(factory.makeEnemy());
    }

    public void moveCannonUp() {
        cannon.moveUp();
        notifyObservers();
    }

    public void moveCannonDown() {
        cannon.moveDown();
        notifyObservers();
    }

    public void shootCannon() {
        List<Missile> newMissiles = cannon.shootMissile(gravity, factory);
        missiles.addAll(newMissiles);
        notifyObservers();
    }

    public void increaseCannonForce() {
        this.cannon.increaseForce();
        gameInfo.setCannonForce(cannon.getForce());
    }

    public void decreaseCannonForce() {
        this.cannon.decreaseForce();
        gameInfo.setCannonForce(cannon.getForce());
    }

    public void increaseCannonAngle() {
        cannon.increaseAngle();
        gameInfo.setCannonAngle(cannon.getAngle());
    }

    public void decreaseCannonAngle() {
        cannon.decreaseAngle();
        gameInfo.setCannonAngle(cannon.getAngle());
    }

    public void increaseGravity() {
        gravity += Config.MODEL_GRAVITY_STEP;
        if (gravity > Config.MODEL_GRAVITY_MAX) {
            gravity = Config.MODEL_GRAVITY_MAX;
        }
        gameInfo.setGravity(gravity);
    }

    public void decreaseGravity() {
        gravity -= Config.MODEL_GRAVITY_STEP;
        if (gravity < Config.MODEL_GRAVITY_MIN) {
            gravity = Config.MODEL_GRAVITY_MIN;
        }
        gameInfo.setGravity(gravity);
    }

    public int getGravity() { return gravity; }

    private void startGameTimers() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveObjects();

                checkCollisions();

                removeDestroyedObjects();

                notifyObservers();
                TimeKeeper.setTime(TimeKeeper.getTime() + Config.MODEL_TIME_TICK_IN_MILLISECONDS);
            }
        }, 0, Config.MODEL_TIME_TICK_IN_MILLISECONDS);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                spawnEnemy();
            }
        }, Config.MODEL_ENEMY_SPAWN_INTERVAL_IN_MILLISECONDS, Config.MODEL_ENEMY_SPAWN_INTERVAL_IN_MILLISECONDS);
    }

    public void changeFireMode() {
        cannon.changeFireMode();
        gameInfo.setFireMode(cannon.getFireMode().getName());
    }

    public Cannon getCannon() {
        return cannon;
    }

    public List<Missile> getMissiles() { return missiles; }

    public List<Enemy> getEnemies() { return enemies; }

    public List<GameObject> getAllGameObjects() {
        List<GameObject> allGameObjects = new ArrayList<GameObject>();

        // add all enemies
        for (Enemy enemy : enemies) {
            allGameObjects.add(enemy);
        }

        // add all missiles
        for (Missile missile : missiles) {
            allGameObjects.add(missile);
        }

        for (Collision collision : collisions) {
            allGameObjects.add(collision);
        }

        // add cannon
        allGameObjects.add(cannon);

        // add game info
        allGameObjects.add(gameInfo);

        return allGameObjects;
    }

    /* MEMENTO FUNCTIONALITIES */
    public Memento saveToMemento() {
        return new Memento(cannon, gameInfo, enemies, missiles, collisions, gravity, factory);
    }

    public void restoreFromMemento(Memento memento) {
        this.cannon = memento.getCannon();
        this.gameInfo = memento.getGameInfo();
        this.gravity = memento.getGravity();

        this.enemies = new ArrayList<Enemy>();
        for (Enemy enemy : memento.getEnemies()) {
            this.enemies.add(factory.makeEnemy(enemy));
        }

        this.missiles = new ArrayList<Missile>();
        for (Missile missile : memento.getMissiles()) {
            this.missiles.add(new Missile(missile));
        }

        this.collisions = new ArrayList<Collision>();
        for (Collision collision : memento.getCollisions()) {
            this.collisions.add(new Collision(collision));
        }

        TimeKeeper.setTime(memento.getTime());

        notifyObservers();
    }

    public static class Memento {
        private Cannon cannon;
        private GameInfo gameInfo;
        private List<Enemy> enemies;
        private List<Missile> missiles;
        private List<Collision> collisions;
        private int gravity;
        private long time;

        public Memento(
                Cannon cannon,
                GameInfo gameInfo,
                List<Enemy> enemies,
                List<Missile> missiles,
                List<Collision> collisions,
                int gravity,
                Factory factory
        ) {
            this.cannon = new Cannon(cannon);
            this.gameInfo = new GameInfo(gameInfo);
            this.gravity = gravity;
            this.time = TimeKeeper.getTime();

            this.enemies = new ArrayList<Enemy>();
            for (Enemy enemy : enemies) {
                this.enemies.add(factory.makeEnemy(enemy));
            }

            this.missiles = new ArrayList<Missile>();
            for (Missile missile : missiles) {
                this.missiles.add(new Missile(missile));
            }

            this.collisions = new ArrayList<Collision>();
            for (Collision collision : collisions) {
                this.collisions.add(new Collision(collision));
            }
        }

        public Cannon getCannon() { return cannon; }
        public GameInfo getGameInfo() { return gameInfo; }
        public int getGravity() { return gravity; }
        public List<Enemy> getEnemies() { return enemies; }
        public List<Missile> getMissiles() { return missiles; }
        public List<Collision> getCollisions() { return collisions; }
        public long getTime() { return time; }
    }
}
