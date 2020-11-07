package com.example.tmdbclone.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tmdbclone.R;
import com.example.tmdbclone.constants.ArticleMovieConstants;
import com.example.tmdbclone.model.MovieDetailed;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailPageView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailPageView extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailPageView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment detailPageView.
     */
    // TODO: Rename and change types and number of parameters
    private static final String MOVIE_KEY = "movie_key";
    private MovieDetailed movie;
    public static DetailPageView newInstance(MovieDetailed movie) {
        DetailPageView fragment = new DetailPageView();
        Bundle args = new Bundle();
        args.putSerializable(MOVIE_KEY, (Serializable) movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        movie = (MovieDetailed) getArguments().getSerializable(MOVIE_KEY);
        View view =  inflater.inflate(R.layout.fragment_detail_page_view, container, false);
        ImageView imageView = view.findViewById(R.id.detailMovieImg);
        TextView title = view.findViewById(R.id.detailMovieTitle);
        TextView rating = view.findViewById(R.id.detailMovieRating);

        Glide.with(this)
                .load(ArticleMovieConstants.BASE_IMAGE+movie.getPosterPath())
                .into(imageView);

        title.setText(movie.getTitle());
        rating.setText(""+movie.getVote_average());
        return view;
    }
}