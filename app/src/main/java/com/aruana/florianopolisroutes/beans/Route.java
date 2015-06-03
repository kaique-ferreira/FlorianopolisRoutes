package com.aruana.florianopolisroutes.beans;

import java.io.Serializable;

/**
 * Created by aruana on 31/05/15.
 */
public class Route implements Serializable {

    private Integer id;
    private Integer shortName;
    private String longName;
    private String lastModifiedDate;
    private Integer agencyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShortName() {
        return shortName;
    }

    public void setShortName(Integer shortName) {
        this.shortName = shortName;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}
