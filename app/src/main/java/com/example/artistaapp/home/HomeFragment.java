package com.example.artistaapp.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artistaapp.R;
import com.example.artistaapp.booking.BookingActivity;
import com.example.artistaapp.objects.Card;
import com.example.artistaapp.utils.CardRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements CardRecyclerViewAdapter.OnBookButtonClickListener {
    private static final String TAG = "HomeFragment";
    private Context mContext = this.getContext();

    private RecyclerView mRecyclerView;
    private CardRecyclerViewAdapter cardAdapter;
    private List<Card> mCards;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCards = new ArrayList<>();
        mCards = setupServiceCardList();

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        cardAdapter = new CardRecyclerViewAdapter(mCards, getContext(), this);

        mRecyclerView = view.findViewById(R.id.service_recycler_view);
        mRecyclerView.setAdapter(cardAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    private List<Card> setupServiceCardList() {
        List<Card> cardList = new ArrayList<>();
        String img1, img2, img3, img4, img5, img6;
        img1 =  String.valueOf(R.drawable.hair_coloring_women);
        img2 = String.valueOf(R.drawable.hair_styling_men);
        img3 = String.valueOf(R.drawable.makeup_bridal_1);
        img4 = String.valueOf(R.drawable.makeup_bridal_2);
        img5 = String.valueOf(R.drawable.nail_1);
        img6 = String.valueOf(R.drawable.spa_1);

        Card card1 = Card.builder()
                .id(1)
                .imgUrl(img1)
                .title("Women Hair Coloring")
                .price(199.00).salon("Zoom Hair Studio")
                .location("New Westminster, BC")
                .description("Beautiful hair coloring, super smooth and silky hair")
                .build();

        Card card2 = Card.builder()
                .id(2)
                .imgUrl(img2)
                .title("Men Hair Styling")
                .price(75.00)
                .salon("Classic Salon")
                .location("New Westminster, BC")
                .description("Stay-in-place wavy hair styling for men")
                .build();

        Card card3 = Card.builder()
                .id(3)
                .imgUrl(img3)
                .title("Bridal Natural Makeup")
                .price(300.00)
                .salon("Salon 91")
                .location("New Westminster, BC")
                .description("Natural glowing skin and soft eye bridal makeup")
                .build();

        Card card4 = Card.builder()
                .id(4)
                .imgUrl(img4)
                .title("Bridal Glam Makeup")
                .price(350.00)
                .salon("Tori Blush")
                .location("New Westminster, BC")
                .description("Glamorous bridal makeup for photo shoot and night party")
                .build();

        Card card5 = Card.builder()
                .id(5)
                .imgUrl(img5)
                .title("Christmas Manicure Acrylic")
                .price(70.00)
                .salon("Sugar Nail Bar")
                .location("New Westminster, BC")
                .description("Cutie manicure with special Christmas design")
                .build();

        Card card6 = Card.builder()
                .id(6)
                .imgUrl(img6)
                .title("Relaxing Hot Stone Massage")
                .price(250.00)
                .salon("Calmina Massage")
                .location("New Westminster, BC")
                .description("Reduce stress and relax your sore back")
                .build();

        cardList.add(card1);
        cardList.add(card2);
        cardList.add(card3);
        cardList.add(card4);
        cardList.add(card5);
        cardList.add(card6);

        return cardList;
    }


    @Override
    public void onCardButtonClick(int position) {
        Log.d(TAG, "onCardButtonClick: " + position);
        Intent intent = new Intent(getContext(), BookingActivity.class);

        // Should use Parcelable but to keep it simple now use Bundle
        // Bundle is not good for app performance if you have a large amount of data
        Bundle cardBundle = new Bundle();
        cardBundle.putString("imgUrl", mCards.get(position).getImgUrl());
        cardBundle.putString("service_title", mCards.get(position).getTitle());
        cardBundle.putDouble("service_price", mCards.get(position).getPrice());
        cardBundle.putString("salon_name", mCards.get(position).getSalon());
        cardBundle.putString("salon_location", mCards.get(position).getLocation());

        // Put data in bundle into intent
        intent.putExtras(cardBundle);

        startActivity(intent);
    }
}
