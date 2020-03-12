package com.example.artistaapp.booking;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.artistaapp.R;
import com.example.artistaapp.appointment.AppointmentActivity;
import java.text.DecimalFormat;

public class BookingDialogFragment extends AppCompatDialogFragment {

    // UI components
    private TextView txtService,txtArtist, txtLevel,txtSalon,txtDate,txtTime;
    private TextView txtPrice, txtAdjust, txtSum, txtTax, txtTotal;

    // Variables
    private String imgUrl, service, artist, level, salon, location, date, time;
    private double price, adjustment, sum, tax, total;
    private static double TAX_RATE = 0.12;

    // Formater
    DecimalFormat df = new DecimalFormat("$##.##");

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog_booking, null);

        setupDialogUIComponents(view);
        getBookingBundle();
        setupDisplayData();

        builder.setView(view)
                .setTitle("Confirmation")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // nothing happen here
                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), AppointmentActivity.class);
                        Bundle bundle = new Bundle();

                        // Set booking bundle to pass to Appointment
                        bundle.putString("imgUrl", imgUrl);
                        bundle.putString("title", service);
                        bundle.putString("salon", salon);
                        bundle.putString("location", location);
                        bundle.putString("date", date);
                        bundle.putString("time", time);
                        bundle.putString("artist", artist);
                        bundle.putString("level", level);
                        bundle.putDouble("price", price);
                        bundle.putDouble("adjust", adjustment);
                        bundle.putDouble("sum", sum);
                        bundle.putDouble("tax", tax);
                        bundle.putDouble("total", total);

                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

        return builder.create();
    }

    private void setupDialogUIComponents(View view) {
        txtService = view.findViewById(R.id.booking_service);
        txtArtist = view.findViewById(R.id.booking_artist_name);
        txtLevel = view.findViewById(R.id.booking_artist_level);
        txtSalon = view.findViewById(R.id.booking_salon);
        txtDate = view.findViewById(R.id.booking_date);
        txtTime = view.findViewById(R.id.booking_time);
        txtPrice = view.findViewById(R.id.booking_service_price);
        txtAdjust = view.findViewById(R.id.booking_price_adjust);
        txtSum = view.findViewById(R.id.booking_sum_price);
        txtTax = view.findViewById(R.id.booking_tax);
        txtTotal = view.findViewById(R.id.booking_total_cost);
    }

    private void setupDisplayData() {
        txtService.setText(service);
        txtArtist.setText(artist);
        txtLevel.setText(level);
        txtSalon.setText(salon);
        txtDate.setText(date);
        txtTime.setText(time);
        txtPrice.setText(df.format(price));
        txtAdjust.setText(df.format(adjustment));
        txtSum.setText(df.format(sum));
        txtTax.setText(df.format(tax));
        txtTotal.setText(df.format(total));
    }

    private void getBookingBundle() {
        imgUrl = getArguments().getString("imgUrl");
        service = getArguments().getString("service_title");
        artist = getArguments().getString("artist_name");
        level = getArguments().getString("artist_level");
        salon = getArguments().getString("salon_name");
        location = getArguments().getString("salon_location");
        date = getArguments().getString("date");
        time = getArguments().getString("time");
        price = getArguments().getDouble("service_price");
        adjustment = getArguments().getDouble("adjustment");
        adjustment = adjustment*price;
        sum = price + adjustment;
        tax = sum * TAX_RATE;
        total = sum + tax;
    }
}
