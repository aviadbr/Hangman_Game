import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;

/*
Author: Aviad Brown
Class of the main frame of the game.
Manage all the game's graphics.
 */
public class GameFrame extends JFrame {
    private static final int NUM_OF_LETTERS = 26;
    private static final int FINAL_STAGE = 6;
    private JButton[] btnsLetters;
    private Hangman game;
    private TreePanel tree;
    private WordPanel wordPanel;
    private JLabel lblUsed;

    public GameFrame(Hangman g) throws InvalidParameterException {
        super("GridLayout");
        if(g == null) throw new InvalidParameterException("Parameter is null.");
        game = g;
        JPanel pnl1 = new JPanel();
        JPanel pnl2 = new JPanel();
        JPanel usedPanel = new JPanel();
        JPanel btnPanel = new JPanel();
        wordPanel = new WordPanel(g.getWord());
        tree = new TreePanel();

        btnsLetters = new JButton[NUM_OF_LETTERS];
        JButton reset = new JButton("Reset");
        lblUsed = new JLabel(game.getUsedString());
        lblUsed.setFont(new Font("Sherif",Font.BOLD,16)); //change the font

        //define the buttons action.
        for (int i = 0; i < NUM_OF_LETTERS; i++) {
            String s = "" + (char) ((int) 'A' + i);
            btnsLetters[i] = new JButton(s);
            btnPanel.add(btnsLetters[i]);
            btnsLetters[i].addActionListener(new ActionListener() {  //buttons listener for letters
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton t = (JButton) e.getSource();
                    t.setEnabled(false); //disable this letter
                    char c = t.getText().charAt(0);
                    int left = game.newLetterSelected(c);
                    lblUsed.setText(game.getUsedString());
                    if (left == 0) { //win the game.
                        wordPanel.updateLabels(c);
                        changeStatusOfButtons(false);
                        win();
                    } else if (left > 0) { // letter exist, update the label
                        wordPanel.updateLabels(c);
                    } else { // doesn't exist, update the tree
                        int level = tree.increaseTreeLevel(); //wrong letter - get a penalty
                        tree.repaint();
                        if (level == FINAL_STAGE) { //loss the game
                            changeStatusOfButtons(false);
                            loss();

                        }
                    }

                }
            });
        }
        //reset button listener
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAll();
            }
        });
        usedPanel.setLayout(new GridLayout(2, 1));
        usedPanel.add(lblUsed);
        pnl2.setLayout(new GridLayout(2, 1));
        pnl1.setLayout(new BorderLayout());
        btnPanel.setLayout(new GridLayout(3, 9));
        pnl2.add(wordPanel);
        pnl2.add(usedPanel);
        pnl1.add(pnl2,BorderLayout.NORTH);
        pnl1.add(btnPanel,BorderLayout.CENTER);
        pnl1.add(reset,BorderLayout.SOUTH);
        setLayout(new GridLayout(2, 1));
        add(tree);
        add(pnl1);

        this.setResizable(false);
        this.setSize(450, 600);
        this.setVisible(true);
    }



    /*
    Method to handle a user win.
    */
    public void win() {
        JOptionPane.showMessageDialog(this,"Congratulation! You won the game. Press reset to start a new one.", "You are the champion!",JOptionPane.INFORMATION_MESSAGE);
    }

    /*
    to handle a user loss.
    */
    public void loss() {
        JOptionPane.showMessageDialog(this,"We are sorry but you lost. Try again.", "Loser!",JOptionPane.INFORMATION_MESSAGE);
        resetAll();
    }

    /*
    method to change all the letters buttons to enabled or disabled.
     */
    public void changeStatusOfButtons(boolean status) {
        for (int i = 0; i < NUM_OF_LETTERS; i++)
            btnsLetters[i].setEnabled(status);
    }

    private void resetAll() {
        changeStatusOfButtons(true);
        tree.resetLevel();
        game.resetGame();
        wordPanel.resetPanel(game.getWord());
        lblUsed.setText(game.getUsedString());
    }
}
