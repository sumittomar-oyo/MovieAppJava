package com.example.tmdbclone.constants;

public class ArticleMovieConstants {

    public static final String POPULAR_URL_MOVIE ="https://api.themoviedb.org/3/movie/popular?api_key=6804e7ea097203de38a96d011138b924&page=";
    public static final String NOWPLAYING_URL_MOVIE ="https://api.themoviedb.org/3/movie/now_playing?api_key=6804e7ea097203de38a96d011138b924&page=";

    public static final String RATING_URL_MOVIE ="https://api.themoviedb.org/3/movie/top_rated?api_key=6804e7ea097203de38a96d011138b924&page=";    // the size of a page that we want
    public static final int PAGE_SIZE = 100;
    public static final String BASE_IMAGE ="https://image.tmdb.org/t/p/w185";

    public static final String MOVIE_DETAIL_URL="https://api.themoviedb.org/3/movie/";
    public static final int FIRST_PAGE = 1;

    public static final String QUERY = "movies";

    public static final String API_KEY = "?api_key=6804e7ea097203de38a96d011138b924";
}
