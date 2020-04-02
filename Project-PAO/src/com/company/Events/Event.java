package com.company.Events;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Event {
    private String name;
    private String date;

    public Event(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public Date getRealDate() throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(this.date);
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
