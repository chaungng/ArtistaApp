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
import com.example.artistaapp.objects.Artist;

import java.text.DecimalFormat;
import java.util.List;

public class ArtistRecyclerViewAdapter extends RecyclerView.Adapter<ArtistRecyclerViewAdapter.ArtistViewHolder> {
    private static final String TAG = "ArtistRecyclerViewAdapt";
    private Context mContext;
    private List<Artist> mArtistList;
    private OnHighlightClickListener mOnHighlightClickListener;

    // Flag which item is selected
    private int selectedPos = RecyclerView.NO_POSITION;

    // Decimal format for the artist price adjustment
    private DecimalFormat df = new DecimalFormat("##.##%");

    // Constructor
    public ArtistRecyclerViewAdapter(List<Artist> mArtistList, Context mContext,
                                     OnHighlightClickListener mOnHighlightClickListener) {
        this.mArtistList = mArtistList;
        this.mContext = mContext;
        this.mOnHighlightClickListener = mOnHighlightClickListener;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_artist_profile, parent, false);
        ArtistViewHolder holder = new ArtistViewHolder(view, mOnHighlightClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        // Artist Profile default
        holder.image.setImageResource(Integer.parseInt(mArtistList.get(position).getImgUrl()));

        // Artist Information
        String aFullName = mArtistList.get(position).getFirstName() + " " + mArtistList.get(position).getLastName();
        String aLevel = "Level: " + mArtistList.get(position).getLevel();
        String aYearOfExp = "Year of experience: " + mArtistList.get(position).getYearsOfExperience();
        String aAdjustment = "Price adjustment: " + df.format(mArtistList.get(position).getAdjustPercentage());

        holder.fullName.setText(aFullName);
        holder.level.setText(aLevel);
        holder.experience.setText(aYearOfExp);
        holder.adjustPercentage.setText(aAdjustment);

        holder.itemView.setSelected(selectedPos == position);
    }

    @Override
    public int getItemCount() {
        return mArtistList.size();
    }

    public int getSelectedPos() {
        return this.selectedPos;
    }

    /**
     * Artist View Holder Class
     */

    public class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String TAG = "ArtistViewHolder";

        ImageView image;
        TextView fullName, level, experience, adjustPercentage;
        OnHighlightClickListener onHighlightClickListener;
        RelativeLayout parentLayout;

        public ArtistViewHolder(@NonNull View itemView, OnHighlightClickListener onHighlightClickListener) {
            super(itemView);

            fullName = itemView.findViewById(R.id.artist_name);
            image = itemView.findViewById(R.id.artist_photo);
            level = itemView.findViewById(R.id.artist_level);
            experience = itemView.findViewById(R.id.artist_exp);
            adjustPercentage = itemView.findViewById(R.id.artist_price_adjust);
            parentLayout = itemView.findViewById(R.id.artist_profile_layout);
            this.onHighlightClickListener = onHighlightClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            notifyItemChanged(selectedPos);
            selectedPos = getLayoutPosition();
            notifyItemChanged(selectedPos);

            onHighlightClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnHighlightClickListener {
        void onItemClick(int position);
    }
}
