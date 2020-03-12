package com.example.artistaapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.artistaapp.objects.Card;

import java.util.List;

@Dao
public interface CardDao {

    @Insert
    long[] insertCard(Card... cards);

    @Query("SELECT * FROM cards")
    LiveData<List<Card>> getCard();

    @Delete
    int delete(Card... cards);

    @Update
    int update(Card... cards);
}
