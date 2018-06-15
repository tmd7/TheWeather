package com.tmarat.theweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainNavigator {

    private WeatherInfoFragment weatherInfoFragment;
    private ArrayList<Data> dataList = new ArrayList<>();


    @Override
    protected void onResume() {
        super.onResume();

        //For example: download data from Server
        //The method returned ArrayList<Data>
        dataList.clear();
        dataList.add(0, new Data("Moscow", "30", "30", "768", "10"));
        dataList.add(1, new Data("London", "15", "50", "760", "30"));
        dataList.add(2, new Data("New York", "12", "60", "770", "50"));
        dataList.add(3, new Data("New York", "12", "60", "770", "50"));

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
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void startListCitiesFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, ListCitiesFragment.init(dataList))
                .addToBackStack("")
                .commit();
    }
}
