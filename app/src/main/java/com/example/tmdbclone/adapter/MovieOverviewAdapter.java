package com.example.tmdbclone.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmdbclone.R;
import com.example.tmdbclone.constants.ArticleMovieConstants;
import com.example.tmdbclone.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieOverviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;
    ArrayList<Movie> movieList;


    public MovieOverviewAdapter(Activity context, ArrayList<Movie> movieList){
        this.context = context;
        this.movieList = movieList;
        Log.e("MovieList2",""+movieList.size());
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.popular_movie_overview,parent,false);
//        View rootView = LayoutInflater.from(context).inflate(R.layout.popular_movie_overview,null);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        RecyclerViewViewHolder recyclerViewViewHolder  = (RecyclerViewViewHolder)holder;
        Glide.with(context)
                .load(ArticleMovieConstants.BASE_IMAGE+movie.getPosterPath())
                .into(recyclerViewViewHolder.imageView);

        recyclerViewViewHolder.textViewId.setText(movie.getId()+"");
        recyclerViewViewHolder.textView.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void submitList(ArrayList<Movie> movies) {
        this.movieList = movies;
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView textViewId;
        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movieCoverView);
            textView = itemView.findViewById(R.id.movieCoverTitle);
            textViewId = itemView.findViewById(R.id.movieCoverId);
        }


    }
}
