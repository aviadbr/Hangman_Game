import javax.swing.*;
import java.io.IOException;
import java.security.InvalidParameterException;

/*
Author: Aviad Brown
the main class of the game
 */
public class Main {
    public static void main(String[] args) {
        try {
            Hangman game = new Hangman(args[0]);
            GameFrame main = new GameFrame(game);
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (IOException e) { //catch error with the file
            System.out.println("There is a problem with the file.");
            e.printStackTrace();
            System.exit(1);
        } catch (InvalidParameterException e) { //catch another error
            System.out.println("There was unexpected problem with some parameters. Program will shut down.");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
