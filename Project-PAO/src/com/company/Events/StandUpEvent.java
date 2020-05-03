package com.company.Events;

import com.company.Guest.Guest;

public class StandUpEvent extends Event {
    private Guest comedian;
    private int pricePerSeatNormal;
    private int pricePerSeatVIP;

    public StandUpEvent(String name, String date, Guest comedian, int pricePerSeatNormal, int pricePerSeatVIP) {
        super(name, date);
        this.comedian = comedian;
        this.pricePerSeatNormal = pricePerSeatNormal;
        this.pricePerSeatVIP = pricePerSeatVIP;
    }

    public String getComedian() {
        return comedian.getName();
    }

    public void setComedian(Guest comedian) {
        this.comedian = comedian;
    }

    public int getPricePerSeatNormal() {
        return pricePerSeatNormal;
    }

    public void setPricePerSeatNormal(int pricePerSeatNormal) {
        this.pricePerSeatNormal = pricePerSeatNormal;
    }

    public int getPricePerSeatVIP() {
        return pricePerSeatVIP;
    }

    public void setPricePerSeatVIP(int pricePerSeatVIP) {
        this.pricePerSeatVIP = pricePerSeatVIP;
    }

    @Override
    public String toString() {
        return "StandUpEvent{" +
                "comedian=" + comedian +
                ", pricePerSeatNormal=" + pricePerSeatNormal +
                ", pricePerSeatVIP=" + pricePerSeatVIP +
                '}';
    }
}
