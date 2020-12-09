package com.example.moviegallery.data.entities;

import com.example.moviegallery.domain.nodel.Rating;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MovieEntity implements Serializable{

    @SerializedName("Actors")
    @Expose
    private String actors;
    @SerializedName("Awards")
    @Expose
    private String awards;
    @SerializedName("BoxOffice")
    @Expose
    private String boxOffice;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("DVD")
    @Expose
    private String dVD;
    @SerializedName("Director")
    @Expose
    private String director;
    @SerializedName("Genre")
    @Expose
    private String genre;
    @SerializedName("Language")
    @Expose
    private String language;
    @SerializedName("Metascore")
    @Expose
    private String metascore;
    @SerializedName("Plot")
    @Expose
    private String plot;
    @SerializedName("Poster")
    @Expose
    private String poster;
    @SerializedName("Production")
    @Expose
    private String production;
    @SerializedName("Rated")
    @Expose
    private String rated;
    @SerializedName("Ratings")
    @Expose
    private List<Rating> ratings = null;
    @SerializedName("Released")
    @Expose
    private String released;
    @SerializedName("Response")
    @Expose
    private String response;
    @SerializedName("Runtime")
    @Expose
    private String runtime;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Website")
    @Expose
    private String website;
    @SerializedName("Writer")
    @Expose
    private String writer;
    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("imdbID")
    @Expose
    private String imdbID;
    @SerializedName("imdbRating")
    @Expose
    private String imdbRating;
    @SerializedName("imdbVotes")
    @Expose
    private String imdbVotes;
    @SerializedName("totalSeasons")
    @Expose
    private String totalSeasons;

    public MovieEntity(String actors, String director, String genre, String plot, String poster, String released, String runtime, String title, String writer, String imdbRating, String imdbID) {
        this.actors = actors;
        this.director = director;
        this.genre = genre;
        this.plot = plot;
        this.poster = poster;
        this.released = released;
        this.runtime = runtime;
        this.title = title;
        this.writer = writer;
        this.imdbRating = imdbRating;
        this.imdbID = imdbID;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDVD() {
        return dVD;
    }

    public void setDVD(String dVD) {
        this.dVD = dVD;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(String totalSeasons) {
        this.totalSeasons = totalSeasons;
    }
}
