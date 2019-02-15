package com.app.model;


import com.app.converters.CarJsonConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor


public class Cars
{
    private Set<Car> cars = new HashSet<>();

    public Cars(Set<String> carSet)
    {
        for (String s : carSet)
        {
            CarJsonConverter carJsonConverter = new CarJsonConverter(s);
            cars.add(carJsonConverter.fromJson().get());
        }
    }
}
