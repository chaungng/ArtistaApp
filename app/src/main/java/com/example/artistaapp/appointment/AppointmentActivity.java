package com.example.artistaapp.appointment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artistaapp.R;
import com.example.artistaapp.objects.Appointment;
import com.example.artistaapp.utils.AppointmentRecyclerViewAdapter;
import com.example.artistaapp.utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

public class AppointmentActivity extends AppCompatActivity {
    private static final String TAG = "AppointmentActivity";

    private static final int ACTIVITY_NUM = 1;
    private Context mContext = AppointmentActivity.this;

    // RecyclerView variables
    private RecyclerView mRecyclerView;
    private AppointmentRecyclerViewAdapter mAppointmentAdapter;
    private List<Appointment> mAppointmentList = new ArrayList<>();

    // UI variables

    // Other variables
    private String imgUrl, title, salon, location, date, time, artist, level;
    private double price, adjustment, sum, tax, total;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        Log.d(TAG, "onCreate: started.");

        // BottomNavigationView
        setupBottomNavigationView();

        mAppointmentList.add(getDataFromBooking());
        mAppointmentAdapter = new AppointmentRecyclerViewAdapter(mAppointmentList, mContext);

        mRecyclerView = findViewById(R.id.appointment_recycler_view);
        mRecyclerView.setAdapter(mAppointmentAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    private Appointment getDataFromBooking() {
        Intent intent = getIntent();

        // get String extra
        imgUrl = intent.getStringExtra("imgUrl");
        title = intent.getStringExtra("title");
        salon = intent.getStringExtra("salon");
        location = intent.getStringExtra("location");
        date = intent.getStringExtra("date");
        time = intent.getStringExtra("time");
        artist = intent.getStringExtra("artist");
        level = intent.getStringExtra("level");

        // get Double extra
        price = intent.getDoubleExtra("price", 0.0);
        adjustment = intent.getDoubleExtra("adjust", 0.0);
        sum = intent.getDoubleExtra("sum", 0.0);
        tax = intent.getDoubleExtra("tax", 0.0);
        total = intent.getDoubleExtra("total", 0.0);

        Appointment anAppointment = Appointment.builder()
                .imgUrl(imgUrl)
                .title(title)
                .salon(salon)
                .location(location)
                .date(date)
                .time(time)
                .artist(artist)
                .level(level)
                .price(price)
                .adjust(adjustment)
                .sum(sum)
                .tax(tax)
                .total(total).build();

        return anAppointment;
    }

    /**
     * Bottom Navigation View Setup
     */
    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);

        // Get the menu items by indexes
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
