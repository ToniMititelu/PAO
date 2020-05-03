package com.company.Services;

import com.company.Events.*;
import com.company.Guest.Guest;
import com.company.Locations.*;
import com.company.Reservations.Reservation;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class CsvService {
    private static CsvService instance = null;
    private final String csvSplitBy = ",";
    private String line = "";
    private ArrayList<Location> locations = new ArrayList<>();
    private Set<Reservation> reservations = new TreeSet<>();
    private ArrayList<String> csvList = new ArrayList<>(Arrays.asList(
            "src/com/company/Services/csvs/PubLocations.csv",
            "src/com/company/Services/csvs/PoolLocations.csv",
            "src/com/company/Services/csvs/RestaurantLocations.csv",
            "src/com/company/Services/csvs/MusicHallLocations.csv"
    ));
    private String reservationsCSV = "src/com/company/Services/csvs/Reservations.csv";

    private CsvService() {
        this.readCsvLocations();
        this.readCsvReservations();
    }

    public static CsvService getInstance() {
        if(instance == null) {
            instance = new CsvService();
        }
        return instance;
    }

    public void readCsvLocations() {
        int i = 0;
        for(var csvFile : csvList) {
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

                line = br.readLine();
                while ((line = br.readLine()) != null) {

                    String[] location = line.split(csvSplitBy);

                    switch (i) {
                        case 0: {
                            locations.add(new PubLocation(location[0], location[1], location[2], Integer.parseInt(location[3]), Integer.parseInt(location[4]), Integer.parseInt(location[5]), Boolean.parseBoolean(location[6]), Boolean.parseBoolean(location[7])));
                            break;
                        }
                        case 1: {
                            locations.add(new PoolLocation(location[0], location[1], location[2], Integer.parseInt(location[3]), Boolean.parseBoolean(location[4]), Boolean.parseBoolean(location[5]), Boolean.parseBoolean(location[6])));
                            break;
                        }
                        case 2: {
                            locations.add(new RestaurantLocation(location[0], location[1], location[2], Integer.parseInt(location[3]), Integer.parseInt(location[4]), Integer.parseInt(location[5]), Boolean.parseBoolean(location[6]), Integer.parseInt(location[7])));
                            break;
                        }
                        case 3: {
                            locations.add(new MusicHallLocation(location[0], location[1], location[2], Integer.parseInt(location[3])));
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    public void writeCsvLocations() throws IOException {
        boolean firstOfPub = false, firstOfPool = false, firstOfRest = false, firstOfMusic = false;
        //locations.add(new MusicHallLocation("disco", "craiova", "RO", 200));
        for(var location : locations) {
            if(location instanceof PubLocation) {
                try (FileWriter writer = new FileWriter(csvList.get(0), firstOfPub)) {
                    String toWrite = "";
                    if(!firstOfPub) {
                        toWrite += "name,city,country,maxCapacity,nrOfTables,nrOfSeatsAtTable,hasScene,hasGames\n";
                    }
                    toWrite = toWrite + location.getName() + "," + location.getCity() + "," +
                                location.getCountry() + "," + location.getMaxCapacity() + "," +
                                ((PubLocation) location).getNrOfTables() + "," + ((PubLocation) location).getNrOfSeatsAtTable() + "," +
                                Boolean.toString(((PubLocation) location).isHasScene()) + "," + Boolean.toString(((PubLocation) location).isHasGames()) + "\n";
                    writer.write(toWrite);
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                firstOfPub = true;
            } else if(location instanceof PoolLocation) {
                try (FileWriter writer = new FileWriter(csvList.get(1), firstOfPool)) {
                    String toWrite = "";
                    if(!firstOfPool) {
                        toWrite += "name,city,country,maxCapacity,hasBar,poolOpenedAtNight,hasScene\n";
                    }
                    toWrite = toWrite + location.getName() + "," + location.getCity() + "," +
                            location.getCountry() + "," + location.getMaxCapacity() + "," +
                            Boolean.toString(((PoolLocation) location).isHasBar()) + "," +
                            Boolean.toString(((PoolLocation) location).isPoolOpenedAtNight()) + "," +
                            Boolean.toString(((PoolLocation) location).isHasScene()) + "\n";
                    writer.write(toWrite);
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                firstOfPool = true;
            } else if(location instanceof RestaurantLocation) {
                try (FileWriter writer = new FileWriter(csvList.get(2), firstOfRest)) {
                    String toWrite = "";
                    if(!firstOfRest) {
                        toWrite += "name,city,country,maxCapacity,nrOfTables,nrOfSeatsAtTable,hasCandyBar,pricePerMenu\n";
                    }
                    toWrite = toWrite + location.getName() + "," + location.getCity() + "," +
                            location.getCountry() + "," + location.getMaxCapacity() + "," +
                            ((RestaurantLocation) location).getNrOfTables() + "," + ((RestaurantLocation) location).getNrOfSeatsAtTable() + "," +
                            Boolean.toString(((RestaurantLocation) location).isHasCandyBar()) + "," + ((RestaurantLocation) location).getPricePerMenu() + "\n";
                    writer.write(toWrite);
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                firstOfRest = true;
            } else if(location instanceof MusicHallLocation) {
                try (FileWriter writer = new FileWriter(csvList.get(3), firstOfMusic)) {
                    String toWrite = "";
                    if(!firstOfMusic) {
                        toWrite += "name,city,country,maxCapacity\n";
                    }
                    toWrite = toWrite + location.getName() + "," + location.getCity() + "," +
                            location.getCountry() + "," + location.getMaxCapacity() + "\n";
                    writer.write(toWrite);
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                firstOfMusic = true;
            }
        }
    }

    public Location findLocationByName(String name) {
        for(var location : this.locations) {
            if(location.getName().equals(name)) {
                return location;
            }
        }
        return null;
    }

    public void readCsvReservations() {
        // - in csv means we don't need that column
        // Concert: eventName,eventDate,guest1,-,normalTicketPrice,vipTicketPrice
        // Party: eventName,eventDate,guest1,guest2,-,-
        // Pool Party: eventName,eventDate,-,-,normalTicketPrice,-
        // StandUp: eventName,eventDate,guest1,-,normalTicketPrice,vipTicketPrice
        try (BufferedReader br = new BufferedReader(new FileReader(reservationsCSV))) {

            line = br.readLine();
            while ((line = br.readLine()) != null) {
                Event event;
                String[] reservation = line.split(csvSplitBy);
                Location location = findLocationByName(reservation[0]);
                switch (reservation[1]) {
                    case "Concert":
                        event = new ConcertEvent(reservation[2], reservation[3], new Guest(reservation[4]), Integer.parseInt(reservation[6]), Integer.parseInt(reservation[7]));
                        break;
                    case "Party":
                        event = new PartyEvent(reservation[2], reservation[3], new Guest(reservation[4]), new Guest(reservation[5]));
                        break;
                    case "Pool":
                        event = new PoolPartyEvent(reservation[2], reservation[3], Integer.parseInt(reservation[6]));
                        break;
                    default:
                        event = new StandUpEvent(reservation[2], reservation[3], new Guest(reservation[4]), Integer.parseInt(reservation[6]), Integer.parseInt(reservation[7]));
                        break;
                }
                Reservation r = new Reservation(location, event);
                reservations.add(r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCsvReservations() throws IOException {
        //Location location = locations.get(2);
        //Event event = new PoolPartyEvent("PoolV2", "01/07/2019", 35);
        //reservations.add(new Reservation(location, event));
        try (FileWriter writer = new FileWriter(reservationsCSV)) {
            String toWrite = "locationName,eventType,eventName,eventDate,guest1,guest2,normalTicketPrice,vipTicketPrice\n";
            for(var reservation : reservations) {
                if (reservation.getEvent() instanceof ConcertEvent) {
                    toWrite = toWrite + reservation.getLocation().getName() + ",Concert," +
                                reservation.getEvent().getName() + "," + reservation.getEvent().getDate() + ","  +
                                ((ConcertEvent) reservation.getEvent()).getSinger() + ",-," + Integer.toString(((ConcertEvent) reservation.getEvent()).getNormalTicketPrice()) +
                                "," + Integer.toString(((ConcertEvent) reservation.getEvent()).getVipTicketPrice()) + "\n";
                } else if (reservation.getEvent() instanceof PartyEvent) {
                    toWrite = toWrite + reservation.getLocation().getName() + ",Party," +
                                reservation.getEvent().getName() + "," + reservation.getEvent().getDate() + ","  +
                            ((PartyEvent) reservation.getEvent()).getSinger() + "," + ((PartyEvent) reservation.getEvent()).getPhotographer() + ",-,-\n";
                } else if (reservation.getEvent() instanceof PoolPartyEvent) {
                    toWrite = toWrite + reservation.getLocation().getName() + ",Pool," +
                                reservation.getEvent().getName() + "," + reservation.getEvent().getDate() + ","  +
                            "-,-," + Integer.toString(((PoolPartyEvent) reservation.getEvent()).getPricePerTicket()) + ",-\n";
                } else {
                    toWrite = toWrite + reservation.getLocation().getName() + ",StandUp," +
                                reservation.getEvent().getName() + "," + reservation.getEvent().getDate() + ","  +
                                ((StandUpEvent) reservation.getEvent()).getComedian() + ",-," +
                                Integer.toString((((StandUpEvent) reservation.getEvent()).getPricePerSeatNormal())) + "," +
                                Integer.toString((((StandUpEvent) reservation.getEvent()).getPricePerSeatVIP())) + "\n";
                }
            }
            writer.write(toWrite);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void display() {
        for(var loc : locations) {
            System.out.println(loc.getName());
        }
    }

    public void displayReservations() {
        for(var reservation : reservations) {
            System.out.println(reservation.getLocation().getName() + "   " + reservation.getEvent().getName());
        }
    }
}
