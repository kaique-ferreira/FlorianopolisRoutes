package com.aruana.florianopolisroutes.api;


import com.aruana.florianopolisroutes.beans.Stop;

import java.util.List;

/**
 * Created by aruana on 02/06/15.
 */
public interface StopCallback {

    public void stopCallback(List<Stop> stops);
}
