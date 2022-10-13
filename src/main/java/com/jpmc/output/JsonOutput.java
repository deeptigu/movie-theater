package com.jpmc.output;

import com.google.gson.Gson;
import com.jpmc.theater.LocalDateProvider;
import com.jpmc.theater.Show;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class JsonOutput implements IOutputFormat{

    private LocalDateProvider provider;

    private Gson gson;

    public JsonOutput(LocalDateProvider provider){
        this.provider = provider;
        this.gson = new Gson();
    }

    @Override
    public void printSchedule(List<Show> schedule) {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(s -> {
            JsonShowObj obj = new JsonShowObj(s.getSequenceOfTheDay(),s.getStartTime(),s.getMovie().getTitle(),s.getTimeInWords(),s.getFee());
            System.out.println(gson.toJson(obj));
        });
        System.out.println("===================================================");
    }

    class JsonShowObj{
        int seqOfDay;
        String startTime;
        String title;
        String timeInWords;
        double fees;

        public JsonShowObj(int seqOfDay, LocalDateTime startTime, String title, String timeInWords, double fees) {
            this.seqOfDay = seqOfDay;
            this.startTime = startTime.toString();
            this.title = title;
            this.timeInWords = timeInWords;
            this.fees = fees;
        }
    }
}
