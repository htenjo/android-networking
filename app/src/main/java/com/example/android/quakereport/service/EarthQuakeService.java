package com.example.android.quakereport.service;

import com.example.android.quakereport.model.EarthQuakePojo;
import com.example.android.quakereport.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by htenjo on 8/12/16.
 */
public interface EarthQuakeService {
    /**
     * Retrieve all events generated between the given dates
     * @param startDate Must be in the format yyyy-MM-dd
     * @param endDate Must be in the format yyyy-MM-dd
     * @param minMagnitude filter the results by the minimum magnitud of the earthquakes
     * @return All the earthQuakes generated between the dates
     */
    @GET(Constants.EARTHQUAKE_SERVICE_LIST_URL)
    Call<EarthQuakePojo> listEarthQuakes(
            @Query("starttime") String startDate,
            @Query("endtime") String endDate,
            @Query("minmagnitude") int minMagnitude);
}
