package com.example.android.quakereport.utils;

/**
 * Created by htenjo on 8/12/16.
 */
public class Constants {
    public static final String DEFAULT_DATE_FORMAT = "MMM dd, yyyy";
    public static final String DEFAULT_TIME_FORMAT = "hh:mm a";

    public static final String EARTHQUAKE_SERVICE_BASE_URL = "http://earthquake.usgs.gov";
    public static final String EARTHQUAKE_SERVICE_LIST_URL = "/fdsnws/event/1/query?format=geojson";
    public static final String EARTHQUAKE_SERVICE_DATE_FORMAT = "yyyy-MM-dd";
}
