package com.mjdroid.glimpse;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText nameField = (EditText) findViewById(R.id.name_field);
        final TextView nameFixed = (TextView) findViewById(R.id.name_fixed);
        final Spinner spinner = (Spinner) findViewById(R.id.city_spinner);
        final TextView selectedCity = (TextView) findViewById(R.id.selected_city);

        Button saveContinue = (Button) findViewById(R.id.save_and_continue);

        saveContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
                String name = nameField.getText().toString();
                if (name == "") {
                    nameField.setHint("Please enter your name!");
                    nameField.setTextColor(Color.RED);
                } else {
                    nameFixed.setText("Welcome " + name + "!");
                    fadeIn.setDuration(1000);
                    fadeIn.setFillAfter(true);
                    nameField.setVisibility(View.INVISIBLE);
                    nameFixed.startAnimation(fadeIn);
                }
            }
        });

        ArrayList<String> cities = new ArrayList<String>();
        cities.add("Select a city...");
        cities.add("Abu Dhabi");
        cities.add("Algiers");
        cities.add("Amman");
        cities.add("Baghdad");
        cities.add("Bahrain");
        cities.add("Beirut");
        cities.add("Cairo");
        cities.add("Damascus");
        cities.add("Doha");
        cities.add("Jerusalem");
        cities.add("Khartoum");
        cities.add("Kuwait");
        cities.add("Muscat");
        cities.add("Rabat");
        cities.add("Riyadh");
        cities.add("Sanaa");
        cities.add("Tripoli");
        cities.add("Tunis");

        //Creating adapter for the spinner
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities){
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
        };

        //Dropdown layout style
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //spinner click listener
        spinner.setOnItemSelectedListener(new SpinnerActivity());

        //Attaching data to adapter
        spinner.setAdapter(cityAdapter);
    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (pos > 0) {
                String selectedCity = (String) parent.getItemAtPosition(pos);
                Toast.makeText(parent.getContext(), "You've selected " + selectedCity, Toast.LENGTH_SHORT).show();
            }

        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }

    }

 }
