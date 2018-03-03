package com.mjdroid.glimpse;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;



public class LoginActivity extends AppCompatActivity {

    boolean infoSaved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText nameField = (EditText) findViewById(R.id.name_field);
        final TextView nameFixed = (TextView) findViewById(R.id.name_fixed);
        final Spinner spinner = (Spinner) findViewById(R.id.city_spinner);
        final TextView selectedCityView = (TextView) findViewById(R.id.selected_city);
        final TextView changeInfo = (TextView) findViewById(R.id.change);
        final Button saveContinue = (Button) findViewById(R.id.save_and_continue);

        nameFixed.setVisibility(View.INVISIBLE);
        selectedCityView.setVisibility(View.INVISIBLE);
        changeInfo.setVisibility(View.INVISIBLE);

        saveContinue.setText(R.string.button_save);

        saveContinue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!infoSaved) {
                    //Using custom AnimationFade class constructor
                    AnimationFade fadeNameFixed = new AnimationFade(1000,nameFixed);
                    AnimationFade fadeCitySelected = new AnimationFade(1000,selectedCityView);

                    String name = nameField.getText().toString();
                    String city = spinner.getSelectedItem().toString();

                    if (name.matches("")) {
                        nameField.setHint("Please enter your name!");
                        nameField.setHintTextColor(Color.RED);
                    } else if(spinner.getSelectedItemPosition() == 0) {
                        Toast.makeText(LoginActivity.this,"Please choose a city",Toast.LENGTH_SHORT).show();
                    } else {
                        nameFixed.setText("Welcome " + name + "!");
                        nameField.setVisibility(View.INVISIBLE);
                        //Using custom method on custom AnimationFade class
                        fadeNameFixed.startFadeIn();

                        spinner.setVisibility(View.INVISIBLE);
                        selectedCityView.setText("You set your home city to " + city);
                        fadeCitySelected.startFadeIn();

                        changeInfo.setVisibility(View.VISIBLE);

                        saveContinue.setText(R.string.button_continue);
                        infoSaved = true;
                    }

                } else {

                    Intent goToMain = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(goToMain);
                }

            }
        });

        changeInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                changeInfo.setVisibility(View.INVISIBLE);
                nameFixed.setVisibility(View.INVISIBLE);
                selectedCityView.setVisibility(View.INVISIBLE);
                nameField.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                saveContinue.setText(R.string.button_save);
                infoSaved = false;
            }
        });

        ArrayList<String> cities = new ArrayList<String>();
        cities.add(getString(R.string.choose_city));
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
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, R.layout.city_spinner_item, cities){
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
        cityAdapter.setDropDownViewResource(R.layout.city_spinner_dropdown_item);

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
