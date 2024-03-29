package com.example.moviegallery.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.moviegallery.data.entities.MovieEntity;
import com.example.moviegallery.domain.nodel.Rating;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.core.Observable;

public class MoviesDAO {

    private Context context;

    public MoviesDAO(Context context) {
        this.context = context;
    }

    public Observable<List<MovieEntity>> getMoviesFromDB() {
        List<MovieEntity> moviesFromDB = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(MoviesDBContract.MovieTable.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            MovieEntity movie = new MovieEntity(
                    cursor.getString(cursor.getColumnIndex(MoviesDBContract.MovieTable.COLUMN_ACTORS)),
                    cursor.getString(cursor.getColumnIndex(MoviesDBContract.MovieTable.COLUMN_DIRECTOR)),
                    cursor.getString(cursor.getColumnIndex(MoviesDBContract.MovieTable.COLUMN_GENRE)),
                    cursor.getString(cursor.getColumnIndex(MoviesDBContract.MovieTable.COLUMN_PLOT)),
                    cursor.getString(cursor.getColumnIndex(MoviesDBContract.MovieTable.COLUMN_POSTER)),
                    cursor.getString(cursor.getColumnIndex(MoviesDBContract.MovieTable.COLUMN_RELEASED)),
                    cursor.getString(cursor.getColumnIndex(MoviesDBContract.MovieTable.COLUMN_RUNTIME)),
                    cursor.getString(cursor.getColumnIndex(MoviesDBContract.MovieTable.COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(MoviesDBContract.MovieTable.COLUMN_WRITER)),
                    cursor.getString(cursor.getColumnIndex(MoviesDBContract.MovieTable.COLUMN_IMDB_RATING)),
                    cursor.getString(cursor.getColumnIndex(MoviesDBContract.MovieTable.COLUMN_IMDB_ID))
            );
            moviesFromDB.add(movie);
        }
        cursor.close();

        for (MovieEntity movie : moviesFromDB) {
            String id = movie.getImdbID();
            List<Rating> r = getRatingsFromDB(id);
            movie.setRatings(r);
        }

        return Observable.just(moviesFromDB);
    }

    public void insertMoviesInDB(List<MovieEntity> movies) {
        for (MovieEntity movie: movies) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MoviesDBContract.MovieTable.COLUMN_ACTORS, movie.getActors());
            contentValues.put(MoviesDBContract.MovieTable.COLUMN_DIRECTOR, movie.getDirector());
            contentValues.put(MoviesDBContract.MovieTable.COLUMN_GENRE, movie.getGenre());
            contentValues.put(MoviesDBContract.MovieTable.COLUMN_IMDB_RATING, movie.getImdbRating());
            contentValues.put(MoviesDBContract.MovieTable.COLUMN_PLOT, movie.getPlot());
            contentValues.put(MoviesDBContract.MovieTable.COLUMN_POSTER, movie.getPoster());
            contentValues.put(MoviesDBContract.MovieTable.COLUMN_RELEASED, movie.getReleased());
            contentValues.put(MoviesDBContract.MovieTable.COLUMN_RUNTIME, movie.getRuntime());
            contentValues.put(MoviesDBContract.MovieTable.COLUMN_TITLE, movie.getTitle());
            contentValues.put(MoviesDBContract.MovieTable.COLUMN_IMDB_ID, movie.getImdbID());
            contentValues.put(MoviesDBContract.MovieTable.COLUMN_WRITER, movie.getWriter());
            context.getContentResolver().insert(MoviesDBContract.MovieTable.CONTENT_URI, contentValues);
        }
    }

    public void deleteAllMovies() {
        context.getContentResolver().delete(MoviesDBContract.MovieTable.CONTENT_URI, null, null);
    }

    private List<Rating> getRatingsFromDB(String movieIDIMDB) {
        List<Rating> ratingsFromDB = new ArrayList<>();

        String[] id = {String.valueOf(movieIDIMDB)};
        Cursor cursor = context.getContentResolver().query(MoviesDBContract.RatingsTable.CONTENT_URI, null,"imdbID = ?", id, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Rating rating = new Rating(
                        cursor.getString(cursor.getColumnIndex(MoviesDBContract.RatingsTable.COLUMN_SOURCE)),
                        cursor.getString(cursor.getColumnIndex(MoviesDBContract.RatingsTable.COLUMN_VALUE)),
                        cursor.getString(cursor.getColumnIndex(MoviesDBContract.RatingsTable.COLUMN_IMDB_ID))
                );
                ratingsFromDB.add(rating);
            }
        }
        Objects.requireNonNull(cursor).close();
        return ratingsFromDB;
    }

    public void insertRatingsInDB(List<Rating> ratings) {
        for (Rating r: ratings) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MoviesDBContract.RatingsTable.COLUMN_SOURCE, r.getSource());
            contentValues.put(MoviesDBContract.RatingsTable.COLUMN_VALUE, r.getValue());
            contentValues.put(MoviesDBContract.RatingsTable.COLUMN_IMDB_ID, r.getImdbID());
            context.getContentResolver().insert(MoviesDBContract.RatingsTable.CONTENT_URI, contentValues);
        }
    }

    public void deleteAllRaitings() {
        context.getContentResolver().delete(MoviesDBContract.RatingsTable.CONTENT_URI, null, null);
    }

}
