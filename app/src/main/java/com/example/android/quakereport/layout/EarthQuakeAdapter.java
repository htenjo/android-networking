package com.example.android.quakereport.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.R;
import com.example.android.quakereport.model.EarthQuake;
import com.example.android.quakereport.utils.Formater;

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
        TextView magnitude = (TextView)listItemView.findViewById(R.id.text_magnitude);
        magnitude.setText(Formater.formatDouble(earthQuake.getMagnitude(), 1, 1));

        TextView place = (TextView)listItemView.findViewById(R.id.text_place);
        place.setText(earthQuake.getPlace());

        TextView time = (TextView)listItemView.findViewById(R.id.text_time);
        time.setText(Formater.formatDate(earthQuake.getTime(), Formater.DEFAULT_DATE_FORMAT));
        return listItemView;
    }
}
