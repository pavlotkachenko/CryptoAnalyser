package us.javarush.tkachenko.cryptoanalyser.chipers;

import us.javarush.tkachenko.cryptoanalyser.constants.CaesarAlphabet;
import us.javarush.tkachenko.cryptoanalyser.file.FileNameValidator;
import us.javarush.tkachenko.cryptoanalyser.file.FileProcessor;

import java.util.List;

public class CaesarCoder {
    private final CaesarCipher caesarCipher;
    private final FileNameValidator validator;
    private final FileProcessor fileProcessor;

    public CaesarCoder(){
        this.caesarCipher = new CaesarCipher(new CaesarAlphabet());
        this.validator = new FileNameValidator();
        this.fileProcessor = new FileProcessor();
    }

    public void encrypt(String inputFileName, String outputFileName, int key){
        validator.validateForReading(inputFileName);
        validator.validateForWriting(outputFileName);

        List<String> sourceLines = fileProcessor.readFile(inputFileName);
        for(String sourceLine : sourceLines){
            String encryptedLine = caesarCipher.encrypt(sourceLine, key);
            fileProcessor.appendToFile(outputFileName, encryptedLine);
        }
    }

    public void decrypt(String inputFileName, String outputFileName, int key){
        validator.validateForReading(inputFileName);
        validator.validateForWriting(outputFileName);

        List<String> sourceLines = fileProcessor.readFile(inputFileName);
        for (String sourceLine : sourceLines){
            String decryptLine = caesarCipher.decrypt(sourceLine, key);
            fileProcessor.appendToFile(outputFileName, decryptLine);
        }
    }

//    public void bruteforce(String inputFileName, String outputFileName, int key){
//        validator.validateForReading(inputFileName);
//        validator.validateForWriting(outputFileName);
//
//        List<String> sourceLines = fileProcessor.readFile(inputFileName);
//        for (String sourceLine : sourceLines){
//            String decryptLine = caesarCipher.(sourceLine);
//            fileProcessor.appendToFile(outputFileName, decryptLine);
//        }
//
//    }
}
