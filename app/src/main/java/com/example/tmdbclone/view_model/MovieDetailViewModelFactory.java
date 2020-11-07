package com.example.tmdbclone.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MovieDetailViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mParam;


    public MovieDetailViewModelFactory(Application application, String param) {
        mApplication = application;
        mParam = param;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieDetailViewModal(mApplication,mParam);
    }
}
