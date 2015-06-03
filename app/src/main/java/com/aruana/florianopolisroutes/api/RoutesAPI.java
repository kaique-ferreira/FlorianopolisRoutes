package com.aruana.florianopolisroutes.api;

import android.content.Context;

import com.aruana.florianopolisroutes.R;
import com.aruana.florianopolisroutes.beans.Departure;
import com.aruana.florianopolisroutes.beans.Route;
import com.aruana.florianopolisroutes.beans.Stop;
import com.aruana.florianopolisroutes.enums.CalendarEnum;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aruana on 01/06/15.
 */
public class RoutesAPI {

    private Gson gson = new Gson();
    private RouteByNameCallback callback;
    private Context context;

    private String hostUrl;
    private String routesService;
    private String departureService;
    private String stopService;
    private String userPassword;

    public RoutesAPI(Context context) {
         this.context = context;
         this.hostUrl = context.getResources().getString(R.string.HOST_URL);
         this.routesService = context.getResources().getString(R.string.ROUTES_BY_STOP);
         this.departureService = context.getResources().getString(R.string.DEPARTURE_BY_ID);
         this.stopService = context.getResources().getString(R.string.STOP_BY_ID);
         this.userPassword = context.getResources().getString(R.string.USER_PASSWORD);
    }

    public void findRoutes(final String stopName, final RouteByNameCallback callback) {
        JsonObject jo = new JsonObject();
        jo.addProperty("stopName", "%"+stopName+"%");

        JsonObject params = new JsonObject();
        params.add("params", jo);

        FutureCallback<JsonObject> futureCallback = new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                Type collectionType = new TypeToken<List<Route>>(){}.getType();
                List<Route> routesResult = gson.fromJson(result.get("rows"),collectionType);
                callback.routeStopNameCallback(routesResult);
            }
        };

        doPost(params,futureCallback,routesService);
    }

    public void findDepartures(final Integer routeId, final DepartureCallback callback, final CalendarEnum calendar) {
        JsonObject jo = new JsonObject();
        jo.addProperty("routeId",routeId);

        JsonObject params = new JsonObject();
        params.add("params", jo);

        FutureCallback<JsonObject> futureCallback = new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                Type collectionType = new TypeToken<List<Departure>>(){}.getType();
                List<Departure> departures = gson.fromJson(result.get("rows"),collectionType);

                List<Departure> departuresCalendar = new ArrayList<Departure>();
                for (Departure departure : departures) {
                    if (departure.getCalendar() == calendar) {
                        departuresCalendar.add(departure);
                    }
                }

                callback.routeDepartureCallback(departuresCalendar);
            }
        };

        doPost(params,futureCallback,departureService);
    }

    public void findStops(final Integer routeId, final StopCallback callback) {
        JsonObject jo = new JsonObject();
        jo.addProperty("routeId",routeId);

        JsonObject params = new JsonObject();
        params.add("params", jo);

        FutureCallback<JsonObject> futureCallback = new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                Type collectionType = new TypeToken<List<Stop>>(){}.getType();
                List<Stop> stops = gson.fromJson(result.get("rows"),collectionType);
                callback.stopCallback(stops);
            }
        };

        doPost(params,futureCallback,stopService);
    }

    private void doPost(JsonObject params,FutureCallback<JsonObject> futureCallback, String serviceUrl) {
        Ion.with(context)
                .load(hostUrl+serviceUrl)
                .setHeader("Authorization","Basic "+userPassword)
                .setHeader("X-AppGlu-Environment","staging")
                .setHeader("Content-Type","application/json")
                .setJsonObjectBody(params)
                .asJsonObject()
                .setCallback(futureCallback);
    }
}
