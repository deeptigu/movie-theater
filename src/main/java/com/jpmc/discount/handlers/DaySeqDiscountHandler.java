package com.jpmc.discount.handlers;

import com.jpmc.theater.Show;

public class DaySeqDiscountHandler implements IDiscountHandler{

    private IDiscountHandler iDiscountHandler;

    @Override
    public void setNextDiscountHandler(IDiscountHandler nextDiscountHandler) {
        this.iDiscountHandler = nextDiscountHandler;
    }

    @Override
    public double getDiscountAmt(Show show) {
        double sequenceDiscount = 0;
        int showSequence = show.getSequenceOfTheDay();
        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showSequence == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        }
        sequenceDiscount = Math.max(sequenceDiscount,iDiscountHandler.getDiscountAmt(show));
        return sequenceDiscount;
    }
}
