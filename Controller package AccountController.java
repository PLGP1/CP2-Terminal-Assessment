package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AccountController {
    private static final String ACCOUNT_FILE = "src/resources/account.csv";

    /**
     * Validates if the given username and password exist in the account.csv file.
     * Skips the CSV header.
     *
     * @param username The username input
     * @param password The password input
     * @return true if credentials match an account, false otherwise
     */
    public static boolean validateLogin(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;  // skip header
                    continue;
                }
                String[] parts = line.split(",", -1);
                if (parts.length >= 2) {
                    String fileUsername = parts[0].trim();
                    String filePassword = parts[1].trim();
                    if (username.equals(fileUsername) && password.equals(filePassword)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // You may want to log or handle the error properly
        }
        return false;
    }
}
