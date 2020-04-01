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

    public Guest getSinger() {
        return singer;
    }

    public void setSinger(Guest singer) {
        this.singer = singer;
    }

    public Guest getPhotographer() {
        return photographer;
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
