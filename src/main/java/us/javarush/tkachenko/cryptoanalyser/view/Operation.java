package us.javarush.tkachenko.cryptoanalyser.view;

public enum Operation {
    EXIT(0, "exit", () -> System.out.println("Operation 1")),
    ENCRYPTION(1, "encrypt file", () -> System.out.println("Operation 2")),
    DECRYPTION(2, "decrypt file", () -> System.out.println("Operation 3")),
    BRUTEFORCE(3, "try to crack file with bruteforce", () -> System.out.println("Operation 4"));

    private final int number;
    private final String description;
    private final Runnable runnable;

    Operation(int number, String description, Runnable runnable) {
        this.number = number;
        this.description = description;
        this.runnable = runnable;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public static Operation getByNumber(int number) {
        for (Operation operation : values()) {
            if (operation.getNumber() == number) {
                return operation;
            }
        }

        throw new IllegalArgumentException("Provided incorrect number for the operation!");
    }
}
