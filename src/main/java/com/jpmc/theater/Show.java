package com.jpmc.theater;

import com.google.gson.annotations.Expose;
import com.jpmc.discount.handlers.DiscountHandlerChain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Show {
    private Movie movie;
    @Expose
    private int sequenceOfTheDay;
    @Expose
    private LocalDateTime showStartTime;
    @Expose
    private double fee;
    @Expose
    private String timeInWords;

    private DiscountHandlerChain discountHandlerChain;

    public Show(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }


    public double calculateTicketPrice(int audienceCount) {
        fee = (movie.getTicketPrice() - discountHandlerChain.calculateDiscount(this))* audienceCount;
        return fee;
    }

    public double getFee() {
        return fee;
    }

    public String getTimeInWords() {
        return timeInWords;
    }


    public void calculateTimeInWords() {
        Duration duration = this.movie.getRunningTime();
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        timeInWords = String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));

    }

    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

    public void setDiscountHandlerChain(DiscountHandlerChain discountHandlerChain) {
        this.discountHandlerChain = discountHandlerChain;
    }
}
