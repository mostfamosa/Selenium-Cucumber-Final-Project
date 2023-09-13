package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadFile {
    public static Properties readPropertiesFile(String fileName) {
        Properties prop = new Properties();
        try (InputStream is = ReadFile.class.getResourceAsStream("/"+fileName)) {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
