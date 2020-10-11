package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestUtils {
    public static final Properties properties = loadProperties();

    private static Properties loadProperties() {
        InputStream input = TestUtils.class.getClassLoader().getResourceAsStream("config.properties");

        if (input == null)
            throw new RuntimeException("Sorry, unable to find config.properties");

        Properties properties = new Properties();
        try {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Sorry, unable to read config.properties because of IOException. " + e.getMessage());
        }
        return properties;
    }
}
