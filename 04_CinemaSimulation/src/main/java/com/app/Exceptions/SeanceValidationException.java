package com.app.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
public class SeanceValidationException extends CustomException {

    private String field;
    private LocalDateTime localDateTime;


    public SeanceValidationException(String field, LocalDateTime localDateTime) {
        this.field = field;
        this.localDateTime = localDateTime;

        if (!getMap().isEmpty()) {
            for (Map.Entry<String, List<String>> m : getMap().entrySet()) {
                m.getValue().add(field + " " + localDateTime);
            }
        } else getMap().put("SEANCE VALIDATION", new ArrayList<>(Arrays.asList(field + " " + localDateTime)));
    }

    @Override
    public String getExceptionMessage() {
        return "SEANCE VALIDATION - " + field + ", " + " " + localDateTime;
    }

}
