package com.company.Locations;

import com.company.Services.GenericCsv.CsvSerializable;

public class Location implements CsvSerializable {
    protected static int ID=0;
    private int id;
    private String name;
    private String city;
    private String country;
    private int maxCapacity;

    public Location() {

    }

    public Location(String name, String city, String country, int maxCapacity) {
        this.id = ID++;
        this.name = name;
        this.city = city;
        this.country = country;
        this.maxCapacity = maxCapacity;
    }

    public Location(String name, String city, String country, String maxCapacity){
        this.id = ID++;
        this.name = name;
        this.city = city;
        this.country = country;
        this.maxCapacity = Integer.parseInt(maxCapacity);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", maxCapacity=" + maxCapacity +
                '}';
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
