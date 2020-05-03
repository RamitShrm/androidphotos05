package com.example.androidphotos05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AlbumActivity extends AppCompatActivity {

    private List<Album> albumObjects;
    Album curAlbum;
    EditText renameText;
    int indexAlbum;

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
                indexAlbum = albumObjects.indexOf(album);
            }
        }

        ImageAdapter adapter = new ImageAdapter(this, albumObjects.get(indexAlbum).getImageNames(), albumObjects.get(indexAlbum).getPhotoList());
        GridView grid = findViewById(R.id.grid_view);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(AlbumActivity.this, PhotoActivity.class);
                Photo photo = albumObjects.get(indexAlbum).getPhotoList().get(position);
                intent.putExtra("Album", albumObjects.get(indexAlbum));
                intent.putExtra("Photo", photo);
                saveAlbums();
                startActivity(intent);

            }
        });
    }

    public void addPhoto(View v){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("fragment", 1);
        startActivity(i);
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

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("fragment", 2);
        startActivity(i);
    }


    public void renameAlbum(View v)
    {
        renameText = findViewById(R.id.editText2);
        String newName = renameText.getText().toString();
        if (!albumObjects.contains(newName) && (!newName.isEmpty()))
        {
            curAlbum.setAlbumName(newName);
            saveAlbums();
            renameText.getText().clear();
            return;
        }
    }

    public void deleteAlbum(View v)
    {
        albumObjects.remove(curAlbum);
        Intent intent = new Intent(AlbumActivity.this, MainActivity.class);
        saveAlbums();
        startActivity(intent);
    }


}
