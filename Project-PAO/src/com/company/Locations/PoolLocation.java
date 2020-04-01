package com.company.Locations;

public class PoolLocation extends Location {
    private boolean hasBar;
    private boolean poolOpenedAtNight;
    private boolean hasScene;

    public PoolLocation(String name, String city, String country, int maxCapacity, boolean hasBar, boolean poolOpenedAtNight, boolean hasScene) {
        super(name, city, country, maxCapacity);
        this.hasBar = hasBar;
        this.poolOpenedAtNight = poolOpenedAtNight;
        this.hasScene = hasScene;
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
}
