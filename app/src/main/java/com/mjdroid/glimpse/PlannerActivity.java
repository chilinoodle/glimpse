package com.mjdroid.glimpse;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlannerActivity extends AppCompatActivity {

    public TextView dateChooser;
    public Spinner spinner;
    public TextView fromHour;
    public TextView toHour;
    public TextView clickedTimeView;
    public TextView withName;
    public TextView withNumber;
    public TextView withWith;
    private static final int RESULT_PICK_CONTACT = 666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);

        spinner = (Spinner) findViewById(R.id.activities_spinner);
        dateChooser = (TextView) findViewById(R.id.date);
        fromHour = (TextView) findViewById(R.id.from_hour);
        toHour = (TextView) findViewById(R.id.to_hour);
        withName = (TextView) findViewById(R.id.with_name);
        withNumber = (TextView) findViewById(R.id.with_number);
        withWith = (TextView) findViewById(R.id.with_with);

        /*TODO:activate this after creating a new Activity Class if needed
        ArrayList<String> activitiesList = new ArrayList<>();
        activitiesList.add(getString(R.string.choose_activity));
        activitiesList.add("Take a walk");
        activitiesList.add("Go for a run");
        activitiesList.add("Go take a hike");
        activitiesList.add("Learn coding");
        activitiesList.add("Watch TV");
        activitiesList.add("Go to cinema");
        activitiesList.add("Go shopping");
        activitiesList.add("Go to lunch");
        activitiesList.add("Go to dinner");
        activitiesList.add("Read a book");
        activitiesList.add("Visit someone");
        activitiesList.add("Do absolutely nothing");
        */

        //Gets items from resource file activities_list.xml
        String[] activity = getResources().getStringArray(R.array.activities_list);

        //Creating adapter for the spinner
        ArrayAdapter<String> activityAdapter = new ArrayAdapter<String>(this, R.layout.city_spinner_item, activity){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner, first item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };

        //Dropdown layout style
        activityAdapter.setDropDownViewResource(R.layout.city_spinner_dropdown_item);

        //spinner click listener
        spinner.setOnItemSelectedListener(new SpinnerActivity());

        //Attaching data to adapter
        spinner.setAdapter(activityAdapter);


        dateChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PlannerActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        fromHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedTimeView = (TextView) v;
                new TimePickerDialog(PlannerActivity.this, time, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),true).show();
            }
        });

        toHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedTimeView = (TextView) v;
                new TimePickerDialog(PlannerActivity.this, time, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),true).show();
            }
        });

        withWith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
            }
        });

    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (pos > 0) {
                String selectedActivity = (String) parent.getItemAtPosition(pos);
                Toast.makeText(parent.getContext(), "You've selected to " + selectedActivity, Toast.LENGTH_SHORT).show();
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    Calendar calendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateView(dateChooser);
        }
    };

    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            updateTimeView(clickedTimeView);
        }
    };

    private void updateDateView(TextView textView) {
        String dateFormat = "EE, d MMMM";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        textView.setText(sdf.format(calendar.getTime()));
    }

    private void updateTimeView(TextView textView) {
        String dateFormat = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        textView.setText(sdf.format(calendar.getTime()));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check whether the result is ok
        if (resultCode == RESULT_OK) {
            // Check for the request code, we might be using multiple startActivityForResult
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Toast.makeText(PlannerActivity.this, "Unable to pick Contact",Toast.LENGTH_SHORT);
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null ;
            String name = null;
            // getData() method will have the Content Uri of the selected contact
            Uri uri = data.getData();
            //Query the content uri
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            // column index of the phone number
            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            // column index of the contact name
            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);
            // Set the value to the text views
            withName.setText(name);
            withNumber.setText(phoneNo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        cursor.close();
    }

}
