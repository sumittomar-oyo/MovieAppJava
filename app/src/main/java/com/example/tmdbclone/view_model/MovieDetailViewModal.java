package com.example.tmdbclone.view_model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.tmdbclone.constants.ArticleMovieConstants;
import com.example.tmdbclone.model.Movie;
import com.example.tmdbclone.model.MovieDetailed;
import com.example.tmdbclone.network.VolleySingleton;
import com.example.tmdbclone.response.MovieResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;

public class MovieDetailViewModal extends ViewModel {


    MutableLiveData<MovieDetailed> movieLiveData;
    MovieDetailed movie;

    public MovieDetailViewModal(@NonNull Application application,String movieId) {
        init(application,movieId);
    }

    public MutableLiveData<MovieDetailed> getMovieMutableLiveData() {
        return movieLiveData;
    }

    public void init(Application application,String movieId) {
        movieLiveData = new MutableLiveData<>();
        Log.e("Movvvii",""+movieId);
        movie = new MovieDetailed();
        movieLiveData.setValue(movie);
        populateList(application,movieId);


    }

    public void populateList(Application application,String movieId)  {
        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.GET,
                ArticleMovieConstants.MOVIE_DETAIL_URL + movieId+ArticleMovieConstants.API_KEY,null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if(response != null) {
                                Gson gson = new GsonBuilder().create();
                                Log.e("here","here");
                                movie = gson.fromJson(response.toString(), MovieDetailed.class);
                                if(movie == null)
                                    Log.e("mmmmmmmmm m m mm","sdfsfsdfafwqef");
                                movieLiveData.postValue((MovieDetailed) movie);
                            }

                        }
                        catch(Exception e){
                            Log.e("error",e.toString());
                            e.printStackTrace();
                        }
                    }
                } ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                            Log.e("ErrorResponse",error.toString());
                    }
                });

        VolleySingleton.getInstance(application).getRequestQueue().add(jsonObjectRequest);
}
}
