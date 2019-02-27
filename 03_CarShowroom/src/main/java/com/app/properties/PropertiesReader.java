package com.app.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    int result = 0;
    InputStream inputStream;

    public int getPropValues(String producer) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = producer + ".properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            String distance = prop.getProperty("OVERWIEV");

            result = Integer.valueOf(distance);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}
