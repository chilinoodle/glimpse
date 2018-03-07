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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class PlannerActivity extends AppCompatActivity {

    public TextView dateChooser;
    public Spinner spinner;
    public TextView fromHour;
    public TextView toHour;
    public TextView clickedTimeView;
    public TextView withName;
    public TextView withNumber;
    public TextView withWith;
    public ImageView clearButton;
    public Button savePlanButton;
    public ImageView activityImage;
    private static final int RESULT_PICK_CONTACT = 666;
    public HashMap<String,String> thisActivityMap = new HashMap<String, String>();
    public PlanActivity selectedActivityObj = new PlanActivity("",R.drawable.eye_green,"",false,"");


    //TODO delete HASHMAP and all .put() if sending an instance of PlanActivity worked
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
        savePlanButton = (Button) findViewById(R.id.save_plan);
        activityImage = (ImageView) findViewById(R.id.main_image);
        clearButton = (ImageView) findViewById(R.id.clear_button);
        clearButton.setVisibility(View.GONE);



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

        savePlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendToMainIntent = new Intent(PlannerActivity.this,MainActivity.class);
                sendToMainIntent.putExtra("sentActivityMap",thisActivityMap);
                sendToMainIntent.putExtra("selectedActivityObj",selectedActivityObj);
                startActivity(sendToMainIntent);
            }
        });
    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

        private int imageInt;

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            if (pos > 0) {
                String selectedActivity = (String) parent.getItemAtPosition(pos);
                Toast.makeText(parent.getContext(), "You've selected to " + selectedActivity, Toast.LENGTH_SHORT).show();
                thisActivityMap.put("selectedActivity", selectedActivity);
                selectedActivityObj.setLongText(selectedActivity);
                switch (selectedActivity) {
                    case "Take a walk":
                        imageInt = R.drawable.ic_directions_walk_black_48dp;
                        activityImage.setImageResource(imageInt);
                        selectedActivityObj.setImageResource(imageInt);
                        break;
                    case "Go for a run":
                        imageInt = R.drawable.ic_directions_run_black_48dp;
                        break;
                    case "Go take a hike":
                        imageInt = R.drawable.ic_directions_walk_black_48dp;
                        break;
                    case "Learn coding":
                        imageInt = R.drawable.ic_code_black_48dp;
                        break;
                    case "Watch TV":
                        imageInt = R.drawable.ic_tv_black_48dp;
                        break;
                    case "Go to cinema":
                        imageInt = R.drawable.ic_local_movies_black_48dp;
                        break;
                    case "Go shopping":
                        imageInt = R.drawable.ic_local_grocery_store_black_48dp;
                        break;
                    case "Go to lunch":
                        imageInt = R.drawable.ic_local_dining_black_48dp;
                        break;
                    case "Go to dinner":
                        imageInt = R.drawable.ic_local_dining_black_48dp;
                        break;
                    case "Read a book":
                        imageInt = R.drawable.ic_local_library_black_48dp;
                        break;
                    case "Visit someone":
                        imageInt = R.drawable.ic_person_pin_black_48dp;
                        break;
                    case "Do absolutely nothing":
                        imageInt = R.drawable.ic_weekend_black_48dp;
                        break;
                    default:
                        imageInt = R.drawable.eye_green;
                }

                selectedActivityObj.setImageResource(imageInt);
                activityImage.setImageResource(imageInt);

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

    //TODO MAKE CHANGE HERE: WHEN A DATE IS NOT UPDATED (BUT LEFT HOW IT WAS BY DEFAULT, OR CANCEL WAS CLICKED) THE HASHMAP WON'T BE UPDATED

    private void updateDateView(TextView textView) {
        String dateFormat = "EE, d MMMM";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        textView.setText(sdf.format(calendar.getTime()));
        thisActivityMap.put("date", sdf.format(calendar.getTime()));
        selectedActivityObj.setDate(sdf.format(calendar.getTime()));
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
            Toast.makeText(PlannerActivity.this, "Unable to pick Contact",Toast.LENGTH_SHORT).show();
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
            clearButton.setVisibility(View.VISIBLE);
            thisActivityMap.put("withName", name);
            thisActivityMap.put("withNumber", phoneNo);
            selectedActivityObj.setHasCompany(true);
            selectedActivityObj.setContact(name);

            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    withName.setText("");
                    withNumber.setText("");
                    clearButton.setVisibility(View.GONE);
                    thisActivityMap.remove("withName");
                    thisActivityMap.remove("withNumber");
                    selectedActivityObj.setHasCompany(false);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        cursor.close();
    }

}