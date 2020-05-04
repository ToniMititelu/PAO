package com.company.Locations;

public class RestaurantLocation extends Location {
    private int nrOfTables;
    private int nrOfSeatsAtTable;
    private boolean hasCandyBar;
    private int pricePerMenu;

    public RestaurantLocation() {

    }

    public RestaurantLocation(String name, String city, String country, int maxCapacity, int nrOfTables, int nrOfSeatsAtTable, boolean hasCandyBar, int pricePerMenu) {
        super(name, city, country, maxCapacity);
        this.nrOfTables = nrOfTables;
        this.nrOfSeatsAtTable = nrOfSeatsAtTable;
        this.hasCandyBar = hasCandyBar;
        this.pricePerMenu = pricePerMenu;
    }

    public RestaurantLocation(String name, String city, String country, String maxCapacity, String nrOfTables, String nrOfSeatsAtTable, String hasCandyBar, String pricePerMenu) {
        super(name, city, country, maxCapacity);
        this.nrOfTables = Integer.parseInt(nrOfTables);
        this.nrOfSeatsAtTable = Integer.parseInt(nrOfSeatsAtTable);
        this.hasCandyBar = Boolean.parseBoolean(hasCandyBar);
        this.pricePerMenu = Integer.parseInt(pricePerMenu);
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

    @Override
    public String[] getColumnNames() {
        return new String[]{"name", "city", "country", "maxCapacity", "nrOfTables", "nrOfSeatsAtTable", "hasCandyBar", "pricePerMenu"};
    }

    @Override
    public String[] toStringArray() {
        return new String[]{getName(), getCity(), getCountry(), Integer.toString(getMaxCapacity()),
                            Integer.toString(nrOfTables), Integer.toString(nrOfSeatsAtTable),
                            Boolean.toString(hasCandyBar), Integer.toString(pricePerMenu)};
    }

    @Override
    public void fromStringArray(String[] data) {
        setName(data[0]);
        setCity(data[1]);
        setCountry(data[2]);
        setMaxCapacity(Integer.parseInt(data[3]));
        setNrOfTables(Integer.parseInt(data[4]));
        setNrOfSeatsAtTable(Integer.parseInt(data[5]));
        setHasCandyBar(Boolean.parseBoolean(data[6]));
        setPricePerMenu(Integer.parseInt(data[7]));
    }
}
