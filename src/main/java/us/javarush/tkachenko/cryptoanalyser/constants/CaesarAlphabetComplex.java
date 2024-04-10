package us.javarush.tkachenko.cryptoanalyser.constants;

import us.javarush.tkachenko.cryptoanalyser.exceptions.CesarAlphabetException;

import java.util.*;

public class CaesarAlphabetComplex {


    private static final Character[] SPECIAL_CHARS_UNICODE_ALPHABET = getUNICODESpecialCharsAlphabet();
    private static final Character[] NUMBERS_UNICODE_ALPHABET = getUNICODENumbersAlphabet();
    private static final Character[] LATIN_UNICODE_ALPHABET = getUNICODELatinAlphabet();
    private static final Character[] CYRILLIC_UNICODE_ALPHABET = getUNICODECyrillicAlphabet();
    private final List<Character> alphabet;
    private final Map<Character, Integer> charIndexes;


    public CaesarAlphabetComplex(){
        List<Character> temporaryAlphabet = new ArrayList<>();

        temporaryAlphabet.addAll(Arrays.asList(SPECIAL_CHARS_UNICODE_ALPHABET));
        temporaryAlphabet.addAll(Arrays.asList(NUMBERS_UNICODE_ALPHABET));
        temporaryAlphabet.addAll(Arrays.asList(CYRILLIC_UNICODE_ALPHABET));
        temporaryAlphabet.addAll(Arrays.asList(LATIN_UNICODE_ALPHABET));

        alphabet = temporaryAlphabet;

        charIndexes = new HashMap<>();
        for (int i = 0; i < alphabet.size(); i++) {
            charIndexes.put(alphabet.get(i), i);
        }
    }

    public Character getCharByIndex(int index) {
        if (index < 0 || index > alphabet.size()){
            try {
                throw new CesarAlphabetException("Invalid index. Index: " + index + ". Valid values are from 0 to " + alphabet.size());
            } catch (CesarAlphabetException e) {
                throw new RuntimeException(e);
            }
        }
        return alphabet.get(index);
    }

    public int getCharIndex(Character character){
        if (!charIndexes.containsKey(character)){
            System.out.println("Invalid character. Char: " + character + ".");
            try {
                throw new CesarAlphabetException("Invalid character. Char: " + character + ".");
            } catch (CesarAlphabetException e) {
                throw new RuntimeException(e);
            }
        }
        return charIndexes.get(character);
    }

    public int getSize(){
        return alphabet.size();
    }

    private static Character[] getUNICODELatinAlphabet(){
        Character[] latinAlphabet = new Character[52];

        for (int i = 65; i <= 90; i++) { // Unicode for uppercase A-Z
            latinAlphabet[i - 65] = (char) i;
        }
        for (int i = 97; i <= 122; i++) { // Unicode for lowercase a-z
            latinAlphabet[i - 97 + 26] = (char) i;
        }

        // return 52 latin alphabet letters including upper and lowercase representation
        return latinAlphabet;
    }

    private static Character[] getUNICODECyrillicAlphabet(){
        Character[] cyrillicAlphabet = new Character[66];

        // The Unicode values for the Russian alphabet start at 1040 for 'A' and end at 1071 for 'Я' for uppercase letters
        // and start at 1072 for 'а' and end at 1103 for 'я' for lowercase letters
        for (int i = 1040; i <= 1071; i++) {
            cyrillicAlphabet[i - 1040] = (char) i;
        }
        for (int i = 1072; i <= 1103; i++) {
            cyrillicAlphabet[i - 1072 + 32] = (char) i;
        }

        // return 66 latin alphabet letters including upper and lowercase representation
        return cyrillicAlphabet;
    }

    private static Character[] getUNICODENumbersAlphabet(){
        Character[] numbersChars = new Character[16];

        // ASCII codes for numbers '0' - '9' range from 48 to 57
        for (int i = 48; i <= 57; i++) {
            numbersChars[i - 48] = (char) i;
        }

        return numbersChars;
    }

    private static Character[] getUNICODESpecialCharsAlphabet(){
        Character[] specialChars = new Character[10];

        // ASCII codes for commonly used symbols
        specialChars[0] = (char) 46;   // '.' (dot)
        specialChars[1] = (char) 44;   // ',' (comma)
        specialChars[2] = (char) 63;   // '?' (question mark)
        specialChars[3] = (char) 33;   // '!' (exclamation mark)
        specialChars[4] = (char) 45;   // '-' (hyphen)
        specialChars[5] = (char) 32;   // ' ' (space)
        specialChars[6] =  '—';
        specialChars[7] = (char) 62;   // '>' (greater then)
        specialChars[8] = (char) 60;   // '<' (less then)
        specialChars[9] = (char) 34;   //" (double quote)

        return specialChars;
    }
}
