package us.javarush.tkachenko.cryptoanalyser.view;

import us.javarush.tkachenko.cryptoanalyser.chipers.CaesarCoder;
import us.javarush.tkachenko.cryptoanalyser.exceptions.FileProcessingException;
import us.javarush.tkachenko.cryptoanalyser.exceptions.InvalidUserInputException;

import java.util.Scanner;

public class ConsoleDialogue implements Dialogue{

    private static final String WELCOME_MESSAGE =
            """
                *************
                ** Welcome **
                *************
            """;
    private static final String OPERATION_PATTERN = "%d - %s";
    private static final String TRY_AGAIN_COMMAND = "again";
    private final Scanner in;
    private final CaesarCoder caesarCoder;

    public ConsoleDialogue(){
        in = new Scanner(System.in);
        caesarCoder = new CaesarCoder();
    }

    @Override
    public void start(){
        showMenu();
        Operation operation = readOperation();
        processOperation(operation);
    }

    private void showMenu(){
        System.out.println(WELCOME_MESSAGE);
        System.out.println("Choose next available option to continue: ");
        for(Operation operation : Operation.values()){
            String message = String.format(OPERATION_PATTERN, operation.getNumber(), operation.getDescription());
            System.out.println(message);
        }
    }

    private Operation readOperation(){
        boolean shouldTryAgain = false;

        do{
            try {
                int option = readInt();
                return Operation.getByNumber(option);
            } catch (IllegalArgumentException exception){
                System.out.println("Operation number is incorrect.");
                System.out.println("Reason: " + exception.getMessage());
                System.out.println("Please try 'again' or do exit!");

                String input = readString();
                if (TRY_AGAIN_COMMAND.equalsIgnoreCase(input)){
                    shouldTryAgain = true;
                }
            }
        } while (shouldTryAgain);
        return Operation.EXIT;
    }

    private void processOperation(Operation operation){
        switch (operation){
            case EXIT -> processExit();
            case ENCRYPTION -> processEncryptionOperation();
            case DECRYPTION -> processDecryptionOperation();
            case BRUTEFORCE -> processBruteforceOperation();
        }
    }

    private void processEncryptionOperation(){
        System.out.println("Please enter filename, with original text: ");
        String inputFileName = readString();

        System.out.println("Please enter the result file to save encryption results: ");
        String outputFileName = readString();

        System.out.println("Enter the key: ");
        int key = readInt();

        try {
            caesarCoder.encrypt(inputFileName, outputFileName, key);
            System.out.println("Done!");
        } catch (FileProcessingException | InvalidUserInputException exception){
            System.err.println("Error take place. Due to the reason: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    private void processDecryptionOperation(){
        System.out.println("Please enter filename, with original text: ");
        String inputFileName = readString();

        System.out.println("Please enter the filename to save encryption results: ");
        String outputFileName = readString();

        System.out.println("Enter the key: ");
        int key = readInt();

        try {
            caesarCoder.decrypt(inputFileName, outputFileName, key);
            System.out.println("Done!");
        } catch (FileProcessingException exception){
            System.err.println("Error take place. Due to the reason: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    private void processBruteforceOperation(){
        System.out.println("Please enter filename, with original text: ");
        String inputFileName = readString();

        System.out.println("Please enter the filename to save encryption results: ");
        String outputFileName = readString();

        System.out.println("Enter the key: ");
        int key = readInt();

//        try{
//            caesarCoder.bruteforce(inputFileName, outputFileName);
//        }
    }

    private void processExit(){
        System.out.println("Bye!");
    }

    private int readInt() throws InvalidUserInputException {
        String input = in.nextLine();
        try{
            return Integer.parseInt(input);
        } catch (NumberFormatException exception){
            throw new InvalidUserInputException("Provided integer value is out of the range.", exception);
        }
    }

    private String readString(){
        return in.nextLine();
    }



}
