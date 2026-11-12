package util;

import exception.EmptyInputException;
import exception.InvalidNumberException;

import java.util.Scanner;

public class CLIUtil {
    private static final Scanner SCANNER = new Scanner(System.in);

    private CLIUtil() {
    }

    public static String readString(String prompt) throws EmptyInputException {
        System.out.print(prompt);
        String input = SCANNER.nextLine().trim();
        if (input.isEmpty()) {
            throw new EmptyInputException("Input tidak boleh kosong.");
        }
        return input;
    }

    public static int readInt(String prompt) throws EmptyInputException, InvalidNumberException {
        String input = readString(prompt);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException("Input harus berupa bilangan bulat.");
        }
    }

    public static double readDouble(String prompt) throws EmptyInputException, InvalidNumberException {
        String input = readString(prompt);
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException("Input harus berupa angka.");
        }
    }
}
