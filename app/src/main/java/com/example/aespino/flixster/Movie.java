package com.example.aespino.flixster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Movie {
    public String getReleaseDate(){
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public float getRating() {
        return rating;
    }

    public String getBackdropUrl(){
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropUrl);
    }

    public String getPosterUrl() {
            return String.format("https://image.tmdb.org/t/p/w342/%s", posterUrl);
    }

    public String title;
    public String releaseDate;
    public String posterUrl;
    public String overview;
    public float rating;
    public String backdropUrl;


    public Movie(JSONObject jsonObject)throws JSONException{
        this.posterUrl = jsonObject.getString("poster_path");
        this.overview = jsonObject.getString("overview");
        this.title = jsonObject.getString("original_title");
        this.rating = jsonObject.getInt("vote_average");
        this.backdropUrl = jsonObject.getString("backdrop_path");
        this.releaseDate = jsonObject.getString("release_date");
    }


    public static ArrayList<Movie> fromJsonArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();

            for(int i = 0; i < array.length(); i++){
                try{
                    results.add(new Movie(array.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        return results;
    }

    //@Override
    //public String toString(){
    //    return title + "-" + rating;
    //}
}
