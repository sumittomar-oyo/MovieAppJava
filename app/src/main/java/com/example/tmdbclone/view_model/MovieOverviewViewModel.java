package com.example.tmdbclone.view_model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.example.tmdbclone.constants.ArticleMovieConstants;
import com.example.tmdbclone.model.Movie;
import com.example.tmdbclone.network.VolleySingleton;
import com.example.tmdbclone.response.MovieResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MovieOverviewViewModel extends AndroidViewModel {


    MutableLiveData<ArrayList<Movie>> movieLiveData;
    ArrayList<Movie> movieList;

    public MovieOverviewViewModel(@NonNull Application application)  {
        super(application);
        movieLiveData = new MutableLiveData<>();
        init(application);
    }

    public MutableLiveData<ArrayList<Movie>> getMovieMutableLiveData() {
        return movieLiveData;
    }

    public void init(Application application) {
        populateList(application);
        movieLiveData.setValue(movieList);
    }

    public void populateList(Application application)  {
        movieList = new ArrayList();
        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.GET,
                ArticleMovieConstants.POPULAR_URL_MOVIE + "1",null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if(response != null) {
                                Gson gson = new GsonBuilder().create();
                                MovieResponse movieResponse = gson.fromJson(response.toString(), MovieResponse.class);
                                movieLiveData.setValue((ArrayList<Movie>)movieResponse.getMovies());
                                Log.e("INRESPONSE","sidnfsd");
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

                    }
                });

        VolleySingleton.getInstance(application).getRequestQueue().add(jsonObjectRequest);


    }
}
