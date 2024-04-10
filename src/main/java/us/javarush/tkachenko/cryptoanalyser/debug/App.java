package us.javarush.tkachenko.cryptoanalyser.debug;

import static us.javarush.tkachenko.cryptoanalyser.debug.Cesar.bruteForceDecryption;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {



        String plainText = "тест, проверка!";
        int shift = 3;

        String cipher = Cesar.encrypt(plainText, shift);
        System.out.println("Encrypted: " + cipher); // Output: "цgtгd/ sypрйшфгоc"

        String deciphered = Cesar.decrypt(cipher, shift);
        System.out.println("Decrypted: " + deciphered); // Output: "тест, проверка!"

        cipher = "хзфх”втусезунг!";
        System.out.println("Brute Force Decryption: ");
        bruteForceDecryption(cipher);
        System.out.println("Please review all possible options and select the right one!");
    }
}
