package com.company.Services;

import com.company.Events.*;
import com.company.Guest.Guest;
import com.company.Locations.*;
import com.company.Reservations.Reservation;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Service {
    private CsvServiceUsingGenerics csv;
    private Timestamps t;

    private List<Location> locations;

    private Set<Reservation> reservations;

    private Scanner in = new Scanner(System.in).useDelimiter("\n");

    public Service() {
        this.t = Timestamps.getInstance();
        this.csv = CsvServiceUsingGenerics.getInstance();

        locations = this.csv.getLocations();
        this.t.writeTimestampsCsv("Import locations");

        reservations = this.csv.getReservations();
        this.t.writeTimestampsCsv("Import reservations");
    }

    // 1. Method to get all possible locations
    public List<Location> getLocations() {
        this.t.writeTimestampsCsv("Get all locations");
        return locations;
    }

    // 2. See all location of one type
    public List<Location> getLocationsOfType(String typeOfLocation) {
        Iterator<Location> itr = locations.iterator();
        List<Location> typeReservations = new ArrayList<>();
        while (itr.hasNext()) {
            Location current = itr.next();
            if(typeOfLocation.equals("Music Hall") && current instanceof MusicHallLocation) {
                typeReservations.add(current);
            } else if(typeOfLocation.equals("Pool") && current instanceof PoolLocation) {
                typeReservations.add(current);
            } else if(typeOfLocation.equals("Pub") && current instanceof PubLocation) {
                typeReservations.add(current);
            } else if(typeOfLocation.equals("Restaurant") && current instanceof RestaurantLocation) {
                typeReservations.add(current);
            }
        }
        this.t.writeTimestampsCsv("Get location of a type");
        return typeReservations;
    }

    // 3. Method to add a new location
    public void addLocation() throws IOException {
        System.out.println("Hi, what type of location do you want to add?");
        System.out.println("1. Music Hall" + "\n" + "2. Pool" + "\n" + "3. Pub" + "\n" + "4. Restaurant" + "\n" + "0. Exit");
        String choice = in.next();
        label:
        while (true) {
            switch (choice) {
                case "1": {
                    String name, city, country;
                    int maxCapacity;
                    System.out.print("Enter location name: ");
                    name = in.next();
                    System.out.print("Enter name of the city: ");
                    city = in.next();
                    System.out.print("Enter country: ");
                    country = in.next();
                    System.out.print("Max capacity: (must be integer) ");
                    maxCapacity = in.nextInt();
                    locations.add(new MusicHallLocation(name, city, country, maxCapacity));
                    break label;
                }
                case "2": {
                    String name, city, country;
                    int maxCapacity;
                    boolean hasBar, poolOpenedAtNight, hasScene;
                    System.out.print("Enter location name: ");
                    name = in.next();
                    System.out.print("Enter name of the city: ");
                    city = in.next();
                    System.out.print("Enter country: ");
                    country = in.next();
                    System.out.print("Max capacity: (must be integer) ");
                    maxCapacity = in.nextInt();
                    System.out.print("Has a bar? (true or false) ");
                    hasBar = in.nextBoolean();
                    System.out.print("Pool opened after 8? (true or false) ");
                    poolOpenedAtNight = in.nextBoolean();
                    System.out.print("Does it has a scene? (true or false) ");
                    hasScene = in.nextBoolean();
                    locations.add(new PoolLocation(name, city, country, maxCapacity, hasBar, poolOpenedAtNight, hasScene));
                    break label;
                }
                case "3": {
                    String name, city, country;
                    int maxCapacity, nrOfTables, nrOfSeatsAtTable;
                    boolean hasScene, hasGames;
                    System.out.print("Enter location name: ");
                    name = in.next();
                    System.out.print("Enter name of the city: ");
                    city = in.next();
                    System.out.print("Enter country: ");
                    country = in.next();
                    System.out.print("Max capacity: (must be integer) ");
                    maxCapacity = in.nextInt();
                    System.out.print("Number of tables in pub: ");
                    nrOfTables = in.nextInt();
                    System.out.print("Number of seats at one table: ");
                    nrOfSeatsAtTable = in.nextInt();
                    System.out.print("Does it has a scene? (true or false) ");
                    hasScene = in.nextBoolean();
                    System.out.print("Has games? (true or false) ");
                    hasGames = in.nextBoolean();
                    locations.add(new PubLocation(name, city, country, maxCapacity, nrOfTables, nrOfSeatsAtTable, hasScene, hasGames));
                    break label;
                }
                case "4": {
                    String name, city, country;
                    int maxCapacity, nrOfTables, nrOfSeatsAtTable, pricePerMenu;
                    boolean hasCandyBar;
                    System.out.print("Enter location name: ");
                    name = in.next();
                    System.out.print("Enter name of the city: ");
                    city = in.next();
                    System.out.print("Enter country: ");
                    country = in.next();
                    System.out.print("Max capacity: (must be integer) ");
                    maxCapacity = in.nextInt();
                    System.out.print("Number of tables in restaurant: (must be integer) ");
                    nrOfTables = in.nextInt();
                    System.out.print("Number of seats at one table: (must be integer) ");
                    nrOfSeatsAtTable = in.nextInt();
                    System.out.print("Does it has a candy bar? (true or false) ");
                    hasCandyBar = in.nextBoolean();
                    System.out.print("Price per menu: (must be integer) ");
                    pricePerMenu = in.nextInt();
                    locations.add(new RestaurantLocation(name, city, country, maxCapacity, nrOfTables, nrOfSeatsAtTable, hasCandyBar, pricePerMenu));
                    break label;
                }
                case "0":
                    break label;
                default:
                    System.out.println("Operation not possible, please select one of available actions!");
                    System.out.print("New choice: ");
                    choice = in.next();
                    break;
            }
        }
        this.t.writeTimestampsCsv("Add location");
        this.csv.setLocations((ArrayList<Location>) this.locations);
        this.csv.writeCsvLocations();
    }

    // 4. Method to get all possible locations in a given city
    public List<Location> getLocationsInCity(String city) {
        Iterator<Location> itr = locations.iterator();
        List<Location> cityLocations = new ArrayList<>();
        while (itr.hasNext()) {
            Location loc = itr.next();
            if(loc.getCity().equals(city)) {
                cityLocations.add(loc);
            }
        }
        this.t.writeTimestampsCsv("Get locations in city");
        return cityLocations;
    }

    // 5. Method do check if a location is available
    public boolean checkLocationExist(String name) {
        Iterator<Location> itr = locations.iterator();
        while (itr.hasNext()) {
            if (itr.next().getName().equals(name)) {
                return true;
            }
        }
        this.t.writeTimestampsCsv("Check if location exists");
        return false;
    }

    // 6. Delete a location
    public void deleteLocation() throws IOException {
        System.out.println("Choose one of the following locations to delete: ");
        int index = 1;
        for(Location location : this.locations) {
            System.out.println(index + ". " + location.getName() + ", " + location.getCity());
            index++;
        }
        index = Integer.parseInt(in.next());
        Location location = this.locations.get(index-1);
        this.locations.remove(index-1);

        // We need to delete all reservations from the deleted location
        List<Reservation> toRemove = new ArrayList<>();
        for(Reservation reservation : this.reservations) {
            if(location.getName().equals(reservation.getLocation().getName())) {
                toRemove.add(reservation);
            }
        }
        for(Reservation reservation : toRemove) {
            this.reservations.remove(reservation);
        }
        this.t.writeTimestampsCsv("Delete location");
        this.csv.setLocations((ArrayList<Location>) this.locations);
        this.csv.writeCsvLocations();
    }

    // 7. Method to get all reservations
    public Set<Reservation> getReservations() {
        this.t.writeTimestampsCsv("Get reservations");
        return reservations;
    }

    // 8. Method to get all reservations at date
    public List<Reservation> getReservationsOnDate(String date) throws ParseException {
        Date realDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        Iterator<Reservation> itr = reservations.iterator();
        List<Reservation> dateReservations = new ArrayList<>();
        while (itr.hasNext()) {
            Reservation current = itr.next();
            if (current.getEventDate().equals(realDate)) {
                dateReservations.add(current);
            }
        }
        this.t.writeTimestampsCsv("Get reservation on date");
        return dateReservations;
    }

    public boolean checkLocationAvailability(String date, String name) throws ParseException {
        return this.getReservationsOnDate(date).isEmpty() || !name.equals(this.getReservationsOnDate(date).get(0).getLocation().getName());
    }

    // 9. Method to get reservations between two dates
    public List<Reservation> getReservationBetweenDates(String date1, String date2) throws ParseException {
        Date realDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
        Date realDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(date2);
        if(realDate1.after(realDate2)) {
            Date aux = realDate1;
            realDate1 = realDate2;
            realDate2 = aux;
        }
        Iterator<Reservation> itr = reservations.iterator();
        List<Reservation> dateReservations = new ArrayList<>();
        while (itr.hasNext()) {
            Reservation current = itr.next();
            if ((current.getEventDate().after(realDate1) || current.getEventDate().equals(realDate1))
                    && (current.getEventDate().before(realDate2) || current.getEventDate().equals(realDate2))) {
                dateReservations.add(current);
            }
        }
        this.t.writeTimestampsCsv("Get reservation between dates");
        return dateReservations;
    }

    // 10. Get all reservation at one type of location
    public List<Reservation> getReservationAtTypeOfLocation(String typeOfLocation) {
        Iterator<Reservation> itr = reservations.iterator();
        List<Reservation> typeReservations = new ArrayList<>();
        while (itr.hasNext()) {
            Reservation current = itr.next();
            if(typeOfLocation.equals("Music Hall") && current.getLocation() instanceof MusicHallLocation) {
                typeReservations.add(current);
            } else if(typeOfLocation.equals("Pool") && current.getLocation() instanceof PoolLocation) {
                typeReservations.add(current);
            } else if(typeOfLocation.equals("Pub") && current.getLocation() instanceof PubLocation) {
                typeReservations.add(current);
            } else if(typeOfLocation.equals("Restaurant") && current.getLocation() instanceof RestaurantLocation) {
                typeReservations.add(current);
            }
        }
        this.t.writeTimestampsCsv("Get reservations in one type of locations");
        return typeReservations;
    }

    public String check(String date, String name) throws ParseException {
        while(true) {
            if(!this.checkLocationAvailability(date, name)) {
                System.out.print("Sorry, that date is taken, enter new one (DD/MM/YYYY): ");
                date = in.next();
            }
            else {
                return date;
            }
        }
    }

    // 11. Make reservation
    public void makeReservation() throws ParseException, IOException {
        System.out.println("Hi! What kind of event would you like to organize?");
        System.out.println("1. Concert" + "\n" + "2. Wedding" + "\n" + "3. Party" + "\n" + "4. Stand-up Comedy");
        System.out.print("Your choice: "); String choice = in.next();
        label:
        while (true) {
            List<Location> availableLocations = new ArrayList<>();
            int i = 0;
            switch (choice) {
                case "1": {
                    for (Location loc : this.getLocations()) {
                        if (loc instanceof MusicHallLocation || loc instanceof PubLocation) {
                            availableLocations.add(loc);
                        }
                    }
                    System.out.println("Please select one of available locations");
                    for (Location loc : availableLocations) {
                        i++;
                        System.out.println(i + ". " + loc.getName() + ", " + loc.getCity());
                    }
                    String newChoice = in.next();
                    Location location = availableLocations.get(Integer.parseInt(newChoice) - 1);
                    System.out.print("Event name: ");
                    String name = in.next();
                    System.out.print("Event date (DD/MM/YYYY): ");
                    String date = in.next();
                    check(date, location.getName());
                    System.out.print("Singer/band name: ");
                    String singerName = in.next();
                    Guest singer = new Guest(singerName);
                    System.out.print("Normal ticket price: ");
                    int normalTicketPrice = in.nextInt();
                    System.out.print("Gold ticket price: ");
                    int vipTicketPrice = in.nextInt();
                    Event event = new ConcertEvent(name, date, singer, normalTicketPrice, vipTicketPrice);
                    reservations.add(new Reservation(location, event));
                    break label;
                }
                case "2": {
                    for (Location loc : this.getLocations()) {
                        if (loc instanceof RestaurantLocation) {
                            availableLocations.add(loc);
                        }
                    }
                    System.out.println("Please select one of available locations");
                    for (Location loc : availableLocations) {
                        i++;
                        System.out.println(i + ". " + loc.getName() + ", " + loc.getCity());
                    }
                    String newChoice = in.next();
                    Location location = availableLocations.get(Integer.parseInt(newChoice) - 1);
                    System.out.print("Event name: ");
                    String name = in.next();
                    System.out.print("Event date (DD/MM/YYYY): ");
                    String date = in.next();
                    check(date, location.getName());
                    System.out.print("Singer/band name: ");
                    String singerName = in.next();
                    Guest singer = new Guest(singerName);
                    System.out.print("Singer/band name: ");
                    String photographerName = in.next();
                    Guest photographer = new Guest(photographerName);
                    Event event = new PartyEvent(name, date, singer, photographer);
                    reservations.add(new Reservation(location, event));
                    break label;
                }
                case "3": {
                    for (Location loc : this.getLocations()) {
                        if (loc instanceof PoolLocation) {
                            availableLocations.add(loc);
                        }
                    }
                    System.out.println("Please select one of available locations");
                    for (Location loc : availableLocations) {
                        i++;
                        System.out.println(i + ". " + loc.getName() + ", " + loc.getCity());
                    }
                    String newChoice = in.next();
                    Location location = availableLocations.get(Integer.parseInt(newChoice) - 1);
                    System.out.print("Event name: ");
                    String name = in.next();
                    System.out.print("Event date (DD/MM/YYYY): ");
                    String date = in.next();
                    check(date, location.getName());
                    System.out.print("Ticket price: ");
                    int ticketPrice = in.nextInt();
                    Event event = new PoolPartyEvent(name, date, ticketPrice);
                    reservations.add(new Reservation(location, event));
                    break label;
                }
                case "4": {
                    for (Location loc : this.getLocations()) {
                        if (loc instanceof PubLocation) {
                            availableLocations.add(loc);
                        }
                    }
                    System.out.println("Please select one of available locations");
                    for (Location loc : availableLocations) {
                        i++;
                        System.out.println(i + ". " + loc.getName() + ", " + loc.getCity());
                    }
                    String newChoice = in.next();
                    Location location = availableLocations.get(Integer.parseInt(newChoice) - 1);
                    System.out.print("Event name: ");
                    String name = in.next();
                    System.out.print("Event date (DD/MM/YYYY): ");
                    String date = in.next();
                    check(date, location.getName());
                    System.out.print("Comedian: ");
                    String comedianName = in.next();
                    Guest comedian = new Guest(comedianName);
                    System.out.print("Normal ticket price: ");
                    int normalTicketPrice = in.nextInt();
                    System.out.print("VIP ticket price: ");
                    int goldTicketPrice = in.nextInt();
                    Event event = new StandUpEvent(name, date, comedian, normalTicketPrice, goldTicketPrice);
                    reservations.add(new Reservation(location, event));
                    break label;
                }
                default:
                    System.out.println("Please enter available operation");
                    System.out.print("Your choice: ");
                    choice = in.next();
                    break;
            }
        }
        this.t.writeTimestampsCsv("Make reservation");
        this.csv.setReservations(this.reservations);
        this.csv.writeCsvReservations();
    }

    // 12. Remove a reservation
    public void removeReservation() throws ParseException, IOException {
        System.out.println("Choose reservation to be removed: ");
        int i = 1;
        for(Reservation reservation : reservations) {
            System.out.println(i + ". " + reservation.getLocation().getName() + ", " + reservation.getEvent().getName() + ", " + reservation.getEvent().getDate());
            i++;
        }
        int indexToRemove = in.nextInt();
        Iterator<Reservation> it = reservations.iterator();
        int j = 0;
        Reservation toRemove = new Reservation();
        while(j < indexToRemove && it.hasNext()) {
            toRemove = it.next();
            j++;
        }
        reservations.remove(toRemove);
        this.t.writeTimestampsCsv("Remove reservation");
        this.csv.setReservations(this.reservations);
        this.csv.writeCsvReservations();
    }

    public void writeToFiles() throws IOException {
        this.csv.setLocations(this.locations);
        this.csv.writeCsvLocations();

        this.csv.setReservations(this.reservations);
        this.csv.writeCsvReservations();

        this.t.writeTimestampsCsv("Update files");
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
