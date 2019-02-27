package com.app.Exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Data
public class MovieValidationException extends CustomException {

    private String field;
    private LocalDateTime localDateTime;

    public MovieValidationException(String field, LocalDateTime localDateTime) {
        this.field = field;
        this.localDateTime = localDateTime;

        if (!getMap().isEmpty()) {
            for (Map.Entry<String, List<String>> m : getMap().entrySet()) {
                m.getValue().add(field + " " + localDateTime);
            }
        } else getMap().put("MOVIE VALIDATION", new ArrayList<>(Arrays.asList(field + " " + localDateTime)));
    }

    @Override
    public String getExceptionMessage() {
        return "MOVIE VALIDATION - " + field + ", " + " " + localDateTime;
    }
}
