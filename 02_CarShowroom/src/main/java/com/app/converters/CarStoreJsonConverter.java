package com.app.converters;

import com.app.model.Cars;

public class CarStoreJsonConverter extends JsonConverter<Cars> {
    public CarStoreJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
