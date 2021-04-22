import javax.swing.*;
import java.awt.*;

/*
Author: Aviad Brown
 panel for the pillar and the hangman graphics.
 */
public class TreePanel extends JPanel {
    private static final int SIZE_OF_TREE = 6;
    private int treeLevel = 0;

    public TreePanel() {
        super();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(treeLevel <= SIZE_OF_TREE)
            drawImage(g,new ImageIcon(treeLevel + ".png").getImage());
    }


    /*
        draw the image of the tree, according to the tree level.
     */
    public void drawImage(Graphics g, Image img) {
        int h = getHeight();
        int w = getWidth();
        g.drawImage(img,w/4-30, 40,w/2,h/2 + 80 ,this);
    }

    /*
    reset the level of tree to 0
     */
    public void resetLevel() {
        treeLevel = 0;
        repaint();
    }

    /*
    increase the tree level.
     */
    public int increaseTreeLevel() {
        treeLevel++;
        repaint();
        return treeLevel;
    }

}
