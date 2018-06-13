package com.tmarat.theweather;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements MainNavigator {

    private WeatherInfoFragment weatherInfoFragment;

    @Override
    protected void onResume() {
        super.onResume();

        //For example: download data from Server
        //The method returned ArrayList<Data>

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        weatherInfoFragment = new WeatherInfoFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, weatherInfoFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_choose_city:
                startListCitiesFragment();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startListCitiesFragment() {
        getFragmentManager()
                .beginTransaction()
                .remove(weatherInfoFragment)
                .add(R.id.main_container, new ListCitiesFragment())
                .addToBackStack("")
                .commit();
    }
}
