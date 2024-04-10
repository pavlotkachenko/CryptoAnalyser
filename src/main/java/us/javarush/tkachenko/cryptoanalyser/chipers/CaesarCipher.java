package us.javarush.tkachenko.cryptoanalyser.chipers;

import us.javarush.tkachenko.cryptoanalyser.constants.CaesarAlphabet;

public class CaesarCipher {
    private static CaesarAlphabet alphabet;

    public CaesarCipher(CaesarAlphabet alphabet){
        this.alphabet = alphabet;
    }

    public String encrypt(String originalText, int key){
        return process(originalText, key);
    }

    public static String decrypt(String originalText, int key){
        return process(originalText, -key);
    }

    public static void bruteforce(String cipherText){
        for(int shiftKey = 0 ; shiftKey < alphabet.getSize(); shiftKey++){
            String decrypted = decrypt(cipherText, shiftKey);
            System.out.println("Key="+shiftKey+", Decryption="+decrypted);
        }
    }

    private static String process(String originalText, int key){
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < originalText.length(); i++) {
            Character originalChar = originalText.charAt(i);
            int originalCharIndex = alphabet.getCharIndex(originalChar);
            int newCharIndex = (alphabet.getSize() + (originalCharIndex + key)) % alphabet.getSize();

            builder.append(alphabet.getCharByIndex(newCharIndex));
        }
        return builder.toString();
    }
}
