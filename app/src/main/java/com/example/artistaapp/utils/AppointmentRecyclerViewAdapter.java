package com.example.artistaapp.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artistaapp.R;
import com.example.artistaapp.objects.Appointment;
import com.example.artistaapp.objects.Artist;

import java.text.DecimalFormat;
import java.util.List;

public class AppointmentRecyclerViewAdapter extends RecyclerView.Adapter<AppointmentRecyclerViewAdapter.AppointmentViewHolder> {
    private static final String TAG = "AppointmentRecyclerView";
    private Context mContext;
    private List<Appointment> mAppointmentList;

    // Flag which item is selected
    private int selectedPos = RecyclerView.NO_POSITION;

    // Decimal format for the artist price adjustment
    private DecimalFormat df = new DecimalFormat("$##.##");

    // Constructor
    public AppointmentRecyclerViewAdapter(List<Appointment> mAppointmentList, Context mContext) {
        this.mAppointmentList = mAppointmentList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_center_appointment_view, parent, false);
        AppointmentViewHolder holder = new AppointmentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        // Appointment Image
        String imgUrl = mAppointmentList.get(position).getImgUrl();

        // Appointment Information
        String title = mAppointmentList.get(position).getTitle();
        String salon = mAppointmentList.get(position).getSalon();
        String location = mAppointmentList.get(position).getLocation();
        String date = "Date: " + mAppointmentList.get(position).getDate();
        String time = "Time: " + mAppointmentList.get(position).getTime();
        String artist = "Artist: " + mAppointmentList.get(position).getArtist();
        String level = "Level: " + mAppointmentList.get(position).getLevel();
        String price = "Price: " + df.format(mAppointmentList.get(position).getPrice());
        String adjust = "Adjust: " + df.format(mAppointmentList.get(position).getAdjust());
        String sum = "Sum: " + df.format(mAppointmentList.get(position).getSum());
        String tax = "Tax: " + df.format(mAppointmentList.get(position).getTax());
        String total = "Total: " + df.format(mAppointmentList.get(position).getTotal());

        // Set text to the view holder
        holder.image.setImageResource(Integer.parseInt(imgUrl));
        holder.txtTitle.setText(title);
        holder.txtSalon.setText(salon);
        holder.txtLocation.setText(location);
        holder.txtDate.setText(date);
        holder.txtTime.setText(time);
        holder.txtArtist.setText(artist);
        holder.txtLevel.setText(level);
        holder.txtPrice.setText(price);
        holder.txtAdjust.setText(adjust);
        holder.txtSum.setText(sum);
        holder.txtTax.setText(tax);
        holder.txtTotal.setText(total);

        holder.itemView.setSelected(selectedPos == position);
    }

    @Override
    public int getItemCount() {
        return mAppointmentList.size();
    }

    public int getSelectedPos() {
        return this.selectedPos;
    }

    /**
     * Artist View Holder Class
     */

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "AppointmentViewHolder";

        ImageView image;
        private TextView txtTitle, txtSalon, txtLocation, txtDate, txtTime, txtArtist, txtLevel,
                txtPrice, txtAdjust, txtSum, txtTax, txtTotal;

        RelativeLayout parentLayout;


        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.cardImage);
            txtTitle = itemView.findViewById(R.id.cardTitle);
            txtSalon = itemView.findViewById(R.id.cardSalon);
            txtLocation = itemView.findViewById(R.id.cardLocation);
            txtDate = itemView.findViewById(R.id.cardDate);
            txtTime = itemView.findViewById(R.id.cardTime);
            txtArtist = itemView.findViewById(R.id.cardArtist);
            txtLevel = itemView.findViewById(R.id.cardLevel);
            txtPrice = itemView.findViewById(R.id.cardPrice);
            txtAdjust = itemView.findViewById(R.id.cardAdjust);
            txtSum = itemView.findViewById(R.id.cardSum);
            txtTax = itemView.findViewById(R.id.cardTax);
            txtTotal = itemView.findViewById(R.id.cardTotal);

            parentLayout = itemView.findViewById(R.id.artist_profile_layout);

        }
    }
}
