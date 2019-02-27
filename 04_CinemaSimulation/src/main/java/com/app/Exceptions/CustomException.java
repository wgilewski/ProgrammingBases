package com.app.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {
    private Map<String, List<String>> map = new HashMap<>();

    public String getExceptionMessage() {
        return "";
    }

}
