package com.example.tmdbclone.data_source;


import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.tmdbclone.model.Movie;

public class MovieDataSourceFactory extends DataSource.Factory {

    /**
     * ArticleDataSourceFactory is responsible for retrieving the data using the
     * ArticleDataSource
     * and
     * PagedList configuration which is inside our ArticleViewModel class
     */


    // creating the mutable live data
    private MutableLiveData<PageKeyedDataSource<Integer, Movie>> itemLiveDataSource = new MutableLiveData<>();
    Context context;
    String CLICKED_BUTTON;
    public MovieDataSourceFactory(Context context,String CLICKED_BUTTON){

        this.context = context;
        this.CLICKED_BUTTON = CLICKED_BUTTON;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        //getting our data source object
        MovieDataSource movieDataSource = new MovieDataSource(context,CLICKED_BUTTON);

        //posting the data source to get the values
        itemLiveDataSource.postValue(movieDataSource);

        //returning the data source
        return movieDataSource;
    }

    //getter for itemlivedatasource
    public MutableLiveData<PageKeyedDataSource<Integer, Movie>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
