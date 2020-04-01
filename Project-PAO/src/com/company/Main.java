package com.company;

import com.company.Locations.*;
import com.company.Reservations.Reservation;
import com.company.Services.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            Service s = new Service();
            Scanner in = new Scanner(System.in).useDelimiter("\n");
            System.out.println("Hi there! Please choose your action: ");
            System.out.println("1. See all locations" + "\n" + "2. See locations of a type" + "\n" + "3. Add new location" + "\n"
                    + "4. Get locations in a city" + "\n" + "5. Check if a location is available by name" + "\n"
                    + "6. See all reservations" + "\n" + "7. See all reservation at a date" + "\n"
                    + "8. See all reservations between two dates" + "\n" + "9. See reservations at one type of location"
                    + "\n" + "0. Exit");
            System.out.print("Your choice: "); String choice = in.next();
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
                    List<Reservation> myList = s.getReservationOnDate(date);
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
                } else if (choice.equals("0")) {
                    break;
                } else {
                    System.out.println("Please choose from possible actions");
                }
                System.out.print("Another action: "); choice = in.next();
            }
        } catch (NullPointerException | ParseException e) {
            System.out.println("Mornings like this..");
        }
    }
}
