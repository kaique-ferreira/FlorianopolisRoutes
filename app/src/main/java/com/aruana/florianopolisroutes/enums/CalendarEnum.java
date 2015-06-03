package com.aruana.florianopolisroutes.enums;

import java.io.Serializable;

/**
 * Created by aruana on 01/06/15.
 */
public enum CalendarEnum implements Serializable{

    WEEKDAY ("WEEKDAY"),
    SATURDAY ("SATURDAY"),
    SUNDAY ("SUNDAY");

    private final String value;
    public String getValue() {
        return value;
    }

    private CalendarEnum(String value) {
        this.value = value;
    }
}
