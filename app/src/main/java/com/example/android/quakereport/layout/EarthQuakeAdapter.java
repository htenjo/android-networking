package com.example.android.quakereport.layout;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.R;
import com.example.android.quakereport.model.EarthQuake;
import com.example.android.quakereport.utils.Constants;
import com.example.android.quakereport.utils.FormatUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by htenjo on 8/10/16.
 */
public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake>{

    public EarthQuakeAdapter(Context context, List<EarthQuake> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_item, parent, false);
        }

        EarthQuake earthQuake = getItem(position);
        TextView magnitudeText = (TextView)listItemView.findViewById(R.id.text_magnitude);
        magnitudeText.setText(FormatUtils.formatDouble(earthQuake.getMagnitude(), 1, 1));
        GradientDrawable circle = (GradientDrawable)magnitudeText.getBackground();
        circle.setColor(getMagnitudColor(earthQuake.getMagnitude()));

        String place = earthQuake.getPlace();
        String[] placeParts = StringUtils.splitByWholeSeparator(place, "of");

        TextView placeGeoText = (TextView)listItemView.findViewById(R.id.text_place_geo);
        placeGeoText.setText(placeParts.length < 2 ? "Near to" : placeParts[0].trim());

        TextView placeCityText = (TextView)listItemView.findViewById(R.id.text_place_city);
        placeCityText.setText(placeParts.length < 2 ? placeParts[0].trim() : placeParts[1].trim());

        TextView dateText = (TextView)listItemView.findViewById(R.id.text_date);
        dateText.setText(FormatUtils.formatDate(earthQuake.getTime(), Constants.DEFAULT_DATE_FORMAT));

        TextView timeText = (TextView)listItemView.findViewById(R.id.text_time);
        timeText.setText(FormatUtils.formatDate(earthQuake.getTime(), Constants.DEFAULT_TIME_FORMAT));
        return listItemView;
    }

    private int getMagnitudColor(double magnitud){
        int magnitudeFloor = (int) Math.floor(magnitud);
        int magnitudeColorResourceId;

        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
