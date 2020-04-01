package com.company.Locations;

public class RestaurantLocation extends Location {
    private int nrOfTables;
    private int nrOfSeatsAtTable;
    private boolean hasCandyBar;
    private int pricePerMenu;

    public RestaurantLocation(String name, String city, String country, int maxCapacity, int nrOfTables, int nrOfSeatsAtTable, boolean hasCandyBar, int pricePerMenu) {
        super(name, city, country, maxCapacity);
        this.nrOfTables = nrOfTables;
        this.nrOfSeatsAtTable = nrOfSeatsAtTable;
        this.hasCandyBar = hasCandyBar;
        this.pricePerMenu = pricePerMenu;
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

    public boolean isHasCandyBar() {
        return hasCandyBar;
    }

    public void setHasCandyBar(boolean hasCandyBar) {
        this.hasCandyBar = hasCandyBar;
    }

    public int getPricePerMenu() {
        return pricePerMenu;
    }

    public void setPricePerMenu(int pricePerMenu) {
        this.pricePerMenu = pricePerMenu;
    }

    @Override
    public String toString() {
        return "RestaurantLocation{" +
                "nrOfTables=" + nrOfTables +
                ", nrOfSeatsAtTable=" + nrOfSeatsAtTable +
                ", hasCandyBar=" + hasCandyBar +
                ", pricePerMenu=" + pricePerMenu +
                '}';
    }
}
