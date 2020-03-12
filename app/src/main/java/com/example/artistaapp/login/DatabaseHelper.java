package com.example.artistaapp.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.artistaapp.objects.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE = "artista.db";
    private static final String TABLE_USER = "users";
    private static final String USER_ID = "id";
    private static final String USER_FNAME = "first_name";
    private static final String USER_LNAME = "last_name";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";

    private static final String TABLE_CREATE = "create table users" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "first_name TEXT NOT NULL," +
            "last_name TEXT NOT NULL," +
            "email TEXT NOT NULL UNIQUE," +
            "password TEXT NOT NULL);";

    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    public void insertUser(User user) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_FNAME, user.getFirstName());
        values.put(USER_LNAME, user.getLastName());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public String searchPassword(String email) {
        db = this.getReadableDatabase();
        String searchQuery = "SELECT email, password from " + TABLE_USER;
        Cursor cursor = db.rawQuery(searchQuery, null);

        // Temporary values to hold search strings
        String tempEmail;
        String tempPassword = "not found";

        if (cursor.moveToFirst()) {
            do {
                tempEmail = cursor.getString(0);
                if (tempEmail.equals(email)) {
                    tempPassword = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }

        return tempPassword;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_USER;
        db.execSQL(query);
    }
}
