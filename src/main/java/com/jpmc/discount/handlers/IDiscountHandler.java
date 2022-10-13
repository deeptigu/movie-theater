package com.jpmc.discount.handlers;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Show;

public interface IDiscountHandler {

    void setNextDiscountHandler(IDiscountHandler nextDiscountHandler);

    double getDiscountAmt(Show show);
}
