package com.jpmc.discount.handlers;

import com.jpmc.theater.Show;

public class TimeDiscountHandler  implements IDiscountHandler{

    private IDiscountHandler iDiscountHandler;

    @Override
    public void setNextDiscountHandler(IDiscountHandler nextDiscountHandler) {
        this.iDiscountHandler = nextDiscountHandler;
    }

    @Override
    public double getDiscountAmt(Show show) {
        double timeDiscount = 0;
        int time = show.getStartTime().getHour();
        if (time >= 11 && time <= 16) {
            timeDiscount = 0.25*show.getMovieFee(); // $3 discount for 1st show
        }
        timeDiscount = Math.max(timeDiscount,iDiscountHandler.getDiscountAmt(show));
        return timeDiscount;
    }
}
