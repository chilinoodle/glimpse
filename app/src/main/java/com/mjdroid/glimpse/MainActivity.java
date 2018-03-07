package com.mjdroid.glimpse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String city = bundle.getString("selectedCity");

        TextView cityView = (TextView) findViewById(R.id.city_view);

        cityView.setText(city);

        TextView addButtonView = (TextView) findViewById(R.id.add_button);

        addButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPlanner = new Intent(MainActivity.this, PlannerActivity.class);
                startActivity(goToPlanner);
            }
        });

        TextView activitySelectedView = (TextView) findViewById(R.id.activity_selected);
        TextView activityContactView = (TextView) findViewById(R.id.activity_contact);
        TextView activityContactNumberView = (TextView) findViewById(R.id.activity_contact_number);
        TextView activityDateView = (TextView) findViewById(R.id.activity_date);

        Intent sentActivity = getIntent();

        if (sentActivity.getSerializableExtra("sentActivityMap") != null) {

            HashMap<String, String> sentActivityMap = (HashMap<String, String>) sentActivity.getSerializableExtra("sentActivityMap");
            String selectedActivity = sentActivityMap.get("selectedActivity");
            String contactName = sentActivityMap.get("withName");
            String contactNumber = sentActivityMap.get("withNumber");
            String date = sentActivityMap.get("date");

            activitySelectedView.setText(selectedActivity);
            activityContactView.setText(contactName);
            activityContactNumberView.setText(contactNumber);
            activityDateView.setText(date);

            ArrayList activities = new ArrayList<>();

            PlanActivity selectedActivityObj = (PlanActivity) sentActivity.getSerializableExtra("selectedActivityObj");
            activities.add(selectedActivityObj);
            ActivityAdapter adapter = new ActivityAdapter(this, activities);
            ListView listView = (ListView) findViewById(R.id.list_container);
            listView.setAdapter(adapter);

        }



    }
}
