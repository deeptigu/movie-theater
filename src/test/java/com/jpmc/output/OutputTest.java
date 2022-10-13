package com.jpmc.output;

import com.jpmc.discount.handlers.DiscountHandlerChain;
import com.jpmc.theater.LocalDateProvider;
import com.jpmc.theater.Movie;
import com.jpmc.theater.Show;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class OutputTest {

    LocalDateProvider provider = LocalDateProvider.singleton();
    DiscountHandlerChain discountHandlerChain = new DiscountHandlerChain();
    List<Show> schedule;

    @Test
    void testTextOutput(){
        IOutputFormat textOutput = new TextOutput(provider);
        createSchedule();
        schedule.forEach(s -> {
            s.setDiscountHandlerChain(discountHandlerChain);
            s.calculateTimeInWords();
            s.calculateTicketPrice(1);
        });
        textOutput.printSchedule(this.schedule);
    }

    @Test
    void testJsonOutput(){
        IOutputFormat output = new JsonOutput(provider);
        createSchedule();
        schedule.forEach(s -> {
            s.setDiscountHandlerChain(discountHandlerChain);
            s.calculateTimeInWords();
            s.calculateTicketPrice(1);
        });
        output.printSchedule(this.schedule);
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
}
