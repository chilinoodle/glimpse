package com.mjdroid.glimpse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ActivityAdapter extends ArrayAdapter <PlanActivity> {

    public ActivityAdapter(Context context, ArrayList<PlanActivity> activities) {
        super(context,0,activities);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item, parent, false);
        }

        PlanActivity selectedActivity = getItem(position);

        ImageView activityIcon = (ImageView) listItemView.findViewById(R.id.activity_icon);
        activityIcon.setImageResource(selectedActivity.getImageResource());

        TextView activityName = (TextView) listItemView.findViewById(R.id.activity_name);
        activityName.setText(selectedActivity.getLongText());

        TextView activityCompany = (TextView) listItemView.findViewById(R.id.with_name);
        activityCompany.setText(selectedActivity.getContact());

        TextView activityDate = (TextView) listItemView.findViewById(R.id.date);
        activityDate.setText(selectedActivity.getDate());

        return listItemView;
    }

}
