package com.jpmc.output;

import com.jpmc.theater.LocalDateProvider;
import com.jpmc.theater.Show;

import java.util.List;

public interface IOutputFormat {

    public void printSchedule(List<Show> schedule);

}
