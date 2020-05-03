package com.company.Events;

import com.company.Guest.Guest;

public class PartyEvent extends Event {
    private Guest singer;
    private Guest photographer;

    public PartyEvent(String name, String date, Guest singer, Guest photographer) {
        super(name, date);
        this.singer = singer;
        this.photographer = photographer;
    }

    public String getSinger() {
        return singer.getName();
    }

    public void setSinger(Guest singer) {
        this.singer = singer;
    }

    public String getPhotographer() {
        return photographer.getName();
    }

    public void setPhotographer(Guest photographer) {
        this.photographer = photographer;
    }

    @Override
    public String toString() {
        return "PartyEvent{" +
                "singer=" + singer +
                ", photographer=" + photographer +
                '}';
    }
}
