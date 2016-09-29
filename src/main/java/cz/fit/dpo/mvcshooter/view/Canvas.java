package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.ModelObserver;
import cz.fit.dpo.mvcshooter.model.entity.GameObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Ondrej Stuchlik
 */
public class Canvas extends JPanel implements ModelObserver {
    private GraphicsDrawer drawer = new GraphicsDrawer();
    private Model model;

    public Canvas(int x, int y, int width, int height, Model model) {
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.setLocation(x, y);
        this.setPreferredSize(new Dimension(width,height));
        this.setVisible(true);
        this.model = model;

        model.registerObserver(this);
    }

    @Override
    public void updateCanvas() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawer.setGraphics(g);

        List<GameObject> allGameObjects = model.getAllGameObjects();
        for (GameObject gameObject : allGameObjects) {
            gameObject.accept(drawer);
        }
    }
    
}
