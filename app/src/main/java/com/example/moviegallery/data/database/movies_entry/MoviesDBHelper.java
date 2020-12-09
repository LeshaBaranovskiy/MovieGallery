package com.example.moviegallery.data.database.movies_entry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.moviegallery.data.database.MoviesDBContract;

public class MoviesDBHelper extends SQLiteOpenHelper {

    public MoviesDBHelper(@Nullable Context context) {
        super(context, MoviesDBContract.DATABASE_NAME, null, MoviesDBContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MoviesDBContract.MovieTable.CREATE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MoviesDBContract.MovieTable.DROP_COMMAND);
        db.execSQL(MoviesDBContract.RatingsTable.DROP_COMMAND);
        onCreate(db);
    }
}
