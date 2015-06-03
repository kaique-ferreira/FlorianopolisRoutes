package com.aruana.florianopolisroutes.api;

import com.aruana.florianopolisroutes.beans.Route;

import java.util.List;

/**
 * Created by aruana on 01/06/15.
 */
public interface RouteByNameCallback {

    public void routeStopNameCallback(List<Route> routesResult);

}
