package com.company.Services;

import com.company.Events.ConcertEvent;
import com.company.Events.PartyEvent;
import com.company.Events.PoolPartyEvent;
import com.company.Events.StandUpEvent;
import com.company.Guest.Guest;
import com.company.Locations.*;
import com.company.Reservations.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Service {
    private List<Location> locations = new ArrayList<>(Arrays.asList(
            new PubLocation("Shelter", "Bucharest", "RO",
                    200, 40, 4, true, false),
            new PoolLocation("Pool", "Bucharest", "RO",
                    200, true, true,false),
            new RestaurantLocation("Papito", "Cluj", "RO",
                    500, 100, 5, true, 100),
            new MusicHallLocation("Sala Palatului", "Bucharest", "RO",
                    4000)
    ));

    private Set<Reservation> reservations = new TreeSet<Reservation>(Arrays.asList(
            new Reservation(locations.get(0), new StandUpEvent("Costel Stand-up", "12/05/2020", new Guest("Costel"), 50, 80)),
            new Reservation(locations.get(1), new PoolPartyEvent("Pool party", "25/08/2020", 50)),
            new Reservation(locations.get(2), new PartyEvent("Wedding", "09/09/2020", new Guest("Guta"), new Guest("Ciolan"))),
            new Reservation(locations.get(3), new ConcertEvent("Vita de vie Concert", "04/04/2020", new Guest("Vita de vie"), 50, 75, 100))
    ));

    private Scanner in = new Scanner(System.in).useDelimiter("\n");

    public Service() {
    }

    // 1. Method to get all possible locations
    public List<Location> getLocations() {
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
        return typeReservations;
    }

    // 3. Method to add a new location
    public void addLocation() {
        System.out.println("Hi, what type of location do you want to add?");
        System.out.println("1. Music Hall" + "\n" + "2. Pool" + "\n" + "3. Pub" + "\n" + "4. Restaurant" + "\n" + "0. Exit");
        String choice = in.next();
        while (true) {
            if (choice.equals("1")) {
                String name, city, country;
                int maxCapacity;
                System.out.print("Enter location name: "); name = in.next();
                System.out.print("Enter name of the city: "); city = in.next();
                System.out.print("Enter country: "); country = in.next();
                System.out.print("Max capacity: (must be integer) "); maxCapacity = in.nextInt();
                locations.add(new MusicHallLocation(name, city, country, maxCapacity));
                break;
            } else if (choice.equals("2")) {
                String name, city, country;
                int maxCapacity;
                boolean hasBar, poolOpenedAtNight, hasScene;
                System.out.print("Enter location name: "); name = in.next();
                System.out.print("Enter name of the city: "); city = in.next();
                System.out.print("Enter country: "); country = in.next();
                System.out.print("Max capacity: (must be integer) "); maxCapacity = in.nextInt();
                System.out.print("Has a bar? (true or false) "); hasBar = in.nextBoolean();
                System.out.print("Pool opened after 8? (true or false) "); poolOpenedAtNight = in.nextBoolean();
                System.out.print("Does it has a scene? (true or false) "); hasScene = in.nextBoolean();
                locations.add(new PoolLocation(name, city, country, maxCapacity, hasBar, poolOpenedAtNight, hasScene));
                break;
            } else if (choice.equals("3")) {
                String name, city, country;
                int maxCapacity, nrOfTables, nrOfSeatsAtTable;
                boolean hasScene, hasGames;
                System.out.print("Enter location name: "); name = in.next();
                System.out.print("Enter name of the city: "); city = in.next();
                System.out.print("Enter country: "); country = in.next();
                System.out.print("Max capacity: (must be integer) "); maxCapacity = in.nextInt();
                System.out.print("Number of tables in pub: "); nrOfTables = in.nextInt();
                System.out.print("Number of seats at one table: "); nrOfSeatsAtTable = in.nextInt();
                System.out.print("Does it has a scene? (true or false) "); hasScene = in.nextBoolean();
                System.out.print("Has games? (true or false) "); hasGames = in.nextBoolean();
                locations.add(new PubLocation(name, city, country, maxCapacity, nrOfTables, nrOfSeatsAtTable, hasScene, hasGames));
                break;
            } else if (choice.equals("4")) {
                String name, city, country;
                int maxCapacity, nrOfTables, nrOfSeatsAtTable, pricePerMenu;
                boolean hasCandyBar;
                System.out.print("Enter location name: "); name = in.next();
                System.out.print("Enter name of the city: "); city = in.next();
                System.out.print("Enter country: "); country = in.next();
                System.out.print("Max capacity: (must be integer) "); maxCapacity = in.nextInt();
                System.out.print("Number of tables in restaurant: (must be integer) "); nrOfTables = in.nextInt();
                System.out.print("Number of seats at one table: (must be integer) "); nrOfSeatsAtTable = in.nextInt();
                System.out.print("Does it has a candy bar? (true or false) "); hasCandyBar = in.nextBoolean();
                System.out.print("Price per menu: (must be integer) "); pricePerMenu = in.nextInt();
                locations.add(new RestaurantLocation(name, city, country, maxCapacity, nrOfTables, nrOfSeatsAtTable, hasCandyBar, pricePerMenu));
                break;
            } else if (choice.equals("0")) {
                break;
            } else {
                System.out.println("Operation not possible, please select one of available actions!");
                System.out.print("New choice: "); choice = in.next();
            }
        }
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
        return false;
    }

    // 6. Method to get all reservations
    public Set<Reservation> getReservations() {
        return reservations;
    }

    // 7. Method to get all reservations at date
    public List<Reservation> getReservationOnDate(String date) throws ParseException {
        Date realDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        Iterator<Reservation> itr = reservations.iterator();
        List<Reservation> dateReservations = new ArrayList<>();
        while (itr.hasNext()) {
            Reservation current = itr.next();
            if (current.getEventDate().equals(realDate)) {
                dateReservations.add(current);
            }
        }
        return dateReservations;
    }

    // 8. Method to get reservations between two dates
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
        return dateReservations;
    }

    // 9. Get all reservation at one type of location
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
        return typeReservations;
    }

    // 10. Make reservation
    public void makeReservation() {
        System.out.println("Hi! What kind of event would you like to organize?");
        System.out.println("1. Concert" + "\n" + "2. Wedding" + "\n" + "3. Party" + "\n" + "4. Stand-up Comedy");
        System.out.print("Your choice: "); String choice = in.next();
        while (true) {
            if (choice.equals("1")) {
                List<Location> availableLocations = new ArrayList<>();
                for (Location loc : this.getLocations()) {
                    if(loc instanceof MusicHallLocation || loc instanceof PubLocation) {
                        availableLocations.add(loc);
                    }
                }
                int i = 0;
                System.out.println("Please select one of available locations");
                for (Location loc : availableLocations) {
                    i++;
                    System.out.println(i + ". " + loc.getName() + ", " + loc.getCity());
                }
                String newChoice = in.next();
            } else if (choice.equals("2")) {

            } else if (choice.equals("3")) {

            } else if (choice.equals("4")) {

            } else {
                System.out.println("Please enter available operation");
                System.out.print("Your choice: "); choice = in.next();
            }
        }
    }
}
