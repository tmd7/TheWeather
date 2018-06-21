package com.tmarat.theweather;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private Toolbar toolbar;

  private ArrayList<Data> dataList = new ArrayList<>();
  private Sensors sensors;

  @Override protected void onPause() {
    super.onPause();
    if (sensors != null) {
      sensors.unregisterSensorListener();
    }
  }

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

    setToolBar();
    setDrawerNavigation();
    startFragment(R.id.main_container, new WeatherInfoFragment());
  }

  private void setToolBar() {
    toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  private void setDrawerNavigation() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  private void showSnackBar(int resId) {
    Snackbar.make(toolbar, resId, Snackbar.LENGTH_SHORT).show();
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
        startFragment(R.id.main_container,ListCitiesFragment.init(dataList));
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  public void startFragment(int rId, Fragment fragment) {
    getFragmentManager()
        .beginTransaction()
        .replace(rId, fragment)
        .commit();
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_ambient_weather) {
      sensors = new Sensors(getApplicationContext());
      Data data = sensors.getData();
      if (sensors.initSensors()) {
        startFragment(R.id.main_container, WeatherInfoFragment.init(data));
      } else {
        showSnackBar(R.string.sensor_error);
      }
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
