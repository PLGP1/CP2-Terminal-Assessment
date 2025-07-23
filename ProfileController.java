package controller;

import model.Profile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ProfileController {
    private static final String FILE_PATH = "src/resources/profile.csv";

    public static Profile getProfileById(String empID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // skip header
                }

                String[] data = line.split(",");
                if (data.length >= 4 && data[0].equals(empID)) {
                    return new Profile(data[0], data[1], data[2], data[3]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
