package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.controller.UserInputController;
import cz.fit.dpo.mvcshooter.model.Config;
import cz.fit.dpo.mvcshooter.model.Model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ondrej Stuchlik
 */
public class MainWindow extends JFrame {

    public MainWindow(Model model, final UserInputController userInputController) {
        try {
            userInputController.setWindowHandle(this);

            Canvas view = new Canvas(0, 0, Config.WINDOW_X_SIZE, Config.WINDOW_Y_SIZE, model);

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Shooting game");
            this.setResizable(false);

            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(
                  (int) (screen.getWidth() / 2 - 250),
                  (int) (screen.getHeight() / 2 - 250));

            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    // delegate to controller
                    userInputController.handleInput(evt);
                }
            });

            this.add(view);
            this.pack();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
    
    public void showHelp() {
        JOptionPane.showMessageDialog(this, 
                "Controls: \n"
                + "Move up - Arrow UP\n"
                + "Move down - Arrow DOWN\n"
                + "Shoot - SPACE\n"
                + "Increase force - W\n"
                + "Decrease force - S\n"
                + "Increase angle - E\n"
                + "Decrease angle - D\n"
                + "Increase gravity - Q\n"
                + "Decrease gravity - A\n"
                + "Change fire mode - F\n"
                + "Save game - K\n"
                + "Load game - L\n"
                + "Exit game - ESC\n"
        );
    }
}
