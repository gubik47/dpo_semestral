package cz.fit.dpo.mvcshooter;

import cz.fit.dpo.mvcshooter.controller.UserInputController;
import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.view.MainWindow;
import javax.swing.SwingUtilities;

/**
 *
 * @author stue
 */
public class Shooter {
    
    public static void main(String[] args) {
        final Model model = new Model();
        final UserInputController controller = new UserInputController(model);

        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
               new MainWindow(model, controller).setVisible(true);
            }
        });
    }
}
