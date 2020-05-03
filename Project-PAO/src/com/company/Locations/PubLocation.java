package com.company.Locations;

public class PubLocation extends Location {
    private int nrOfTables;
    private int nrOfSeatsAtTable;
    private boolean hasScene;
    private boolean hasGames;

    public PubLocation(String name, String city, String country, int maxCapacity, int nrOfTables, int nrOfSeatsAtTable, boolean hasScene, boolean hasGames) {
        super(name, city, country, maxCapacity);
        this.nrOfTables = nrOfTables;
        this.nrOfSeatsAtTable = nrOfSeatsAtTable;
        this.hasScene = hasScene;
        this.hasGames = hasGames;
    }

    public PubLocation(String name, String city, String country, String maxCapacity, String nrOfTables, String nrOfSeatsAtTable, String hasScene, String hasGames) {
        super(name, city, country, maxCapacity);
        this.nrOfTables = Integer.parseInt(nrOfTables);
        this.nrOfSeatsAtTable = Integer.parseInt(nrOfSeatsAtTable);
        this.hasScene = Boolean.parseBoolean(hasScene);
        this.hasGames = Boolean.parseBoolean(hasGames);
    }

    public int getNrOfTables() {
        return nrOfTables;
    }

    public void setNrOfTables(int nrOfTables) {
        this.nrOfTables = nrOfTables;
    }

    public int getNrOfSeatsAtTable() {
        return nrOfSeatsAtTable;
    }

    public void setNrOfSeatsAtTable(int nrOfSeatsAtTable) {
        this.nrOfSeatsAtTable = nrOfSeatsAtTable;
    }

    public boolean isHasScene() {
        return hasScene;
    }

    public void setHasScene(boolean hasScene) {
        this.hasScene = hasScene;
    }

    public boolean isHasGames() {
        return hasGames;
    }

    public void setHasGames(boolean hasGames) {
        this.hasGames = hasGames;
    }

    @Override
    public String toString() {
        return "PubLocation{" +
                "nrOfTables=" + nrOfTables +
                ", nrOfSeatsAtTable=" + nrOfSeatsAtTable +
                ", hasScene=" + hasScene +
                ", hasGames=" + hasGames +
                '}';
    }
}
