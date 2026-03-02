package com.saucedemo.data.reader;

import com.google.gson.*;

import java.io.FileReader;
import java.util.*;

public final class JsonDataReader {

    private JsonDataReader() {}

    public static List<Map<String, String>> readAsMaps(String filePath) {

        try {

            JsonArray array = JsonParser
                    .parseReader(new FileReader(filePath))
                    .getAsJsonArray();

            List<Map<String, String>> rows = new ArrayList<>();

            for (JsonElement element : array) {

                JsonObject obj = element.getAsJsonObject();
                Map<String, String> row = new LinkedHashMap<>();

                for (String key : obj.keySet()) {

                    row.put(
                            key,
                            obj.get(key).isJsonNull()
                                    ? ""
                                    : obj.get(key).getAsString().trim()
                    );
                }

                rows.add(row);
            }

            return rows;

        } catch (Exception e) {
            throw new RuntimeException("Failed to read json: " + filePath, e);
        }
    }

    public static Object[][] toSingleColumnData(List<?> data) {

        Object[][] result = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i);
        }

        return result;
    }
}