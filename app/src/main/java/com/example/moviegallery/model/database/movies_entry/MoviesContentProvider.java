package com.example.moviegallery.model.database.movies_entry;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.moviegallery.model.database.MoviesDBContract;

public class MoviesContentProvider extends ContentProvider {

    private static final int MOVIES = 100;
    public static final int MOVIE_ID = 101;

    public static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(MoviesDBContract.MovieTable.CONTENT_AUTHORITY, MoviesDBContract.PATH_MOVIES, MOVIES);
        sUriMatcher.addURI(MoviesDBContract.MovieTable.CONTENT_AUTHORITY, MoviesDBContract.PATH_MOVIES + "/#", MOVIE_ID);
    }

    private MoviesDBHelper moviesDBHelper;

    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        moviesDBHelper = new MoviesDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        database = getDatabase(false);

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIES:
                cursor = database.query(MoviesDBContract.MovieTable.TABLE_NAME, projection, selection, selectionArgs,
                        null,null, sortOrder);
                break;
            case MOVIE_ID:
                selection = MoviesDBContract.MovieTable._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(MoviesDBContract.MovieTable.TABLE_NAME, projection, selection, selectionArgs,
                        null,null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = sUriMatcher.match(uri);

        switch (match) {
            case MOVIES:
                return MoviesDBContract.MovieTable.CONTENT_LIST_TYPE;
            case MOVIE_ID:
                return MoviesDBContract.MovieTable.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int match = sUriMatcher.match(uri);

        switch (match) {
            case MOVIES:
                return insertMovie(uri, values);
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        database = getDatabase(true);

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIES:
                return database.delete(MoviesDBContract.MovieTable.TABLE_NAME, selection, selectionArgs);
            case MOVIE_ID:
                selection = MoviesDBContract.MovieTable._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return database.delete(MoviesDBContract.MovieTable.TABLE_NAME, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIES:
                return updateMovie(uri, values, selection, selectionArgs);
            case MOVIE_ID:
                selection = MoviesDBContract.MovieTable._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateMovie(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private Uri insertMovie(Uri uri, ContentValues values) {
        database = getDatabase(true);

        long id = database.insert(MoviesDBContract.MovieTable.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e("ttt", "Failed to insert row for " + uri);
            return null;
        }
        return ContentUris.withAppendedId(uri, id);
    }

    private int updateMovie(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (values.size() == 0) {
            return 0;
        }

        database = getDatabase(true);

        return database.update(MoviesDBContract.MovieTable.TABLE_NAME, values, selection, selectionArgs);
    }

    private SQLiteDatabase getDatabase(boolean isWritable) {
        if (isWritable) {
            database = moviesDBHelper.getWritableDatabase();
        } else {
            database = moviesDBHelper.getReadableDatabase();
        }
        return database;
    }
}
