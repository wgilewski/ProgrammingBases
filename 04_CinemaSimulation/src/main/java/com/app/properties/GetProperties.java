package com.app.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
    String result = "";
    InputStream inputStream;


    public String getGenreValues() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "genres.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            String genres = prop.getProperty("genres");


            result = genres;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }

    public String getSeanceValues() throws IOException {

        try {

            Properties prop = new Properties();
            String propFileName = "seance.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            String priceMax = prop.getProperty("PRICE_MAX");
            String priceMin = prop.getProperty("PRICE_MIN");
            String duration1 = prop.getProperty("DURATION_1");
            String duration2 = prop.getProperty("DURATION_2");
            String duration3 = prop.getProperty("DURATION_3");
            String roomMax = prop.getProperty("ROOM_MAX");
            String roomMin = prop.getProperty("ROOM_MIN");

            result = priceMax + "," + priceMin + "," + duration1 + "," + duration2 + "," + duration3 + "," + roomMax + "," + roomMin;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }

    public String getRepertoireValues() throws IOException {

        try {

            Properties prop = new Properties();
            String propFileName = "repertoire.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            String houreMax = prop.getProperty("HOUR_MAX");
            String houreMin = prop.getProperty("HOUR_MIN");
            String minutesMax = prop.getProperty("MIN_MAX");
            String minutesMin = prop.getProperty("MIN_MIN");


            result = houreMax + "," + houreMin + "," + minutesMax + "," + minutesMin;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}
