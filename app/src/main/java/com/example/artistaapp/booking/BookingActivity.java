package com.example.artistaapp.booking;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artistaapp.R;
import com.example.artistaapp.objects.Artist;
import com.example.artistaapp.utils.ArtistRecyclerViewAdapter;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class BookingActivity extends AppCompatActivity implements View.OnClickListener,
        ArtistRecyclerViewAdapter.OnHighlightClickListener {

    private static final String TAG = "BookingActivity";

    private Context mContext = BookingActivity.this;

    // RecyclerView variables
    private RecyclerView mRecyclerView;
    private ArtistRecyclerViewAdapter artistAdapter;
    private List<Artist> mArtistList;

    // UI variables
    private AppCompatButton btnDatePicker, btnTimePicker;
    private ImageView backArrow, saveCheckMark;

    // Other variables
    private String imgUrl, bookDate, bookTime, serviceTitle, salonName, salonLocation;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private double servicePrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Log.d(TAG, "onCreate: started.");

        setupToolBar();
        setupDateTimePicker();
        setupArtistRecyclerView();
        getDataFromServiceItemClicked();
    }

    private void getDataFromServiceItemClicked() {
        Log.d(TAG, "getDataFromServiceItemClicked: called");
        Intent intent = getIntent();

        imgUrl = intent.getStringExtra("imgUrl");
        serviceTitle = intent.getStringExtra("service_title");
        salonName = intent.getStringExtra("salon_name");
        salonLocation = intent.getStringExtra("salon_location");
        servicePrice = intent.getDoubleExtra("service_price", 0.0);
    }

    private void setupArtistRecyclerView() {
        Log.d(TAG, "setupArtistRecyclerView: called");
        mArtistList = new ArrayList<>();
        mArtistList = setupArtistList();

        artistAdapter = new ArtistRecyclerViewAdapter(mArtistList, mContext, this);
        mRecyclerView = findViewById(R.id.artist_list);
        mRecyclerView.setAdapter(artistAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    private List<Artist> setupArtistList() {
        List<Artist> artistList = new ArrayList<>();

        Artist artist1 = Artist.builder()
                .imgUrl(String.valueOf(R.drawable.artist1))
                .artistID(1)
                .firstName("Chau")
                .lastName("Nguyen")
                .level("Senior")
                .yearsOfExperience(7)
                .adjustPercentage(0.2)
                .build();
        Artist artist2 = Artist.builder()
                .imgUrl(String.valueOf(R.drawable.artist2))
                .artistID(2)
                .firstName("Antonio")
                .lastName("Casanova")
                .level("Junior")
                .yearsOfExperience(3)
                .adjustPercentage(0.0)
                .build();
        Artist artist3 = Artist.builder()
                .imgUrl(String.valueOf(R.drawable.artist3))
                .artistID(3)
                .firstName("Amelia")
                .lastName("Park")
                .level("Trainee")
                .yearsOfExperience(1)
                .adjustPercentage(-0.2)
                .build();

        artistList.add(artist1);
        artistList.add(artist2);
        artistList.add(artist3);

        return artistList;
    }

    private void setupToolBar() {
        backArrow = findViewById(R.id.backArrow);
        saveCheckMark = findViewById(R.id.saveCheckmark);
        backArrow.setOnClickListener(this);
        saveCheckMark.setOnClickListener(this);
    }

    private void setupDateTimePicker() {
        btnDatePicker = findViewById(R.id.date_button);
        btnTimePicker = findViewById(R.id.time_button);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            bookDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                            btnDatePicker.setText(bookDate);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            Format formatter = new SimpleDateFormat("h:mm a");

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            bookTime = hourOfDay + ":" + minute;
                            btnTimePicker.setText(bookTime);

                            // Change the time in booking summary
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if (v == backArrow) {
            Log.d(TAG, "onClick: Navigating back to Home Fragment");
            finish();
        }

        if (v == saveCheckMark) {
            Log.d(TAG, "onClick: Navigating with bundle to Appointment Activity");
            if (bookDate == null) {
                Toast.makeText(mContext, "Please choose a date!", Toast.LENGTH_SHORT).show();
            } else if (bookTime == null) {
                Toast.makeText(mContext, "Please choose a time!", Toast.LENGTH_SHORT).show();
            } else if (artistAdapter.getSelectedPos() == RecyclerView.NO_POSITION) {
                Toast.makeText(mContext, "Please choose an artist!", Toast.LENGTH_SHORT).show();
            } else {
                openDialog();
            }
        }
    }

    private void openDialog() {
        BookingDialogFragment aBookingDialogFragment = new BookingDialogFragment();
        Bundle bookingBundle = setBookingBundle(new Bundle());
        aBookingDialogFragment.setArguments(bookingBundle);

        aBookingDialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    private Bundle setBookingBundle(Bundle bundle) {
        String artistName = mArtistList.get(artistAdapter.getSelectedPos()).getFirstName()
                + " " + mArtistList.get(artistAdapter.getSelectedPos()).getLastName();
        String artistLevel = mArtistList.get(artistAdapter.getSelectedPos()).getLevel();
        double artistAdjust = mArtistList.get(artistAdapter.getSelectedPos()).getAdjustPercentage();

        bundle.putString("imgUrl", imgUrl);
        bundle.putString("service_title", serviceTitle);
        bundle.putString("salon_name", salonName);
        bundle.putString("salon_location", salonLocation);
        bundle.putString("artist_name", artistName);
        bundle.putString("artist_level", artistLevel);
        bundle.putString("date", bookDate);
        bundle.putString("time", bookTime);
        bundle.putDouble("service_price", servicePrice);
        bundle.putDouble("adjustment", artistAdjust);

        return bundle;
    }

    @Override
    public void onItemClick(int position) {
        Log.d(TAG, "onItemClick: called");
    }
}
