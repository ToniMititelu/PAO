package com.company.Services;

import com.company.Events.*;
import com.company.Guest.Guest;
import com.company.Locations.*;
import com.company.Reservations.Reservation;
import com.company.Services.GenericCsv.CsvReader;
import com.company.Services.GenericCsv.CsvWriter;

import java.io.*;
import java.util.*;

public class CsvServiceUsingGenerics {

    private static CsvServiceUsingGenerics instance = null;
    private List<Location> locations = new ArrayList<>();
    private Set<Reservation> reservations = new TreeSet<>();

    private ArrayList<String> csvList = new ArrayList<>(Arrays.asList(
            "src/com/company/Services/csvs/PubLocations.csv",
            "src/com/company/Services/csvs/PoolLocations.csv",
            "src/com/company/Services/csvs/RestaurantLocations.csv",
            "src/com/company/Services/csvs/MusicHallLocations.csv"
    ));
    private String csvReservations = "src/com/company/Services/csvs/Reservations.csv";


    private CsvServiceUsingGenerics() {
        readCsvLocations();
        readCsvReservations();
    }

    public static CsvServiceUsingGenerics getInstance() {
        if(instance == null) {
            instance = new CsvServiceUsingGenerics();
        }
        return instance;
    }

    public void readCsvLocations() {
        int i = 0;
        for(var csv : csvList) {
            switch (i) {
                case 0: {
                    try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
                        CsvReader<PubLocation> reader = new CsvReader<>(br, new PubLocation());

                        while (reader.hasMoreObjects()) {

                            PubLocation aux = reader.readObject(new PubLocation());
                            locations.add(aux);

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 1: {
                    try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
                        CsvReader<PoolLocation> reader = new CsvReader<>(br, new PoolLocation());

                        while (reader.hasMoreObjects()) {

                            PoolLocation aux = reader.readObject(new PoolLocation());
                            locations.add(aux);

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 2: {
                    try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
                        CsvReader<RestaurantLocation> reader = new CsvReader<>(br, new RestaurantLocation());

                        while (reader.hasMoreObjects()) {

                            RestaurantLocation aux = reader.readObject(new RestaurantLocation());
                            locations.add(aux);

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 3: {
                    try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
                        CsvReader<MusicHallLocation> reader = new CsvReader<>(br, new MusicHallLocation());

                        while (reader.hasMoreObjects()) {

                            MusicHallLocation aux = reader.readObject(new MusicHallLocation());
                            locations.add(aux);

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
            i++;
        }
    }

    public void writeCsvLocations() {
        boolean appendPub = false, appendPool = false, appendRest = false, appendMusic = false;
        for(var location : locations) {
            if(location instanceof PubLocation) {
                try (FileWriter writer = new FileWriter(csvList.get(0), appendPub)) {

                    CsvWriter<PubLocation> writer1 = new CsvWriter<>(writer, (PubLocation) location, appendPub);
                    writer1.writeObject((PubLocation) location);

                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                appendPub = true;
            }
            if(location instanceof PoolLocation) {
                try (FileWriter writer = new FileWriter(csvList.get(1), appendPool)) {

                    CsvWriter<PoolLocation> writer1 = new CsvWriter<>(writer, (PoolLocation) location, appendPool);
                    writer1.writeObject((PoolLocation) location);

                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                appendPool = true;
            }
            if(location instanceof RestaurantLocation) {
                try (FileWriter writer = new FileWriter(csvList.get(2), appendRest)) {

                    CsvWriter<RestaurantLocation> writer1 = new CsvWriter<>(writer, (RestaurantLocation) location, appendRest);
                    writer1.writeObject((RestaurantLocation) location);

                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                appendRest = true;
            }
            if(location instanceof MusicHallLocation) {
                try (FileWriter writer = new FileWriter(csvList.get(3), appendMusic)) {

                    CsvWriter<MusicHallLocation> writer1 = new CsvWriter<>(writer, (MusicHallLocation) location, appendMusic);
                    writer1.writeObject((MusicHallLocation) location);

                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                appendMusic = true;
            }

        }
    }

    public void readCsvReservations() {
        try (BufferedReader br = new BufferedReader(new FileReader(csvReservations))) {
            CsvReader<Reservation> reader = new CsvReader<>(br, new Reservation());

            while (reader.hasMoreObjects()) {

                Reservation aux = reader.readObject(new Reservation((ArrayList<Location>) locations));
                if(aux.getLocation() != null) {
                    reservations.add(aux);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCsvReservations() {
        boolean appendReservation = false;
        for(var reservation : reservations) {
            try (FileWriter writer = new FileWriter(csvReservations, appendReservation)) {

                CsvWriter<Reservation> writer1 = new CsvWriter<>(writer, reservation, appendReservation);
                writer1.writeObject(reservation);

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
            appendReservation = true;
        }
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void showLocations() {
        for(var location : locations) {
            System.out.println(location.getName() + "   " + location.getCity());
        }
        System.out.println("-----------------------------------");
        for(var reservation : reservations) {
            System.out.println(reservation.getLocation().getName() + "   " + reservation.getEvent().getName());
        }
    }

    public void addSomeData() {
        Location l1 = new MusicHallLocation("Trr", "Brasov", "RO", 100);
        Location l2 = new PubLocation("Beau", "Arad", "RO", 100, 50, 2, false, true);
        Location l3 = new RestaurantLocation("Fita", "Oradea", "RO", 200, 100, 2, true, 300);
        Location l4 = new PoolLocation("Rat", "Slatina", "RO", 200, true, false, false);
        locations.add(l1);
        locations.add(l2);
        locations.add(l3);
        locations.add(l4);
        Event e1 = new ConcertEvent("BUG Mafia", "01/03/2020", new Guest("BUG Mafia"), 40, 60);
        Event e2 = new PartyEvent("Majorat", "04/10/2020", new Guest("Salam"), new Guest("Mita"));
        Event e3 = new PoolPartyEvent("Night", "15/08/2020", 80);
        Event e4 = new StandUpEvent("Micutzu&CO", "12/12/2020", new Guest("Micutzu"), 60, 100);
        reservations.add(new Reservation(l1, e1));
        reservations.add(new Reservation(l2, e4));
        reservations.add(new Reservation(l3, e2));
        reservations.add(new Reservation(l4, e3));
    }
}
