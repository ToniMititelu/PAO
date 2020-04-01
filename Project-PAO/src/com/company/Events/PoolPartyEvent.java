package com.company.Events;

public class PoolPartyEvent extends Event {
    private int pricePerTicket;

    public PoolPartyEvent(String name, String date, int pricePerTicket) {
        super(name, date);
        this.pricePerTicket = pricePerTicket;
    }

    public int getPricePerTicket() {
        return pricePerTicket;
    }

    public void setPricePerTicket(int pricePerTicket) {
        this.pricePerTicket = pricePerTicket;
    }

    @Override
    public String toString() {
        return "PoolPartyEvent{" +
                "pricePerTicket=" + pricePerTicket +
                '}';
    }
}
