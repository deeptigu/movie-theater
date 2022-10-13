package com.jpmc.theater;

import com.jpmc.discount.handlers.DiscountHandlerChain;
import com.jpmc.output.IOutputFormat;
import com.jpmc.output.JsonOutput;
import com.jpmc.output.TextOutput;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Theater {

    LocalDateProvider provider;
    private List<Show> schedule;

    public Theater(LocalDateProvider provider) {
        this.provider = provider;
    }

    void createSchedule(){
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        schedule = List.of(
                new Show(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
                new Show(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
                new Show(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
                new Show(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
                new Show(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
                new Show(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
                new Show(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
                new Show(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
                new Show(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
        );
    }

    public static void main(String[] args) {
        LocalDateProvider provider = LocalDateProvider.singleton();
        Theater theater = new Theater(provider);
        theater.createSchedule();
        DiscountHandlerChain discountHandlerChain = new DiscountHandlerChain();
        theater.schedule.forEach(s -> {
            s.setDiscountHandlerChain(discountHandlerChain);
            s.calculateTimeInWords();
            s.calculateTicketPrice(1);
        });
        theater.printScheduleInText();
        theater.printScheduleInJson();
    }

    public void printScheduleInText(){
        IOutputFormat textOutput = new TextOutput(provider);
        textOutput.printSchedule(this.schedule);
    }
    public void printScheduleInJson() {
        IOutputFormat textOutput = new JsonOutput(provider);
        textOutput.printSchedule(this.schedule);
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Show show;
        try {
            show = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, show, howManyTickets);
    }

    public List<Show> getSchedule() {
        return schedule;
    }
}
