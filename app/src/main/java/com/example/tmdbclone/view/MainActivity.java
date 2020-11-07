package com.example.tmdbclone.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ModuleInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.tmdbclone.R;
import com.example.tmdbclone.adapter.MovieOverviewAdapter;
import com.example.tmdbclone.adapter.MoviePagerAdapter;
import com.example.tmdbclone.model.Movie;
import com.example.tmdbclone.utility.SliderTimer;
import com.example.tmdbclone.view_model.MovieOverviewViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements LifecycleOwner {

    Activity context;
    Button popularButton;
    Button nowPlayingButton;
    Button ratingButton;
    RecyclerView popularOverview;
    MovieOverviewViewModel movieOverviewViewModel;
    MovieOverviewAdapter movieOverviewAdapter;
    MoviePagerAdapter moviePagerAdapter;
    ViewPager viewPager;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }

    public void initialize(){

        context = this;
        popularButton = findViewById(R.id.popularButton);
        nowPlayingButton = findViewById(R.id.nowPlayingButton);
        ratingButton = findViewById(R.id.ratingButton);
        popularOverview = findViewById(R.id.popularOverview);
        popularOverview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        viewPager = findViewById(R.id.viewPager);
        movieOverviewViewModel = ViewModelProviders.of(this).get(MovieOverviewViewModel.class);
        movieOverviewViewModel.getMovieMutableLiveData().observe(this,movieListUpdateObserver);
        timer = new Timer();


        popularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity("popular");
            }
        });

        nowPlayingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity("nowplaying");
            }
        });

        ratingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity("rating");
            }
        });

    }

    public void openNewActivity(String clickedButton){
        Intent intent = new Intent(this, MovieListView.class);
        intent.putExtra("CLICKED_BUTTON", clickedButton );
        startActivity(intent);
    }




    public void openMovieDetailsActivity(View view){
        TextView tt = view.findViewById(R.id.movieId);
        if(tt == null){
            tt = view.findViewById(R.id.movieCoverId);
        }
        Intent intent = new Intent(this,MovieDetailActivity.class);
        intent.putExtra("MOVIE_ID",tt.getText());
        startActivity(intent);

    }

    Observer<ArrayList<Movie>> movieListUpdateObserver = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movieList) {
            movieOverviewAdapter = new MovieOverviewAdapter(context,movieList);
            moviePagerAdapter = new MoviePagerAdapter(context,movieList);
            viewPager.setAdapter(moviePagerAdapter);
            popularOverview.setAdapter(movieOverviewAdapter);
            Log.e("size",""+movieList.size());
            (timer).scheduleAtFixedRate(new SliderTimer(MainActivity.this,viewPager,20),5000,5000);
        }
    };







}


