package com.company.Events;

import com.company.Guest.Guest;

public class ConcertEvent extends Event {
    private Guest singer;
    private int normalTicketPrice;
    private int goldTicketPrice;
    private int vipTicketPrice;

    public ConcertEvent(String name, String date, Guest singer, int normalTicketPrice, int goldTicketPrice, int vipTicketPrice) {
        super(name, date);
        this.singer = singer;
        this.normalTicketPrice = normalTicketPrice;
        this.goldTicketPrice = goldTicketPrice;
        this.vipTicketPrice = vipTicketPrice;
    }

    public Guest getSinger() {
        return singer;
    }

    public void setSinger(Guest singer) {
        this.singer = singer;
    }

    public int getNormalTicketPrice() {
        return normalTicketPrice;
    }

    public void setNormalTicketPrice(int normalTicketPrice) {
        this.normalTicketPrice = normalTicketPrice;
    }

    public int getGoldTicketPrice() {
        return goldTicketPrice;
    }

    public void setGoldTicketPrice(int goldTicketPrice) {
        this.goldTicketPrice = goldTicketPrice;
    }

    public int getVipTicketPrice() {
        return vipTicketPrice;
    }

    public void setVipTicketPrice(int vipTicketPrice) {
        this.vipTicketPrice = vipTicketPrice;
    }

    @Override
    public String toString() {
        return "ConcertEvent{" +
                "singer=" + singer +
                ", normalTicketPrice=" + normalTicketPrice +
                ", goldTicketPrice=" + goldTicketPrice +
                ", vipTicketPrice=" + vipTicketPrice +
                '}';
    }
}
