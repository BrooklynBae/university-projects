package org.example;

import java.util.HashMap;

public class Parser {
    public static HashMap<String, String> parse(String queryString) {
        HashMap<String, String> params = new HashMap<>();

        if (queryString == null || queryString.isEmpty()) {
            return params;
        }

        for (String pair : queryString.split("&")) {
            String[] keyValue = pair.split("=");
            String key = keyValue[0];
            if (keyValue.length > 1) {
                String value = keyValue[1];
                params.put(key, value);
            } else {
                params.put(key, "");
            }
        }

        return params;
    }
}
