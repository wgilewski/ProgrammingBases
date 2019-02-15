package com.app.converters;

import com.app.model.Cars;

public class CarsJsonConverter extends JsonConverter<Cars>
{
    public CarsJsonConverter(String jsonFilename)
    {
        super(jsonFilename);
    }
}
