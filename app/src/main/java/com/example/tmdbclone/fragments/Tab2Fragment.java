package com.example.tmdbclone.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tmdbclone.R;
import com.example.tmdbclone.model.Genres;
import com.example.tmdbclone.model.MovieDetailed;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    private static final String MOVIE_KEY = "movie_key";
    MovieDetailed movie;
    public static Tab2Fragment newInstance(MovieDetailed movie) {
        Tab2Fragment fragment = new Tab2Fragment();
        Bundle args = new Bundle();
        args.putSerializable(MOVIE_KEY,(Serializable)movie);
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
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        ArrayList<Genres> list = (ArrayList<Genres>)movie.getGenres();
        String str ="";
        if(list != null)
            for(Genres genre:list){
                str = str+genre.getName();
                str = str + "\n";
            }
        TextView textView = view.findViewById(R.id.genreTextView);
        textView.setText(str);
        return view;
    }
}