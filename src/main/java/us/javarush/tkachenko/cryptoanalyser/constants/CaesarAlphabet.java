package us.javarush.tkachenko.cryptoanalyser.constants;

import us.javarush.tkachenko.cryptoanalyser.exceptions.CesarAlphabetException;

import java.util.*;

public class CaesarAlphabet {
  public static final String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
    public static final String RUSSIAN_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    public static final String CHARACTERS_ALPHABET = Arrays.toString(getSpecialCharacters());
    public static final String BI_LINGUAL_ALPHABET = RUSSIAN_ALPHABET + ENGLISH_ALPHABET + CHARACTERS_ALPHABET;
    private static final Character[] KNOWN_ALPHABET = getKnownAlphabet();
    private final List<Character> alphabet;
    private final Map<Character, Integer> charIndexes = new HashMap<>();

    public CaesarAlphabet() {
        List<Character> temporaryAlphabet = new ArrayList<>();
        temporaryAlphabet.addAll(Arrays.asList(KNOWN_ALPHABET));
        alphabet = temporaryAlphabet;

        for (int i = 0; i < alphabet.size(); i++) {
            charIndexes.put(alphabet.get(i), i);
        }
    }


    public Character getCharByIndex(int index) {
        if (index < 0 || index > alphabet.size()) {
            try {
                throw new CesarAlphabetException("Invalid index. Index: " + index + ". Valid values are from 0 to " + alphabet.size());
            } catch (CesarAlphabetException e) {
                throw new RuntimeException(e);
            }
        }
        return alphabet.get(index);
    }

    public int getCharIndex(Character character) {
        if (!charIndexes.containsKey(character)) {
            System.out.println("Invalid character. Char: " + character + ".");
            try {
                throw new CesarAlphabetException("Invalid character. Char: " + character + ".");
            } catch (CesarAlphabetException e) {
                throw new RuntimeException(e);
            }
        }
        return charIndexes.get(character);
    }

    public int getSize() {
        return alphabet.size();
    }

    private static Character[] getKnownAlphabet() {

        int arraySize = BI_LINGUAL_ALPHABET.length();
        Character[] alphabet = new Character[arraySize];

        for (int i = 0; i < arraySize; i++) {
            alphabet[i] = BI_LINGUAL_ALPHABET.charAt(i);
        }

        return alphabet;
    }

    private static Character[] getSpecialCharacters() {
        Character[] specialCharacterAlphabet = new Character[3];

        specialCharacterAlphabet[0] = (char) 32;   // ' ' (space)
        specialCharacterAlphabet[1] =  '—';
        specialCharacterAlphabet[2] = (char) 13;   // enter or line feed

        return specialCharacterAlphabet;
    }


}
