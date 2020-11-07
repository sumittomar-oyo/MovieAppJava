package com.example.tmdbclone.view_model;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.tmdbclone.constants.ArticleMovieConstants;
import com.example.tmdbclone.data_source.MovieDataSourceFactory;
import com.example.tmdbclone.model.Movie;


public class MovieViewModel extends ViewModel {

    private static final String TAG = MovieViewModel.class.getSimpleName();

    public LiveData<PagedList<Movie>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, Movie>> liveDataSource;

    public MovieViewModel(@NonNull Application application, String CLICKED_BUTTON){
        Log.d(TAG, "ArticleViewModel called");

        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory(application,CLICKED_BUTTON);

        //getting the live data source from data source factory
        liveDataSource = movieDataSourceFactory.getItemLiveDataSource();

        // Getting PagedList configuration
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(true)
                        .setPageSize(ArticleMovieConstants.PAGE_SIZE).build();

        // Building the paged list
        itemPagedList = (new LivePagedListBuilder(movieDataSourceFactory, pagedListConfig)).build();
    }
}
