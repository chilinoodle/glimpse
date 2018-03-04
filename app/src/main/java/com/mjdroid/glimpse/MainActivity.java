package com.mjdroid.glimpse;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;



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

    }
}
