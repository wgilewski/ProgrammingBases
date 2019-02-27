package com.app.model;


import com.app.Exceptions.MovieValidationException;
import com.app.properties.GetProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@Data
@NoArgsConstructor
@Builder

public class Movie {
    private String type;
    private String title;
    private String directorName;

    public Movie(String type, String title, String directorName) {
        setTitle(title);
        setDirectorName(directorName);
        setType(type);
    }

    public void setTitle(String title) {
        if (title.matches("[A-Z ]+")) {
            this.title = title;
        } else {
            throw new MovieValidationException("TITLE", LocalDateTime.now());
        }

    }

    public void setDirectorName(String directorName) {

        if (directorName.matches("[A-Z]+ [a-z]+")) {
            this.directorName = directorName;
        } else {
            throw new MovieValidationException("DIRECTOR NAME", LocalDateTime.now());
        }
    }

    public void setType(String type) {
        GetProperties getProperties = new GetProperties();
        String prop = "";
        try {
            prop = getProperties.getGenreValues();

        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> types = Arrays.asList(prop.split(","));

        if (types.contains(type)) {
            this.type = type;
        } else {
            throw new MovieValidationException("TYPE", LocalDateTime.now());
        }

    }
}
