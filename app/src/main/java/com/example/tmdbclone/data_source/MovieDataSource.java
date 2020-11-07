package com.example.tmdbclone.data_source;


import android.content.Context;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.tmdbclone.constants.ArticleMovieConstants;
import com.example.tmdbclone.model.Movie;
import com.example.tmdbclone.network.VolleySingleton;
import com.example.tmdbclone.response.MovieResponse;
import com.example.tmdbclone.utility.ResolveURL;
import com.example.tmdbclone.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class MovieDataSource extends PageKeyedDataSource<Integer, Movie> {

    private static final String TAG = MovieDataSource.class.getSimpleName();
    String URL = "";

    Context context;
    /*
     * This method is responsible to load the data initially
     * when app screen is launched for the first time.
     * We are fetching the first page data from the api
     * and passing it via the callback method to the UI.
     */

    MovieDataSource(Context context,String CLICKED_BUTTON){
        this.context = context;
        URL = ResolveURL.getURL(CLICKED_BUTTON);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
        Log.d(TAG, "loadInitial called");
        JsonObjectRequest stringReq = new JsonObjectRequest(Request.Method.GET, URL+"1",null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if(response != null) {
                                Gson gson = new GsonBuilder().create();
                                MovieResponse movieResponse = gson.fromJson(response.toString(), MovieResponse.class);
                                callback.onResult(movieResponse.getMovies(),null,ArticleMovieConstants.FIRST_PAGE+1);
                            }

                        }
                        catch(Exception e){
                            Log.e("error",e.toString());
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(context,"Hello Javatpoint start",Toast.LENGTH_SHORT).show();
                        Log.e("error first",error.toString());

                    }
                });
//        stringReq.setShouldCache(true);
        VolleySingleton.getInstance(context).getRequestQueue().add(stringReq);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {

    }

    /*
     * This method it is responsible for the subsequent call to load the data page wise when user scroll
     * This method is executed in the background thread
     * We are fetching the next page data from the api
     * and passing it via the callback method to the UI.
     * The "params.key" variable will have the updated value.
     */

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {

        JsonObjectRequest stringReq = new JsonObjectRequest(Request.Method.GET, URL+params.key,null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if(response != null) {
                                Gson gson = new GsonBuilder().create();
                                MovieResponse movieResponse = gson.fromJson(response.toString(), MovieResponse.class);
                                Integer key = (params.key == movieResponse.getTotalResults()) ? null : params.key + 1;
                                callback.onResult(movieResponse.getMovies(),key);
                            }

                        }
                        catch(Exception e){
                            Log.e("error",e.toString());
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,"Hello Javatpoint 2",Toast.LENGTH_SHORT).show();

                    }
                });

        VolleySingleton.getInstance(context).getRequestQueue().add(stringReq);

    }
}
