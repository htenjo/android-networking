package com.example.android.quakereport.model;

import java.util.Date;

/**
 * Created by htenjo on 8/10/16.
 */
public class EarthQuake {
    private String place;
    private double magnitude;
    private Date time;
    private String url;

    public EarthQuake(String place, double magnitude, Date time) {
        this.place = place;
        this.magnitude = magnitude;
        this.time = time;
    }

    public EarthQuake(String place, double magnitude, Date time, String url) {
        this(place, magnitude, time);
        this.url = url;
    }

    public String getPlace() {
        return place;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public Date getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }
}
