package com.aruana.florianopolisroutes.api;

import com.aruana.florianopolisroutes.beans.Departure;

import java.util.List;

/**
 * Created by aruana on 02/06/15.
 */
public interface DepartureCallback {

    public void routeDepartureCallback(List<Departure> departures);
}
