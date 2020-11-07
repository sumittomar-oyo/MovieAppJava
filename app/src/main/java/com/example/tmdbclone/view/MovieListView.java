package com.example.tmdbclone.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tmdbclone.R;
import com.example.tmdbclone.adapter.MovieAdapter;
import com.example.tmdbclone.view_model.MovieViewModel;
import com.example.tmdbclone.view_model.MovieViewModelFactory;

public class MovieListView extends AppCompatActivity {

    private static final String TAG = MovieListView.class.getSimpleName();
    private RecyclerView my_recycler_view;
    private LinearLayoutManager layoutManager;
    private MovieAdapter adapter;
    MovieViewModel movieViewModel;
    String CLICKED_BUTTON ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list_view);
        CLICKED_BUTTON = getIntent().getStringExtra("CLICKED_BUTTON");
        if(CLICKED_BUTTON.equals("popular"))
            this.setTitle("Popular Movies");
        else if(CLICKED_BUTTON.equals("nowplaying"))
            this.setTitle("Now Playing");
        else
            this.setTitle("Top Rated");
        initialization();
        getMovieArticles();

    }


    /**
     * initialization of views and others
     *
     * @param @null
     */
    private void initialization() {
        my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(MovieListView.this);
        my_recycler_view.setLayoutManager(layoutManager);

        my_recycler_view.setHasFixedSize(true);

        adapter = new MovieAdapter(MovieListView.this);
        movieViewModel = ViewModelProviders.of(this, new MovieViewModelFactory(getApplication(), CLICKED_BUTTON)).get(MovieViewModel.class);
    }

    private void getMovieArticles() {

        movieViewModel.itemPagedList.observe(this, ( movies) ->
                { Log.e("errror", movies.toString());
                    adapter.submitList(movies);}
        );

        my_recycler_view.setAdapter(adapter);
    }

    public void openMovieDetailsActivity(View view){
        TextView tt = view.findViewById(R.id.movieId);
        Intent intent = new Intent(this,MovieDetailActivity.class);
        intent.putExtra("MOVIE_ID",tt.getText());
        startActivity(intent);
    }
}

