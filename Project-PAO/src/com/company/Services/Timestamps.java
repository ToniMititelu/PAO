package com.company.Services;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class Timestamps {
    private static Timestamps instance = null;
    private String timestampCSV;

    private Timestamps() {
        timestampCSV  = "src/com/company/Services/csvs/Timestamps.csv";
    }

    public static Timestamps getInstance() {
        if(instance == null) {
            instance = new Timestamps();
        }
        return instance;
    }

    public void writeTimestampsCsv(String action) {
        try (FileWriter writer = new FileWriter(timestampCSV, true)) {
            action = "\n" + action + "," + new Timestamp((new Date()).getTime()).toString();
            writer.write(action);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
