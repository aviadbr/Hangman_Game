import java.security.InvalidParameterException;
import java.util.HashMap;
/*
Author: Aviad Brown
Class to represent a word.
 */
public class Word {
    private String word;
    private HashMap<Character, Integer> lettersMap;

    public Word(String w) throws InvalidParameterException {
        if(w == null || w.equals(""))
            throw new InvalidParameterException("Problem with the word.");
        lettersMap = new HashMap<Character, Integer>();
        this.word = w.toUpperCase();

        char c;
        for (int i = 0; i <word.length(); i++) {
            c = word.charAt(i);

            lettersMap.computeIfPresent(c,(key, val) -> val + 1);
            lettersMap.computeIfAbsent(c, k -> 1);

        }
    }



    public int getLength() {
        return word.length();
    }

    public Character getCharAtIndex(int i) {
        if(i<word.length() && i>=0) {
            return word.charAt(i);
        }
        return null;
    }

    public boolean checkIfContainsChar(char c) {
        return lettersMap.containsKey(c);
    }

    //return the number of letters equal to c in the word
    public int numOfLetter( char c) {
        return lettersMap.get(c);
    }
}
