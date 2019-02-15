package com.app.model;

import com.app.converters.SeancesJsonConverter;
import com.app.properties.GetProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Repertoire
{
    private final static String[] propValues = getProperties().split(",");
    private final static int HOUR_MAX = Integer.valueOf(propValues[0]);
    private final static int HOUR_MIN = Integer.valueOf(propValues[1]);
    private final static int MIN_MAX = Integer.valueOf(propValues[2]);
    private final static int MIN_MIN = Integer.valueOf(propValues[3]);

    private Map<LocalDate, Map<Seance, List<LocalTime>>> map = new HashMap<>();
    private Seances seances;

    public Repertoire(String seancesFileName, LocalDate date1, LocalDate date2)
    {
        SeancesJsonConverter seancesJsonConverter = new SeancesJsonConverter(seancesFileName);
        seances = seancesJsonConverter.fromJson().get();
        Map<Seance,List<LocalTime>> seancesMap = new HashMap<>();

        for (Seance s : seances.getSeances())
        {
            seancesMap.put(s,localTimesGenerator());
        }

        Period period = Period.between(date1, date2);
        int dif = period.getDays();

        for (int i = 0; i < dif; i++)
        {
            map.put(LocalDate.now().plusDays(i),seancesMap);
        }
    }

    private static String getProperties()
    {
        GetProperties getProperties = new GetProperties();
        try {
            String prop = getProperties.getRepertoireValues();
            return prop;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private List<LocalTime> localTimesGenerator()
    {
        Random rnd = new Random();
        int randomInt = rnd.nextInt(6) + 3;
        List<LocalTime> localTimes = new ArrayList<>();

        do {

            int hour = rnd.nextInt(HOUR_MAX - HOUR_MIN + 1) + HOUR_MIN;
            int minute = rnd.nextInt(MIN_MAX - MIN_MIN + 1) + MIN_MIN;
            localTimes.add(LocalTime.of(hour,minute));

        }while (localTimes.size() != randomInt);

        Collections.sort(localTimes);
        return localTimes;
    }
    public String toString(LocalDate localDate)
    {
        if (!map.containsKey(localDate))
        {
            return "nie ma jeszcze repertuaru na ten dzień ";
        }
        else {
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<LocalDate, Map<Seance, List<LocalTime>>> m : map.entrySet())
            {
                stringBuilder.setLength(0);
                stringBuilder.append("dnia : " + localDate + " mogą u nas państwo zobaczyć : \n\n");
                for (Map.Entry<Seance, List<LocalTime>> m1 : m.getValue().entrySet()) {
                    stringBuilder.append(m1.getKey().getMovie().getTitle() + " jest to film " + m1.getKey().getMovie().getType() +
                            " wyreżyserowany przez " + m1.getKey().getMovie().getDirectorName() + " i trwa on " + m1.getKey().getMovieDuration() +
                            "minut. Cena biletu to " + m1.getKey().getPrice() + "zł. \nZapraszamy na seans do sali numer : " + m1.getKey().getRoomNumber() +
                            " w godzinach : \n" + m1.getValue() + "\n\n");
                }
            }
            return String.valueOf(stringBuilder);
        }}
}

