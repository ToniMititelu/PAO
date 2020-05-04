package com.company.Locations;

import com.company.Services.GenericCsv.CsvSerializable;

public class PoolLocation extends Location implements CsvSerializable {
    private boolean hasBar;
    private boolean poolOpenedAtNight;
    private boolean hasScene;

    public PoolLocation() {

    }

    public PoolLocation(String name, String city, String country, int maxCapacity, boolean hasBar, boolean poolOpenedAtNight, boolean hasScene) {
        super(name, city, country, maxCapacity);
        this.hasBar = hasBar;
        this.poolOpenedAtNight = poolOpenedAtNight;
        this.hasScene = hasScene;
    }

    public PoolLocation(String name, String city, String country, String maxCapacity, String hasBar, String poolOpenedAtNight, String hasScene) {
        super(name, city, country, maxCapacity);
        this.hasBar = Boolean.parseBoolean(hasBar);
        this.poolOpenedAtNight = Boolean.parseBoolean(poolOpenedAtNight);
        this.hasScene = Boolean.parseBoolean(hasScene);
    }

    public boolean isHasBar() {
        return hasBar;
    }

    public void setHasBar(boolean hasBar) {
        this.hasBar = hasBar;
    }

    public boolean isPoolOpenedAtNight() {
        return poolOpenedAtNight;
    }

    public void setPoolOpenedAtNight(boolean poolOpenedAtNight) {
        this.poolOpenedAtNight = poolOpenedAtNight;
    }

    public boolean isHasScene() {
        return hasScene;
    }

    public void setHasScene(boolean hasScene) {
        this.hasScene = hasScene;
    }

    @Override
    public String toString() {
        return "PoolLocation{" +
                "hasBar=" + hasBar +
                ", poolOpenedAtNight=" + poolOpenedAtNight +
                ", hasScene=" + hasScene +
                '}';
    }

    @Override
    public String[] getColumnNames() {
        return new String[]{"name", "city", "country", "maxCapacity", "hasBar", "poolOpenedAtNight", "hasScene"};
    }

    @Override
    public String[] toStringArray() {
        return new String[]{getName(), getCity(), getCountry(), Integer.toString(getMaxCapacity()),
                    Boolean.toString(hasBar), Boolean.toString(poolOpenedAtNight), Boolean.toString(hasScene)};
    }

    @Override
    public void fromStringArray(String[] data) {
        setName(data[0]);
        setCity(data[1]);
        setCountry(data[2]);
        setMaxCapacity(Integer.parseInt(data[3]));
        setHasBar(Boolean.parseBoolean(data[4]));
        setPoolOpenedAtNight(Boolean.parseBoolean(data[5]));
        setHasScene(Boolean.parseBoolean(data[6]));
    }
}
