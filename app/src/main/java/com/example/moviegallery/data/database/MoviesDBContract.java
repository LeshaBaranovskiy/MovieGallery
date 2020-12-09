package com.example.moviegallery.data.database;

import android.net.Uri;
import android.provider.BaseColumns;

public final class MoviesDBContract {
    private MoviesDBContract() {}

    public static final String DATABASE_NAME = "movies_db";
    public static final int DATABASE_VERSION = 2;

    public static final String PATH_MOVIES = "movies";
    public static final String PATH_RATING = "rating";

    public static final class MovieTable implements BaseColumns {
        public static final String TABLE_NAME = "movies";

        public static final String CONTENT_AUTHORITY = "com.example.moviegallery.data.database.movies_entry";

        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_MOVIES);

        public static final String COLUMN_ACTORS = "actors";
        public static final String COLUMN_DIRECTOR = "director";
        public static final String COLUMN_GENRE = "genre";
        public static final String COLUMN_PLOT = "plot";
        public static final String COLUMN_POSTER = "poster";
        public static final String COLUMN_RELEASED = "released";
        public static final String COLUMN_RUNTIME = "runtime";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_WRITER = "writer";
        public static final String COLUMN_IMDB_RATING = "imdb_rating";
        public static final String COLUMN_IMDB_ID = "imdbID";

        public static final String TYPE_TEXT = "TEXT";
        public static final String TYPE_INTEGER = "INTEGER";

        public static final String CREATE_COMMAND = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" + _ID + " " + TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ACTORS + " " + TYPE_TEXT + ", " +
                COLUMN_DIRECTOR + " " + TYPE_TEXT + ", " +
                COLUMN_GENRE + " " + TYPE_TEXT + ", " +
                COLUMN_PLOT + " " + TYPE_TEXT + ", " +
                COLUMN_POSTER + " " + TYPE_TEXT + ", " +
                COLUMN_RELEASED + " " + TYPE_TEXT + ", " +
                COLUMN_RUNTIME + " " + TYPE_TEXT + ", " +
                COLUMN_TITLE + " " + TYPE_TEXT + ", " +
                COLUMN_WRITER + " " + TYPE_TEXT + ", " +
                COLUMN_IMDB_ID + " " + TYPE_TEXT + ", " +
                COLUMN_IMDB_RATING + " " + TYPE_TEXT + ")";

        public static final String DROP_COMMAND = "DROP TABLE IF EXISTS " + TABLE_NAME;
        public static final String CONTENT_LIST_TYPE = "list_movie";
        public static final String CONTENT_ITEM_TYPE = "movie";
    }

    public static final class RatingsTable implements BaseColumns {
        public static final String TABLE_NAME = "ratings";

        public static final String CONTENT_AUTHORITY = "com.example.moviegallery.data.database.ratings_entry";

        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_RATING);

        public static final String COLUMN_SOURCE = "source";
        public static final String COLUMN_VALUE = "value";
        public static final String COLUMN_IMDB_ID = "imdbID";

        public static final String TYPE_TEXT = "TEXT";
        public static final String TYPE_INTEGER = "INTEGER";

        public static final String CREATE_COMMAND = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" + _ID + " " + TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SOURCE + " " + TYPE_TEXT + ", " +
                COLUMN_IMDB_ID + " " + TYPE_TEXT + ", " +
                COLUMN_VALUE + " " + TYPE_TEXT + ")";

        public static final String DROP_COMMAND = "DROP TABLE IF EXISTS " + TABLE_NAME;
        public static final String CONTENT_LIST_TYPE = "list_rating";
        public static final String CONTENT_ITEM_TYPE = "rating";
    }

}
