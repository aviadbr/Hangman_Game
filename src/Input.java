import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
Author: Aviad Brown
class to read the words from a file.
The filename is a parameter of the constructor.
The words separated by white blocks. Don't use ',' or other signs to separate them.
Just words with latin letters are allowed.
 */
public class Input {

    private ArrayList<Word> wordsList;

    public Input(String fileName) throws IOException {
        wordsList = new ArrayList<>();
        Scanner s = new Scanner(new File(fileName));
        while (s.hasNext()) { //read the file. If there is a problem, throws exception
            String word = s.next();
            if(word.matches("[a-zA-Z]+")) { //add word just if it is valid - just latin letters.
                wordsList.add(new Word(word));
            }
        }
        s.close();
    }

    public ArrayList<Word> getWordsList() {
        return wordsList;
    }
}
