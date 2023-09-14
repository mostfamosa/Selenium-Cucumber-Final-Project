package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ReadFile {
    public static Properties readPropertiesFile(String fileName) {
        Properties prop = new Properties();
        try (InputStream is = ReadFile.class.getResourceAsStream("/" + fileName);
             InputStreamReader reader = new InputStreamReader(is, "UTF-8")) {
            prop.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
