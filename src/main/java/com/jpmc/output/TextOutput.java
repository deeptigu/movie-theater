package com.jpmc.output;

import com.jpmc.theater.LocalDateProvider;
import com.jpmc.theater.Show;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TextOutput implements IOutputFormat{

    private LocalDateProvider provider;

    public TextOutput(LocalDateProvider provider){
        this.provider = provider;
    }

    @Override
    public void printSchedule(List<Show> schedule) {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + s.getTimeInWords()  + s.getFee()+"$")
        );
        System.out.println("===================================================");
    }


}
