package com.company.Reservations;

import com.company.Events.*;
import com.company.Guest.Guest;
import com.company.Locations.Location;
import com.company.Services.GenericCsv.CsvSerializable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Reservation implements Comparable<Reservation>, CsvSerializable {
    private Location location;
    private Event event;
    private ArrayList<Location> locations = new ArrayList<>();

    public Reservation() {

    }

    public Reservation(Location location, Event event) {
        this.location = location;
        this.event = event;
    }

    public Reservation(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getEventDate() throws ParseException {
        return this.event.getRealDate();
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "location=" + location +
                ", event=" + event +
                '}';
    }


    @Override
    public int compareTo(Reservation o) {
        try {
            if(this.getEventDate().before(o.getEventDate())) {
                return -1;
            } else if(this.getEventDate().after(o.getEventDate())) {
                return 1;
            } else if(this.getLocation().getName().compareTo(o.getLocation().getName()) < 0) {
                return -1;
            } else if(this.getLocation().getName().compareTo(o.getLocation().getName()) > 0) {
                return 1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String[] getColumnNames() {
        return new String[]{"locationName", "eventType", "eventName", "eventDate", "guest1", "guest2", "normalTicketPrice", "vipTicketPrice"};
    }

    @Override
    public String[] toStringArray() {
        if(event instanceof ConcertEvent) {
            return new String[] {
                    location.getName(), "Concert", event.getName(),
                    event.getDate(), ((ConcertEvent) event).getSinger(),
                    "-", Integer.toString(((ConcertEvent) event).getNormalTicketPrice()),
                    Integer.toString(((ConcertEvent) event).getVipTicketPrice())
            };
        } else if(event instanceof PartyEvent) {
            return new String[] {
                    location.getName(), "Party", event.getName(),
                    event.getDate(), ((PartyEvent) event).getSinger(),
                    ((PartyEvent) event).getPhotographer(), "-", "-"
            };
        } else if(event instanceof PoolPartyEvent) {
            return new String[] {
                    location.getName(), "Pool", event.getName(),
                    event.getDate(), "-", "-", Integer.toString(((PoolPartyEvent) event).getPricePerTicket()), "-"
            };
        } else if(event instanceof StandUpEvent) {
            return new String[] {
                    location.getName(), "StandUp", event.getName(),
                    event.getDate(), ((StandUpEvent) event).getComedian(), "-",
                    Integer.toString(((StandUpEvent) event).getPricePerSeatNormal()),
                    Integer.toString(((StandUpEvent) event).getPricePerSeatVIP())
            };
        }
        return new String[] {
                location.getName(), "Other", event.getName(), event.getDate()
        };
    }

    public Location findLocationByName(String name) {
        for(var location : locations) {
            if(location.getName().equals(name)) {
                return location;
            }
        }
        return null;
    }

    @Override
    public void fromStringArray(String[] reservation) {
        setLocation(findLocationByName(reservation[0]));
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
    }
}
