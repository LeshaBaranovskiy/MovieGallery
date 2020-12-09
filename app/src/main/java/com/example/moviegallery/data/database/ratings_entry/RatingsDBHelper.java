package com.example.moviegallery.data.database.ratings_entry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.moviegallery.data.database.MoviesDBContract;

public class RatingsDBHelper extends SQLiteOpenHelper {

    public RatingsDBHelper(@Nullable Context context) {
        super(context, MoviesDBContract.RatingsTable.TABLE_NAME, null, MoviesDBContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MoviesDBContract.RatingsTable.CREATE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MoviesDBContract.RatingsTable.DROP_COMMAND);
        onCreate(db);
    }
}
