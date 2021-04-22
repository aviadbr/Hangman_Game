import javax.swing.*;
import java.awt.*;

/*
Author: Aviad Brown
Class that extends JPanel, show the letters that the user found.
 */
public class WordPanel extends JPanel {
    private int wordLength;
    private char[] letters;
    private JLabel[] lbls;
    private Word word;

    public WordPanel(Word w) {
        super();
        resetPanel(w);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=0;i<wordLength;i++) {
            lbls[i].setText("" + letters[i]);
        }
    }


    /*
    Gets a char, and reveal all the equals letters in the word.
     */
    public void updateLabels(char c) {
        for (int i = 0; i < wordLength; i++) {
            if(word.getCharAtIndex(i) == c) {
                if(i == 0) //if first letter, use upper case.
                    letters[i] = c;
                else //if not, use lower case.
                    letters[i] = Character.toLowerCase(c);
            }
        }
        repaint();
    }

    /*
    reset the word panel with new word
     */
    public void resetPanel(Word w) {
        this.removeAll();
        this.revalidate();
        word = w;
        wordLength = word.getLength();
        letters = new char[wordLength];
        lbls = new JLabel[wordLength];
        for(int i=0;i<wordLength;i++) {
            letters[i] = '_'; //initialize with _ all the labels
            lbls[i] = new JLabel("" + letters[i]);
            lbls[i].setFont(new Font("Sherif",Font.BOLD,32)); //change the font
//
            this.add(lbls[i]);
        }
        repaint();
    }
}
