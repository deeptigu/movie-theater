package com.jpmc.discount.handlers;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Show;

public class SpecialDiscountHandler implements IDiscountHandler{

    private IDiscountHandler iDiscountHandler;

    private int MOVIE_CODE_SPECIAL = 1; // we can set this field via property file using setters

    @Override
    public void setNextDiscountHandler(IDiscountHandler nextDiscountHandler) {
        this.iDiscountHandler = nextDiscountHandler;
    }

    @Override
    public double getDiscountAmt(Show show) {
        double timeDiscount = 0;
        if (show.getMovie().getSpecialCode() == MOVIE_CODE_SPECIAL ) {
            timeDiscount = 0.2 * show.getMovie().getTicketPrice(); // 20% off discount on special movies
        }
        double temp = 0;
        if(iDiscountHandler != null){
            temp = iDiscountHandler.getDiscountAmt(show);
        }
        timeDiscount = Math.max(timeDiscount,temp);
        return timeDiscount;
    }
}
