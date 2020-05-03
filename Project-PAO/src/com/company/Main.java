package com.company;

import com.company.Locations.*;
import com.company.Reservations.Reservation;
import com.company.Services.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*
    public static void main(String[] args) throws ParseException, IOException {
        //CsvService csv = CsvService.getInstance();
        //csv.readCsvLocations();
        //csv.display();
        //csv.writeCsvLocations();
        //csv.readCsvReservations();
        //csv.display();
        //csv.displayReservations();
        //csv.writeCsvReservations();
        Service s = new Service();

                        // Added only for testing
                s.setLocations(new ArrayList<>(Arrays.asList(
                        new PubLocation("Shelter", "Bucharest", "RO", 200, 40, 4, true, false),
                        new PoolLocation("Pool", "Bucharest", "RO", 200, true, true, false),
                        new RestaurantLocation("Papito", "Cluj", "RO", 500, 100, 5, true, 100),
                        new MusicHallLocation("Sala Palatului", "Bucharest", "RO", 4000)
                )));

                s.setReservations(new TreeSet<>(Arrays.asList(
                        new Reservation(s.getLocations().get(0), new StandUpEvent("Costel Stand-up", "12/05/2020", new Guest("Costel"), 50, 80)),
                        new Reservation(s.getLocations().get(1), new PoolPartyEvent("Pool party", "25/08/2020", 50)),
                        new Reservation(s.getLocations().get(2), new PartyEvent("Wedding", "09/09/2020", new Guest("Guta"), new Guest("Ciolan"))),
                        new Reservation(s.getLocations().get(3), new ConcertEvent("Vita de vie Concert", "04/04/2020", new Guest("Vita de vie"), 50, 100))
                )));
    } */


    public static void main(String[] args) throws ParseException, IOException {
	// write your code here

        Service s = new Service();
        int numberOfTries = 3;
        while(true) {
            try {
                Scanner in = new Scanner(System.in).useDelimiter("\n");
                boolean end = false;
                do {
                    menu();
                    String choice = in.next();
                    System.out.println();
                    switch (choice) {
                        case "1": {
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
                            break;
                        }
                        case "2": {
                            System.out.println("Type of location accepted: Music Hall, Pool, Pub, Restaurant");
                            System.out.print("Your choice: ");
                            String type = in.next();
                            for (Location location : s.getLocationsOfType(type)) {
                                System.out.println(type + ", " + location.getName());
                            }
                            break;
                        }
                        case "3": {
                            s.addLocation();
                            break;
                        }
                        case "4": {
                            System.out.print("Enter city where you want to search: ");
                            String city = in.next();
                            List<Location> cityLocations = s.getLocationsInCity(city);
                            for (Location loc : cityLocations) {
                                System.out.println(loc.getName() + ", " + loc.getCity());
                            }
                            break;
                        }
                        case "5": {
                            System.out.print("Enter location name: ");
                            String name = in.next();
                            System.out.println(s.checkLocationExist(name));
                            break;
                        }
                        case "6": {
                            s.deleteLocation();
                            break;
                        }
                        case "7": {
                            for (Reservation reservation : s.getReservations()) {
                                System.out.println(reservation.getLocation().getName() + ", "
                                        + reservation.getEvent().getName() + ", " + reservation.getEvent().getDate());
                            }
                            break;
                        }
                        case "8": {
                            System.out.print("Enter date in the following format DD/MM/YYYY: ");
                            String date = in.next();
                            List<Reservation> myList = s.getReservationsOnDate(date);
                            for (Reservation reservation : myList) {
                                System.out.println(reservation.getLocation().getName() + ", "
                                        + reservation.getEvent().getName() + ", " + reservation.getEvent().getDate());
                            }
                            break;
                        }
                        case "9": {
                            System.out.print("Enter first date in the following format DD/MM/YYYY: ");
                            String date1 = in.next();
                            System.out.print("Enter second date in the following format DD/MM/YYYY: ");
                            String date2 = in.next();
                            List<Reservation> myList = s.getReservationBetweenDates(date1, date2);
                            for (Reservation reservation : myList) {
                                System.out.println(reservation.getLocation().getName() + ", "
                                        + reservation.getEvent().getName() + ", " + reservation.getEvent().getDate());
                            }
                            break;
                        }
                        case "10": {
                            System.out.println("Type of location accepted: Music Hall, Pool, Pub, Restaurant");
                            System.out.print("Your choice: ");
                            String type = in.next();
                            for (Reservation reservation : s.getReservationAtTypeOfLocation(type)) {
                                System.out.println(reservation.getLocation().getName() + ", "
                                        + reservation.getEvent().getName() + ", " + reservation.getEvent().getDate());
                            }
                            break;
                        }
                        case "11": {
                            s.makeReservation();
                            break;
                        }
                        case "12": {
                            s.removeReservation();
                            break;
                        }
                        case "0": {
                            s.writeToFiles();
                            System.exit(0);
                            break;
                        }
                        default: {
                            System.out.println("Please choose from possible actions");
                        }
                    }
                } while (!end);
            } catch (ParseException | IOException e) {
                System.out.println("Seems you got date wrong, please follow the format");
                if(--numberOfTries == 0) throw e;
                System.out.println("You have " + numberOfTries + " tries left");
            }
        }
    }

    public static void menu() {
        System.out.println("\nHi there! Please choose your action: ");
        System.out.println("1. See all locations\n2. See locations of a type\n3. Add new location\n"
                + "4. Get locations in a city\n5. Check if a location is available by name\n6. Remove location\n"
                + "7. See all reservations\n8. See all reservation at a date\n"
                + "9. See all reservations between two dates\n10. See reservations at one type of location\n"
                + "11. Make reservation\n12. Remove reservation\n0. Exit");
        System.out.print("Your choice: ");
    }


}
