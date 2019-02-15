package com.app.converters;

import com.app.model.Movies;

public class MoviesJsonConverter extends JsonConverter<Movies>
{
    public MoviesJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
