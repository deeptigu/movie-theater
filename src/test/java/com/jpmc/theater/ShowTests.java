package com.jpmc.theater;

import com.jpmc.discount.handlers.DiscountHandlerChain;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowTests {

    DiscountHandlerChain discountHandlerChain  = new DiscountHandlerChain();

    @Test
    void testTimeInWordsField(){
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Show show = new Show(spiderMan, 1, LocalDateTime.of(2022, 12, 3,10,04));
        show.calculateTimeInWords();
        assertEquals("(1 hour 30 minutes)",show.getTimeInWords());
    }

    @Test
    void checkFirstSeqDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Show show = new Show(spiderMan, 1, LocalDateTime.of(2022, 12, 3,10,04));
        show.setDiscountHandlerChain(discountHandlerChain);
        assertEquals(9.5, show.calculateTicketPrice(1));
    }


    @Test
    void checkSecondSeqDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Show show = new Show(spiderMan, 2, LocalDateTime.of(2022, 12, 3,10,04));
        show.setDiscountHandlerChain(discountHandlerChain);
        assertEquals(10.5, show.calculateTicketPrice(1));
    }

    @Test
    void checkTimeDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Show show = new Show(spiderMan, 5, LocalDateTime.of(2022, 12, 3,11,04));
        show.setDiscountHandlerChain(discountHandlerChain);
        assertEquals(9.375, show.calculateTicketPrice(1));
    }

    @Test
    void check7thDayDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Show show = new Show(spiderMan, 5, LocalDateTime.of(2022, 12, 7,10,04));
        show.setDiscountHandlerChain(discountHandlerChain);
        assertEquals(11.5, show.calculateTicketPrice(1));
    }

    @Test
    void specialMovieDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Show show = new Show(spiderMan, 5, LocalDateTime.of(2022, 12, 3,10,04));
        show.setDiscountHandlerChain(discountHandlerChain);
        assertEquals(10, show.calculateTicketPrice(1));
    }

    @Test
    void specialPlus2ndSeqMovieDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Show show = new Show(spiderMan, 2, LocalDateTime.of(2022, 12, 3,10,04));
        show.setDiscountHandlerChain(discountHandlerChain);
        assertEquals(10, show.calculateTicketPrice(1));
    }

    @Test
    void specialPlus1stSeqMovieDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Show show = new Show(spiderMan, 1, LocalDateTime.of(2022, 12, 3,10,04));
        show.setDiscountHandlerChain(discountHandlerChain);
        assertEquals(9.5, show.calculateTicketPrice(1));
    }

    @Test
    void specialPlus7thDayMovieDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Show show = new Show(spiderMan, 1, LocalDateTime.of(2022, 12, 7,10,04));
        show.setDiscountHandlerChain(discountHandlerChain);
        assertEquals(9.5, show.calculateTicketPrice(1));
    }

    @Test
    void specialPlusTimeMovieDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Show show = new Show(spiderMan, 1, LocalDateTime.of(2022, 12, 3,10,04));
        show.setDiscountHandlerChain(discountHandlerChain);
        assertEquals(9.5, show.calculateTicketPrice(1));
    }

    ////// Possibilities with 1st Seq

    @Test
    void firstSeqPlus7thDayMovieDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Show show = new Show(spiderMan, 1, LocalDateTime.of(2022, 12, 7,10,04));
        show.setDiscountHandlerChain(discountHandlerChain);
        assertEquals(9.5, show.calculateTicketPrice(1));
    }

    @Test
    void firstSeqPlusTimeMovieDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 0);
        Show show = new Show(spiderMan, 1, LocalDateTime.of(2022, 12, 3,12,04));
        show.setDiscountHandlerChain(discountHandlerChain);
        assertEquals(7, show.calculateTicketPrice(1));
    }

    ////combination possibilities with 2nd seq

    @Test
    void secondSeqPlus7thDayMovieDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Show show = new Show(spiderMan, 2, LocalDateTime.of(2022, 12, 7,10,04));
        show.setDiscountHandlerChain(discountHandlerChain);
        assertEquals(10.5, show.calculateTicketPrice(1));
    }

    @Test
    void secondSeqPlusTimeMovieDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 0);
        Show show = new Show(spiderMan, 2, LocalDateTime.of(2022, 12, 3,12,04));
        show.setDiscountHandlerChain(discountHandlerChain);
        assertEquals(7.5, show.calculateTicketPrice(1));
    }

    @Test
    void seventhDayPlusTimeMovieDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 0);
        Show show = new Show(spiderMan, 2, LocalDateTime.of(2022, 12, 7,13,04));
        show.setDiscountHandlerChain(discountHandlerChain);
        assertEquals(7.5, show.calculateTicketPrice(1));
    }

    @Test
    void seqOnePlusSeventhDayPlusTimeMovieDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 0);
        Show show = new Show(spiderMan, 1, LocalDateTime.of(2022, 12, 7,13,04));
        show.setDiscountHandlerChain(discountHandlerChain);
        assertEquals(7.0, show.calculateTicketPrice(1));
    }

    // Similarly we can add the fllowing combinations : seqOne+7thDay+Time , seqOne+7thDay+Special,
                                //similarly with seq2: seq2+7thDay+Time , seq2+7thDay+Special, seq2PlusSeventhDayPlusTime
                                // combinations of 4 : seq2+7thDay+Time+Sepatial , seq1+7thDay+Special+time

    // ALso we can add Null and empty test cases for variots fields

}
