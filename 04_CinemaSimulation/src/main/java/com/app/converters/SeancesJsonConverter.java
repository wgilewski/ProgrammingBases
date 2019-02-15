package com.app.converters;


import com.app.model.Seances;

public class SeancesJsonConverter extends JsonConverter<Seances>
{
    public SeancesJsonConverter(String jsonFilename)
    {
        super(jsonFilename);
    }
}
