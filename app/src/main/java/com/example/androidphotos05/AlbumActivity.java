package com.example.androidphotos05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends AppCompatActivity {

    public PhotoAdapter p;
    Album album;
    List<Photo> photos = new ArrayList<>();
    ListView photoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        photoList = (ListView) findViewById(R.id.photoList);
        photos = album.getPhotoList();
        //photoList.setAdapter(p);



    }

    public void renameAlbum(View v)
    {
        album.setAlbumName(photoList.toString());
        photos.clear();
        photos.addAll(album.getPhotoList());

    }

    public void deletePhoto(View v)
    {
        Photo delete = (Photo) photoList.getSelectedItem();
        album.delPhoto(delete);
        photos.clear();
        photos.addAll(album.getPhotoList());
    }

    public void addPhoto(View v)
    {



    }
}
