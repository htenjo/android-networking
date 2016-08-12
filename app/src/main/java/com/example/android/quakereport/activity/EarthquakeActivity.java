/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.quakereport.R;
import com.example.android.quakereport.layout.EarthQuakeAdapter;
import com.example.android.quakereport.model.EarthQuake;
import com.example.android.quakereport.model.EarthQuakePojo;
import com.example.android.quakereport.service.EarthQuakeService;
import com.example.android.quakereport.service.NetworkFactory;
import com.example.android.quakereport.utils.Constants;
import com.example.android.quakereport.utils.QueryUtils;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EarthquakeActivity extends BaseActivity {
    private EarthQuakeService service;
    private List<EarthQuake> earthquakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        configureService();

        // Create a fake list of earthquake locations.
        //earthquakes  = QueryUtils.extractEarthquakes();
        retrieveEarthQuakes();
    }

    private void configureService(){
        NetworkFactory serviceFactory = NetworkFactory.getInstance();
        String baseUrl = Constants.EARTHQUAKE_SERVICE_BASE_URL;
        service = serviceFactory.buildService(EarthQuakeService.class, baseUrl);
    }

    private void retrieveEarthQuakes(){
        Call<EarthQuakePojo> call = service.listEarthQuakes("2016-07-01", "2016-07-02", 2);

        call.enqueue(new Callback<EarthQuakePojo>() {
            @Override
            public void onResponse(Call<EarthQuakePojo> call, Response<EarthQuakePojo> response) {
                if(response.isSuccessful()){
                    earthquakes = QueryUtils.extractEarthquakes(response.body());

                    if(earthquakes != null){
                        // Find a reference to the {@link ListView} in the layout
                        ListView earthquakeListView = (ListView) findViewById(R.id.list);

                        // Create a new {@link ArrayAdapter} of earthquakes
                        EarthQuakeAdapter adapter = new EarthQuakeAdapter(EarthquakeActivity.this, earthquakes);

                        if(earthquakeListView != null) {
                            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Uri url = Uri.parse(earthquakes.get(position).getUrl());
                                    Intent i = new Intent(Intent.ACTION_VIEW, url);
                                    startActivity(i);
                                }
                            });
                        }

                        earthquakeListView.setAdapter(adapter);
                    }
                }else{
                    Log.e(getLogTag(), "Error in the request: Error " + response.code());
                    Log.e(getLogTag(), "Error Message ----> " + response.message());

                    try {
                        Log.e(getLogTag(), "Error Message ----> " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<EarthQuakePojo> call, Throwable t) {
                Log.e(getLogTag(), "Error calling the server: " + t.getMessage());
            }
        });
    }
}
