package com.company.Locations;

import java.util.HashSet;

public class Location {
    protected static int ID=0;
    private int id;
    private String name;
    private String city;
    private String country;
    private HashSet<String> possibleEvents;
    private int maxCapacity;

    public Location(String name, String city, String country, int maxCapacity) {
        this.id = ID++;
        this.name = name;
        this.city = city;
        this.country = country;
        this.maxCapacity = maxCapacity;
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

    public HashSet<String> getPossibleEvents() {
        return possibleEvents;
    }

    public void setPossibleEvents(HashSet<String> possibleEvents) {
        this.possibleEvents = possibleEvents;
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
                ", possibleEvents=" + possibleEvents +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}
