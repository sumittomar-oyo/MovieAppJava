package com.example.tmdbclone.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmdbclone.R;
import com.example.tmdbclone.constants.ArticleMovieConstants;
import com.example.tmdbclone.model.Movie;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.ViewHolder> {

    private Context mCtx;

    public MovieAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.list_each_row_movies, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder viewHolder, int i) {
        Movie movie = getItem(i);
        if (movie != null) {
            viewHolder.movieTitle.setText(movie.getTitle());
            viewHolder.movieId.setText(movie.getId()+"");
            viewHolder.movieOverView.setText(movie.getOverview());
            Log.e("rrr", ArticleMovieConstants.BASE_IMAGE+movie.getPosterPath());

            Glide.with(mCtx)
                    .load(ArticleMovieConstants.BASE_IMAGE+movie.getPosterPath())
                    .into(viewHolder.imgViewMoviesCover);

        } else {
            Toast.makeText(mCtx, "article is null", Toast.LENGTH_LONG).show();
        }
    }

    private static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Movie>() {
                @Override
                public boolean areItemsTheSame(Movie oldMovie, Movie newMovie) {
                    return newMovie.getTitle() == newMovie.getTitle();
                }

                @Override
                public boolean areContentsTheSame(Movie oldMovie, Movie newMovie) {
                    return oldMovie.equals(newMovie);
                }
            };


    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView movieTitle;
        private final TextView movieOverView;
        private final TextView movieId;
        private final ImageView imgViewMoviesCover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            movieOverView = (TextView) itemView.findViewById(R.id.movieOverview);
            movieId = (TextView) itemView.findViewById(R.id.movieId);
            imgViewMoviesCover = (ImageView) itemView.findViewById(R.id.imgViewMoviesCover);
        }
    }


}
