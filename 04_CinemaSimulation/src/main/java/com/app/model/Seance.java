package com.app.model;

import com.app.Exceptions.SeanceValidationException;
import com.app.converters.SeancesJsonConverter;
import com.app.properties.GetProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor

public class Seance {
    final static String[] properties = getProperties().split(",");
    final static BigDecimal PRICE_MAX = new BigDecimal(properties[0]);
    final static BigDecimal PRICE_MIN = new BigDecimal(properties[1]);
    final static List<Integer> DURATIONS = new ArrayList<>(Arrays.asList(Integer.valueOf(properties[2]), Integer.valueOf(properties[3]), Integer.valueOf(properties[4])));
    final static int ROOM_MAX = Integer.valueOf(properties[5]);
    final static int ROOM_MIN = Integer.valueOf(properties[6]);


    private Movie movie;
    private BigDecimal price;
    private int movieDuration;
    private int roomNumber;


    public Seance(Movie movie, BigDecimal price, int movieDuration, int roomNumber) {
        setMovie(movie);
        setPrice(price);
        setMovieDuration(movieDuration);
        setRoomNumber(roomNumber);
    }

    public void setPrice(BigDecimal price) {
        if (price.intValue() >= PRICE_MIN.intValue() && price.intValue() <= PRICE_MAX.intValue()) {
            this.price = price;
        } else {
            throw new SeanceValidationException("PRICE", LocalDateTime.now());
        }
    }

    public void setMovieDuration(int movieDuration) {

        if (DURATIONS.contains(movieDuration)) {
            this.movieDuration = movieDuration;
        } else {
            throw new SeanceValidationException("MOVIE DURATION", LocalDateTime.now());

        }
    }

    public void setRoomNumber(int roomNumber) {
        if (roomNumber >= ROOM_MIN && roomNumber <= ROOM_MAX) {
            this.roomNumber = roomNumber;
        } else {
            throw new SeanceValidationException("ROOM NUMBER", LocalDateTime.now());

        }
    }

    public static void saveSeances(String seancesFile, Set<Seance> seancesSet) {
        SeancesJsonConverter seancesJsonConverter = new SeancesJsonConverter(seancesFile);
        Seances seances = Seances.builder().seances(seancesSet).build();
        seancesJsonConverter.toJson(seances);
    }

    private static String getProperties() {
        GetProperties getProperties = new GetProperties();
        try {
            return getProperties.getSeanceValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
