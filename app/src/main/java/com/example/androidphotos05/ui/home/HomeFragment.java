package com.example.androidphotos05.ui.home;
//Ramit Sharma rks142
//Thomas Hanna trh80

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidphotos05.Album;
import com.example.androidphotos05.R;
import com.example.androidphotos05.RecyclerViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private List<String> albumList = new ArrayList<>();
    private List<Album> albumObjects = new ArrayList<>();
    private RecyclerViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        albumObjects = readAlbums();

        for (Album album : albumObjects){
            albumList.add(album.getAlbumName());
        }

        final Button addButton = root.findViewById(R.id.add_button);
        final EditText albumText = root.findViewById(R.id.album_text);
        RecyclerView recyclerView = root.findViewById(R.id.album_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        adapter = new RecyclerViewAdapter(root.getContext(), albumList);
        recyclerView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!albumText.getText().toString().isEmpty()
                        && !albumList.contains(albumText.getText().toString())) {
                    Album newAlbum = new Album(albumText.getText().toString());
                    albumObjects.add(newAlbum);
                    albumList.add(newAlbum.getAlbumName());
                    adapter.notifyDataSetChanged();
                    albumText.getText().clear();
                    saveAlbums();
                }
            }
        });

        return root;
    }

    private void saveAlbums(){
        SharedPreferences sharedPreferences = Objects.requireNonNull(
                this.getActivity()).getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor Editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(albumObjects);
        Editor.putString("AlbumsList", json);
        Editor.apply();
    }

    private List<Album> readAlbums(){
        SharedPreferences sharedPreferences = Objects.requireNonNull(
                this.getActivity()).getSharedPreferences("shared preferences",MODE_PRIVATE);
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
