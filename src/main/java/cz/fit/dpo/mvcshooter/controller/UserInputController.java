package cz.fit.dpo.mvcshooter.controller;

import cz.fit.dpo.mvcshooter.memento.GameSave;
import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.view.MainWindow;

import java.awt.event.KeyEvent;

/**
 * Created by Jakub on 22.10.2015.
 */
public class UserInputController {
    private MainWindow window;
    private Model model;
    private GameSave caretaker;

    public UserInputController(Model model) {
        this.model = model;
        this.caretaker = new GameSave();
    }

    public void handleInput(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 27: {
                // exit game
                System.exit(0);
                break;
            }
            case KeyEvent.VK_UP: {
                this.model.moveCannonUp();
                break;
            }
            case KeyEvent.VK_DOWN: {
                this.model.moveCannonDown();
                break;
            }
            case KeyEvent.VK_SPACE: {
                this.model.shootCannon();
                break;
            }
            case KeyEvent.VK_W: {
                this.model.increaseCannonForce();
                break;
            }
            case KeyEvent.VK_S: {
                this.model.decreaseCannonForce();
                break;
            }
            case KeyEvent.VK_E: {
                this.model.decreaseCannonAngle();;
                break;
            }
            case KeyEvent.VK_D: {
                this.model.increaseCannonAngle();
                break;
            }
            case KeyEvent.VK_Q: {
                this.model.increaseGravity();;
                break;
            }
            case KeyEvent.VK_A: {
                this.model.decreaseGravity();
                break;
            }
            case KeyEvent.VK_H: {
                // show game controls
                window.showHelp();
                break;
            }
            case KeyEvent.VK_F: {
                // change fire mode
                this.model.changeFireMode();
                break;
            }
            case KeyEvent.VK_K: {
                // save game
                System.out.println("Save game");
                caretaker.saveGame(model.saveToMemento());
                break;
            }
            case KeyEvent.VK_L: {
                // load game
                System.out.println("Load game");
                model.restoreFromMemento(caretaker.loadGame());
                break;
            }
            default: {
                System.out.println("Unknown key pressed: " + e.getKeyCode());
            }
        }
    }

    public void setWindowHandle(MainWindow window) {
        this.window = window;
    }
}
