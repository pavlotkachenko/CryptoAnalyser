package us.javarush.tkachenko.cryptoanalyser.entrypoint;

import us.javarush.tkachenko.cryptoanalyser.view.ConsoleDialogue;
import us.javarush.tkachenko.cryptoanalyser.view.Dialogue;


public class Application extends Object{
    public static void main(String[] args) {
        Dialogue dialogue = new ConsoleDialogue();
        dialogue.start();
    }
}
