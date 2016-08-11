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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.quakereport.R;
import com.example.android.quakereport.layout.EarthQuakeAdapter;
import com.example.android.quakereport.model.EarthQuake;
import com.example.android.quakereport.utils.QueryUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthquakeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        final List<EarthQuake> earthquakes = QueryUtils.extractEarthquakes();

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthQuakeAdapter adapter = new EarthQuakeAdapter(this, earthquakes);

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


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }

    private List<EarthQuake> buildDataSource(){
        Date currentDate = new Date();
        List<EarthQuake> earthQuakesList = new ArrayList<>();
        earthQuakesList.add(new EarthQuake("San Francisco", 7.0, currentDate));
        earthQuakesList.add(new EarthQuake("London", 7.0, currentDate));
        earthQuakesList.add(new EarthQuake("Tokyo", 7.0, currentDate));
        earthQuakesList.add(new EarthQuake("Mexico City", 7.0, currentDate));
        earthQuakesList.add(new EarthQuake("Moscow", 7.0, currentDate));
        earthQuakesList.add(new EarthQuake("Rio de Janeiro", 7.0, currentDate));
        earthQuakesList.add(new EarthQuake("Paris", 7.0, currentDate));
        return earthQuakesList;
    }
}
