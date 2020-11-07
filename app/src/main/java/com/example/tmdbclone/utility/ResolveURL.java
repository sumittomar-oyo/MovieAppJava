package com.example.tmdbclone.utility;

import com.example.tmdbclone.constants.ArticleMovieConstants;

public class ResolveURL {
    public static String getURL(String CLICKED_BUTTON){
        if (CLICKED_BUTTON.equals("popular")) {
            return ArticleMovieConstants.POPULAR_URL_MOVIE;
        }
        else if(CLICKED_BUTTON.equals("nowplaying")) {
            return ArticleMovieConstants.NOWPLAYING_URL_MOVIE;
        }
        else{
            return ArticleMovieConstants.RATING_URL_MOVIE;
        }

    }

}
