package app.util;

import java.io.IOException;
import java.util.Properties;

public class PropHelper {

    private static PropHelper instance;
    private static Properties properties;

    private PropHelper() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropHelper getInstance() {
        if (instance == null) {
            instance = new PropHelper();
        }
        return instance;
    }

    public Properties getProperties() {
        return properties;
    }
}
