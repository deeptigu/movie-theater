package com.jpmc.theater;

public class Reservation {
    private Customer customer;
    private Show show;
    private int audienceCount;

    public Reservation(Customer customer, Show show, int audienceCount) {
        this.customer = customer;
        this.show = show;
        this.audienceCount = audienceCount;
    }

    public double totalFee() {
        return show.getMovieFee() * audienceCount;
    }
}