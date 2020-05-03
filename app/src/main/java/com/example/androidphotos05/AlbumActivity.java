package com.example.androidphotos05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AlbumActivity extends AppCompatActivity {

    private List<Album> albumObjects;
    Album curAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        albumObjects = readAlbums();
        String selAlbum = Objects.requireNonNull(
                getIntent().getExtras()).getString("Album");

        for (Album album : albumObjects){
            if (album.getAlbumName().equals(selAlbum)){
                curAlbum = album;
            }
        }

        ImageAdapter adapter = new ImageAdapter(this, curAlbum.getImageNames(), curAlbum.getPhotoList());
        GridView grid = findViewById(R.id.grid_view);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(AlbumActivity.this, PhotoActivity.class);
                Photo photo = curAlbum.getPhotoList().get(position);
                intent.putExtra("Album", curAlbum);
                intent.putExtra("Photo", photo);
                saveAlbums();
                startActivity(intent);

            }
        });
    }

    private List<Album> readAlbums(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("AlbumsList", null);
        Type type = new TypeToken<List<Album>>() {}.getType();
        List<Album> albums = gson.fromJson(json, type);

        if (albums == null){
            albums = new ArrayList<>();
        }

        return albums;
    }

    private void saveAlbums(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor Editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(albumObjects);
        Editor.putString("AlbumsList", json);
        Editor.apply();
    }
}
