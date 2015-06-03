package com.aruana.florianopolisroutes.beans;

import java.io.Serializable;

/**
 * Created by aruana on 02/06/15.
 */
public class Stop implements Serializable{

    private Integer id;
    private String name;
    private Integer sequence;

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
