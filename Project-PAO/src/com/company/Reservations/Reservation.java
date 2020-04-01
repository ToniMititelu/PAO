package com.company.Reservations;

import com.company.Events.Event;
import com.company.Locations.Location;

import java.text.ParseException;
import java.util.Date;

public class Reservation implements Comparable<Reservation> {
    private Location location;
    private Event event;

    public Reservation(Location location, Event event) {
        this.location = location;
        this.event = event;
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
}
