package com.example.tmdbclone.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.tmdbclone.R;
import com.example.tmdbclone.constants.ArticleMovieConstants;
import com.example.tmdbclone.model.Movie;
import com.example.tmdbclone.view.MainActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MoviePagerAdapter extends PagerAdapter {

    private Activity activity;
    private ArrayList<Movie> movies;

    public MoviePagerAdapter(Activity activity,ArrayList<Movie> movies){

        this.activity = activity;
        this.movies = movies;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_each_row_movies, null);
        ImageView imageView =  view.findViewById(R.id.imgViewMoviesCover);
        TextView textView1 = view.findViewById(R.id.movieTitle);
        TextView textView2 = view.findViewById(R.id.movieOverview);
        TextView textView3 = view.findViewById(R.id.movieId);
        textView1.setText(movies.get(position).getTitle());
        textView2.setText(movies.get(position).getOverview());
        textView3.setText(movies.get(position).getId()+"");
        Glide.with(activity)
                .load(ArticleMovieConstants.BASE_IMAGE+movies.get(position).getPoster_path())
                .into(imageView);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View)object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

}
