package com.example.tmdbclone.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tmdbclone.fragments.DetailPageView;
import com.example.tmdbclone.R;
import com.example.tmdbclone.fragments.Tab1Fragment;
import com.example.tmdbclone.fragments.Tab2Fragment;
import com.example.tmdbclone.adapter.DetailsPagePagerAdapter;
import com.example.tmdbclone.model.MovieDetailed;
import com.example.tmdbclone.view_model.MovieDetailViewModal;
import com.example.tmdbclone.view_model.MovieDetailViewModelFactory;
import com.google.android.material.tabs.TabLayout;

public class MovieDetailActivity extends AppCompatActivity {

    String movieId;
    private ViewPager mViewPager;
    MovieDetailViewModal movieDetailViewModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieId = getIntent().getStringExtra("MOVIE_ID");

        mViewPager = (ViewPager) findViewById(R.id.container);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        movieDetailViewModal = ViewModelProviders.of(this, new MovieDetailViewModelFactory(getApplication(), movieId)).get(MovieDetailViewModal.class);
        movieDetailViewModal.getMovieMutableLiveData().observe(this,movieUpdateObserver);
    }


    Observer<MovieDetailed> movieUpdateObserver = new Observer<MovieDetailed>() {
        @Override
        public void onChanged(MovieDetailed movie) {
            if(movie.getId() == 0)
                return;
            DetailsPagePagerAdapter adapter = new DetailsPagePagerAdapter(getSupportFragmentManager());

            adapter.addFragment(Tab1Fragment.newInstance(movie), "INFO");
            adapter.addFragment(Tab2Fragment.newInstance(movie), "GENRES");

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            DetailPageView fragment = DetailPageView.newInstance(movie);
            fragmentTransaction.add(R.id.detailTopView,fragment);
            fragmentTransaction.commit();


            mViewPager.setAdapter(adapter);
        }
    };



}