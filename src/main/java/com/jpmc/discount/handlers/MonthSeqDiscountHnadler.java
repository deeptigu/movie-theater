package com.jpmc.discount.handlers;

import com.jpmc.theater.Show;

public class MonthSeqDiscountHnadler implements IDiscountHandler{

    private IDiscountHandler iDiscountHandler;

    @Override
    public void setNextDiscountHandler(IDiscountHandler nextDiscountHandler) {
        this.iDiscountHandler = nextDiscountHandler;
    }

    @Override
    public double getDiscountAmt(Show show) {
        double dayDiscount = 0;
        int showSequence = show.getStartTime().getDayOfMonth();
        if (showSequence == 7) {
            dayDiscount = 1; // $1 discount for 7th day of the month show
        }
        dayDiscount = Math.max(dayDiscount,iDiscountHandler.getDiscountAmt(show));
        return dayDiscount;
    }
}
