package com.example.tmdbclone.view_model;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.example.tmdbclone.constants.ArticleMovieConstants;
import com.example.tmdbclone.data_source.db.DatabaseClient;
import com.example.tmdbclone.data_source.db.MovieEntity;
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

public class MovieOverviewViewModel2 extends AndroidViewModel {


    MutableLiveData<ArrayList<Movie>> movieLiveData;
    ArrayList<Movie> movieList;


    public MovieOverviewViewModel2(@NonNull Application application)  {
        super(application);
        movieList = new ArrayList();
        movieLiveData = new MutableLiveData<>();
        init(application);
    }

    public MutableLiveData<ArrayList<Movie>> getMovieMutableLiveData() {
        return movieLiveData;
    }

    public void init(Application application) {
        movieLiveData.setValue(movieList);

        ConnectivityManager cm = (ConnectivityManager) application.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            fetchfromServer(application);
        } else {
            fetchfromRoom(application);
        }

    }


    private void fetchfromRoom(Application application) {
        Toast.makeText(application,"In rooom",Toast.LENGTH_LONG).show();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                List<Movie> movies = DatabaseClient.getInstance(application).getAppDatabase().movieDao().getAll();
                Log.e("In rroomm",movies.toString());
                movieList = new ArrayList();
//                for (MovieEntity movieEntity: movies) {
//                    Movie repo = new Movie(
//                            movieEntity.getId(),
//                            movieEntity.getTitle(),
//                            movieEntity.getOverview(),
//                            movieEntity.getBackdrop_path(),
//                            movieEntity.getPoster_path());
//                    Log.e("posterpath",movieEntity.getPoster_path());
//                    movieList.add(repo);
//                }
                movieList.addAll(movies);

                movieLiveData.postValue(movieList);
            }
        });
        thread.start();
    }

    private void fetchfromServer(Application application) {
        Toast.makeText(application,"In server",Toast.LENGTH_LONG).show();
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
                                movieList.addAll(movieResponse.getMovies());
                                movieLiveData.setValue((ArrayList<Movie>)movieResponse.getMovies());
                                saveTask(application);
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
        jsonObjectRequest.setShouldCache(false);
        VolleySingleton.getInstance(application).getRequestQueue().add(jsonObjectRequest);

    }


    private void saveTask(Application application) {
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task

                DatabaseClient.getInstance(application).getAppDatabase().movieDao().delete();
                for (int i = 0; i < movieList.size(); i++) {
                    Movie movieEntity= new Movie();
                    movieEntity.setId(movieList.get(i).getId());
                    movieEntity.setOverview(movieList.get(i).getOverview());
                    movieEntity.setBackdrop_path(movieList.get(i).getBackdrop_path());
                    movieEntity.setPoster_path(movieList.get(i).getPoster_path());
                    movieEntity.setTitle(movieList.get(i).getTitle());
                    DatabaseClient.getInstance(application).getAppDatabase().movieDao().insert(movieEntity);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(application, "Saved", Toast.LENGTH_LONG).show();
            }
        }
        SaveTask st = new SaveTask();
        st.execute();
    }

}


