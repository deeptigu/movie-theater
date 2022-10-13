package com.jpmc.discount.handlers;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Show;

public class DiscountHandlerChain {

    private IDiscountHandler daySeqDiscountHandler;
    private IDiscountHandler monthSeqHnadler;
    private IDiscountHandler specialDisHandler;
    private IDiscountHandler timeHandler;

    public DiscountHandlerChain(){
        daySeqDiscountHandler = new DaySeqDiscountHandler();
        monthSeqHnadler = new MonthSeqDiscountHnadler();
        specialDisHandler = new SpecialDiscountHandler();
        timeHandler = new TimeDiscountHandler();
        daySeqDiscountHandler.setNextDiscountHandler(monthSeqHnadler);
        monthSeqHnadler.setNextDiscountHandler(timeHandler);
        timeHandler.setNextDiscountHandler(specialDisHandler);
    }

    public double calculateDiscount(Show show){
        return daySeqDiscountHandler.getDiscountAmt(show);
    }

}
