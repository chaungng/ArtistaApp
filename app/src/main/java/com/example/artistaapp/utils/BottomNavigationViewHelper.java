package com.example.artistaapp.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.artistaapp.R;
import com.example.artistaapp.appointment.AppointmentActivity;
import com.example.artistaapp.home.HomeActivity;
import com.example.artistaapp.love.LoveActivity;
import com.example.artistaapp.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHelper";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        Intent intentHome = new Intent(context, HomeActivity.class); // ACTIVITY_NUM = 0
                        context.startActivity(intentHome);
                        break;
                    case R.id.ic_appointment:
                        Intent intentAppointment = new Intent(context, AppointmentActivity.class); // ACTIVITY_NUM = 1
                        context.startActivity(intentAppointment);
                        break;
                    case R.id.ic_love:
                        Intent intentLikes = new Intent(context, LoveActivity.class); // ACTIVITY_NUM = 2
                        context.startActivity(intentLikes);
                        break;
                    case R.id.ic_profile:
                        Intent intentProfile = new Intent(context, ProfileActivity.class); // ACTIVITY_NUM = 3
                        context.startActivity(intentProfile);
                        break;
                }

                return false;
            }
        });
    }
}
