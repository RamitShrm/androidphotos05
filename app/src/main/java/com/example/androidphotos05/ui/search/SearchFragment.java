package com.example.androidphotos05.ui.search;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.androidphotos05.Album;
import com.example.androidphotos05.Photo;
import com.example.androidphotos05.R;
import com.example.androidphotos05.SearchResultsActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static android.content.Context.MODE_PRIVATE;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchFragment extends Fragment  {

    private EditText personText;
    private EditText locationText;
    private List<Photo> resultsList = new ArrayList<>();
    private List<Album> albumList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        albumList = readAlbums();

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

                if(albumList.isEmpty())
                {
                    return;
                }

                for(int x = 0; x < albumList.size(); x++)
                {
                    Album album = albumList.get(x);
                    for(int y = 0; y < album.getPhotoList().size(); x++)
                    {
                        Photo photo = album.getPhotoList().get(y);
                        String location = photo.getLocation();
                        if(location.indexOf(personText.toString()) != -1)
                        {
                            resultsList.add(photo);
                        }
                        for(int z = 0; z < photo.getPeople().size(); z++)
                        {
                            if(photo.getPeople().get(z).equals(personText.toString()))
                            {
                                resultsList.add(photo);
                            }
                        }
                    }
                }
                Intent intent = new Intent(getActivity(), SearchResultsActivity.class);
                intent.putExtra("Search Results", (Serializable) resultsList);
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

    private List<Album> readAlbums(){
        SharedPreferences sharedPreferences = Objects.requireNonNull(
                getActivity()).getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("AlbumsList", null);
        Type type = new TypeToken<List<Album>>() {}.getType();
        List<Album> albums = gson.fromJson(json, type);

        if (albums == null){
            albums = new ArrayList<>();
        }

        return albums;
    }

}
