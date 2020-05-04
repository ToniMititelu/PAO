package com.company.Locations;

import com.company.Services.GenericCsv.CsvSerializable;

public class MusicHallLocation extends Location implements CsvSerializable {

    public MusicHallLocation() {

    }

    public MusicHallLocation(String name, String city, String country, int maxCapacity) {
        super(name, city, country, maxCapacity);
    }

    public MusicHallLocation(String name, String city, String country, String maxCapacity) {
        super(name, city, country, maxCapacity);
    }

    @Override
    public String[] getColumnNames() {
        return new String[]{"name", "city", "country", "maxCapacity"};
    }

    @Override
    public String[] toStringArray() {
        return new String[]{getName(), getCity(), getCountry(), Integer.toString(getMaxCapacity())};
    }

    @Override
    public void fromStringArray(String[] data) {
        setName(data[0]);
        setCity(data[1]);
        setCountry(data[2]);
        setMaxCapacity(Integer.parseInt(data[3]));
    }
}
