package com.example.artistaapp.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artistaapp.R;
import com.example.artistaapp.objects.Card;

import java.text.DecimalFormat;
import java.util.List;

public class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardRecyclerViewAdapter.CardViewHolder>{
    private static final String TAG = "CardRecyclerViewAdapter";
    private List<Card> mServiceCard;
    private Context mContext;
    private OnBookButtonClickListener mOnBookButtonClickListener;
    private DecimalFormat df = new DecimalFormat("$##.##");

    public CardRecyclerViewAdapter(List<Card> mServiceCard, Context mContext,
                                   OnBookButtonClickListener onBookButtonClickListener) {
        this.mServiceCard = mServiceCard;
        this.mContext = mContext;
        this.mOnBookButtonClickListener = onBookButtonClickListener;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_center_cardview, parent, false);
        CardViewHolder holder = new CardViewHolder(view, mOnBookButtonClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        // Default photo

        holder.image.setImageResource(Integer.parseInt(mServiceCard.get(position).getImgUrl()));
        // Price is a double in the Card class
        holder.price.setText(df.format(mServiceCard.get(position).getPrice()));

        // Service information
        holder.title.setText(mServiceCard.get(position).getTitle());
        holder.salon.setText(mServiceCard.get(position).getSalon());
        holder.location.setText(mServiceCard.get(position).getLocation());
        holder.description.setText(mServiceCard.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return mServiceCard.size();
    }


    /**
     *  CardViewHolder
     */
    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title, price, salon, location, description;
        ImageView image;
        AppCompatButton btnBook;
        OnBookButtonClickListener onBookButtonClickListener;

        public CardViewHolder(@NonNull View itemView, OnBookButtonClickListener onBookButtonClickListener) {
            super(itemView);

            title = itemView.findViewById(R.id.cardTitle);
            image = itemView.findViewById(R.id.cardImage);
            price = itemView.findViewById(R.id.cardPrice);
            salon = itemView.findViewById(R.id.cardSalon);
            location = itemView.findViewById(R.id.cardLocation);
            description = itemView.findViewById(R.id.cardDescription);

            // Button Book now
            btnBook = itemView.findViewById(R.id.book_now_button);

            // Set default OnBookButtonClickListener
            this.onBookButtonClickListener = onBookButtonClickListener;

            //Set onClickListener for btnBook
            btnBook.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onBookButtonClickListener.onCardButtonClick(getAdapterPosition());
        }
    }

    public interface OnBookButtonClickListener {
        void onCardButtonClick(int position);
    }
}












