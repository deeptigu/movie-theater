package com.jpmc.theater;

import com.jpmc.output.IOutputFormat;
import com.jpmc.output.TextOutput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {
    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
//        System.out.println("You have to pay " + reservation.getTotalFee());
        assertEquals(reservation.totalFee(), 50);
    }

    @Test
    void printMovieSchedule() {
        LocalDateProvider provider = LocalDateProvider.singleton();
        Theater theater = new Theater(provider);

        IOutputFormat textOutput = new TextOutput(provider);
        textOutput.printSchedule(theater.getSchedule());
    }
}
