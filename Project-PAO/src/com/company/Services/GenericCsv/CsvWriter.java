package com.company.Services.GenericCsv;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter<T extends CsvSerializable> {
    private FileWriter writer;

    /**
     * Constructs a new CSV file writer.
     *
     * @param writer destination of CSV file
     * @param object if not null, will be used to extract the column headers
     * @throws IOException if there was any error with writing the object to file
     */
    public CsvWriter(FileWriter writer, T object, boolean append) throws IOException {
        this.writer = writer;

        if (object != null) {
            String[] columns = object.getColumnNames();
            String header = String.join(",", columns) + '\n';
            if(!append)
                writer.write(header);
            writer.flush();
        }
    }

    public void writeObject(T object) throws IOException {
        String[] strings = object.toStringArray();
        String line = String.join(",", strings) + '\n';
        writer.write(line);
    }
}