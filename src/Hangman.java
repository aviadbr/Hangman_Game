import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/*
Author: Aviad Brown
class to handle the main logic of the game.
 */
public class Hangman {
    private ArrayList<Character> used;
    private ArrayList<Word> wordsList;
    private Input input;
    private Word word;
    int lettersLeft;

    public Hangman(String fileName) throws IOException {
        input = new Input(fileName);  //read the words from the file
        wordsList = input.getWordsList();
        if(wordsList.size() == 0) throw new IOException("The file is empty.");
        used = new ArrayList<>();
        word = chooseNewWord();
        lettersLeft = word.getLength();
    }

    public Word getWord() {
        return this.word;
    }


    /*
    Returns a string representation of the letters that have been already used.
     */
    public String getUsedString() {
        String s = "Used letters: ";
        for(char c : used) {
            s += c + " ";
        }
        return s;
    }

    /*
    Handle new letter was selected.
    Returns -1 if the letter is not exists.
    Else, returns lettersLeft.
     */
    public int newLetterSelected(char c) {
        if(used.contains(c)) // already used
            return -1;

        used.add(c);  //insert the letter to used list.
        if(!word.checkIfContainsChar(c)) { //the word doesn't contain this letter
            return -1;
        }
        lettersLeft -= word.numOfLetter(c);
        return lettersLeft;
    }


    /*
    choose another word for reset or winning.
     */
    public Word chooseNewWord() {
        Random rand = new Random();
        return wordsList.get(rand.nextInt(wordsList.size()));
    }

    /*
    reset the game status
     */
    public void resetGame() {
        used.clear();
        word = chooseNewWord();
        lettersLeft = word.getLength();
    }
}
