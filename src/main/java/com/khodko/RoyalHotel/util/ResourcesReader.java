package com.khodko.RoyalHotel.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourcesReader {

    public Properties load(String filename) throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                throw new IOException("File doesn't exist");
            }
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        }
    }
}
