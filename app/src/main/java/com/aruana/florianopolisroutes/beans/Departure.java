package com.aruana.florianopolisroutes.beans;

import com.aruana.florianopolisroutes.enums.CalendarEnum;

import java.io.Serializable;

/**
 * Created by aruana on 01/06/15.
 */
public class Departure implements Serializable {

    private Integer id;
    private CalendarEnum calendar;
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CalendarEnum getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarEnum calendar) {
        this.calendar = calendar;
    }

}
