package com.company;

import com.company.Events.ConcertEvent;
import com.company.Events.PartyEvent;
import com.company.Events.PoolPartyEvent;
import com.company.Events.StandUpEvent;
import com.company.Guest.Guest;
import com.company.Locations.*;
import com.company.Reservations.Reservation;
import com.company.Services.Service;

import java.text.ParseException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            Service s = new Service();

            // Added only for testing
            s.setLocations(new ArrayList<>(Arrays.asList(
                    new PubLocation("Shelter", "Bucharest", "RO",
                            200, 40, 4, true, false),
                    new PoolLocation("Pool", "Bucharest", "RO",
                            200, true, true,false),
                    new RestaurantLocation("Papito", "Cluj", "RO",
                            500, 100, 5, true, 100),
                    new MusicHallLocation("Sala Palatului", "Bucharest", "RO",
                            4000)
            )));

            s.setReservations(new TreeSet<Reservation>(Arrays.asList(
                    new Reservation(s.getLocations().get(0), new StandUpEvent("Costel Stand-up", "12/05/2020", new Guest("Costel"), 50, 80)),
                    new Reservation(s.getLocations().get(1), new PoolPartyEvent("Pool party", "25/08/2020", 50)),
                    new Reservation(s.getLocations().get(2), new PartyEvent("Wedding", "09/09/2020", new Guest("Guta"), new Guest("Ciolan"))),
                    new Reservation(s.getLocations().get(3), new ConcertEvent("Vita de vie Concert", "04/04/2020", new Guest("Vita de vie"), 50, 75, 100))
            )));

            Scanner in = new Scanner(System.in).useDelimiter("\n");
            menu(); String choice = in.next();
            System.out.println();
            while (true) {
                if (choice.equals("1")) {
                    for (Location loc : s.getLocations()) {
                        if (loc instanceof MusicHallLocation) {
                            System.out.println("Music Hall, " + loc.getName() + ", " + loc.getCity());
                        } else if (loc instanceof PoolLocation) {
                            System.out.println("Pool, " + loc.getName() + ", " + loc.getCity());
                        } else if (loc instanceof PubLocation) {
                            System.out.println("Pub, " + loc.getName() + ", " + loc.getCity());
                        } else if (loc instanceof RestaurantLocation) {
                            System.out.println("Restaurant, " + loc.getName() + ", " + loc.getCity());
                        }
                    }
                } else if (choice.equals("2")) {
                    System.out.println("Type of location accepted: Music Hall, Pool, Pub, Restaurant");
                    System.out.print("Your choice: "); String type = in.next();
                    for(Location location : s.getLocationsOfType(type)) {
                        System.out.println(type + ", " + location.getName());
                    }
                } else if (choice.equals("3")) {
                    s.addLocation();
                } else if (choice.equals("4")) {
                    System.out.print("Enter city where you want to search: ");
                    String city = in.nextLine();
                    List<Location> cityLocations = s.getLocationsInCity(city);
                    for (Location loc : cityLocations) {
                        System.out.println(loc.getName() + ", " + loc.getCity());
                    }
                } else if (choice.equals("5")) {
                    System.out.print("Enter location name: ");
                    String name = in.nextLine();
                    System.out.println(s.checkLocationExist(name));
                } else if (choice.equals("6")) {
                    for (Reservation reservation : s.getReservations()) {
                        System.out.println(reservation.getLocation().getName() + ", "
                                + reservation.getEvent().getName() + ", " + reservation.getEventDate());
                    }
                } else if (choice.equals("7")) {
                    System.out.print("Enter date in the following format DD/MM/YYYY: ");
                    String date = in.next();
                    List<Reservation> myList = s.getReservationsOnDate(date);
                    for (Reservation reservation : myList) {
                        System.out.println(reservation.getLocation().getName() + ", "
                                + reservation.getEvent().getName() + ", " + reservation.getEventDate());
                    }
                } else if (choice.equals("8")) {
                    System.out.print("Enter first date in the following format DD/MM/YYYY: ");
                    String date1 = in.next();
                    System.out.print("Enter second date in the following format DD/MM/YYYY: ");
                    String date2 = in.next();
                    List<Reservation> myList = s.getReservationBetweenDates(date1, date2);
                    for (Reservation reservation : myList) {
                        System.out.println(reservation.getLocation().getName() + ", "
                                + reservation.getEvent().getName() + ", " + reservation.getEventDate());
                    }
                } else if (choice.equals("9")) {
                    System.out.println("Type of location accepted: Music Hall, Pool, Pub, Restaurant");
                    System.out.print("Your choice: "); String type = in.next();
                    for(Reservation reservation : s.getReservationAtTypeOfLocation(type)) {
                        System.out.println(reservation.getLocation().getName() + ", "
                                + reservation.getEvent().getName() + ", " + reservation.getEventDate());
                    }
                } else if (choice.equals("10")) {
                    s.makeReservation();
                } else if (choice.equals("0")) {
                    break;
                } else {
                    System.out.println("Please choose from possible actions");
                }
                menu(); choice = in.next(); System.out.println();
            }
        } catch (NullPointerException | ParseException e) {
            System.out.println("Mornings like this..");
        }
    }

    public static void menu() {
        System.out.println("\nHi there! Please choose your action: ");
        System.out.println("1. See all locations\n2. See locations of a type\n3. Add new location\n"
                + "4. Get locations in a city\n5. Check if a location is available by name\n"
                + "6. See all reservations\n7. See all reservation at a date\n"
                + "8. See all reservations between two dates\n9. See reservations at one type of location\n"
                + "10. Make reservation\n0. Exit");
        System.out.print("Your choice: ");
    }
}
