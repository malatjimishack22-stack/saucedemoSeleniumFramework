package com.saucedemo.data.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public final class CsvDataReader {

    private CsvDataReader() {}

    public static Object[][] read(String filePath) {

        List<Object[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            // ✅ Read header first
            String headerLine = br.readLine();
            if (headerLine == null) {
                throw new RuntimeException("CSV file is empty: " + filePath);
            }

            String[] headers = headerLine.split(",", -1);

            // Trim header names
            for (int i = 0; i < headers.length; i++) {
                headers[i] = headers[i].trim();
            }

            // ✅ Read data rows
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] values = line.split(",", -1);

                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].trim();
                }

                if (values.length != headers.length) {
                    throw new RuntimeException(
                            "Column count mismatch. Expected "
                                    + headers.length + " but found "
                                    + values.length
                    );
                }

                Map<String, String> rowMap = new LinkedHashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    rowMap.put(headers[i], values[i]);
                }

                rows.add(new Object[]{rowMap});
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read CSV: " + filePath, e);
        }

        return rows.toArray(new Object[0][]);
    }
}