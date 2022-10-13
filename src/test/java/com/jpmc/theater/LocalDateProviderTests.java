package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentTime() {
        LocalDate ld = LocalDateProvider.singleton().currentDate();
        assertEquals(ld,LocalDateProvider.singleton().currentDate());
       // System.out.println("current time is - " + LocalDateProvider.singleton().currentDate());
    }
}
