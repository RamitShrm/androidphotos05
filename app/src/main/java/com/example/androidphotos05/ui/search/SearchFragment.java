package com.example.androidphotos05.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.androidphotos05.Photo;
import com.example.androidphotos05.R;
import com.example.androidphotos05.SearchResultsActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment  {

    //private SearchViewModel searchViewModel;
    private EditText personText;
    private EditText locationText;
    private List<Photo> resultsList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        //final TextView textView = root.findViewById(R.id.text_search);
        /*searchViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });*/

        personText = root.findViewById(R.id.personText);
        locationText = root.findViewById(R.id.locText);
        Button search = root.findViewById(R.id.searchBtn);
        Button clear = root.findViewById(R.id.clearBtn);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( personText.getText().toString().isEmpty() && locationText.getText().toString().isEmpty()){
                    return;
                }
                // Search each album, get each photo with the criteria, add them to resultsList
                Intent intent = new Intent(getActivity(), SearchResultsActivity.class);
                //intent.putExtra("Search Results", resultsList);
                startActivity(intent);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personText.getText().clear();
                locationText.getText().clear();
            }
        });

        return root;
    }

}
