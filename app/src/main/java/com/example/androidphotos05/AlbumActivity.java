package com.example.androidphotos05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.example.androidphotos05.ui.add.AddFragment;

import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends AppCompatActivity {

    public PhotoAdapter p;
    Album album;
    List<Photo> photos = new ArrayList<>();
    ListView photoList;
    EditText rename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        photoList = (ListView) findViewById(R.id.photoList);
        rename = findViewById(R.id.editText2);
        //if(album.getPhotoList() != null)
        //{
           // photos = album.getPhotoList();
            //photoList.setAdapter(p);
       // }

    }

    public void addPhoto(View v)
    {
        Intent intent = new Intent(this, AddFragment.class);
        intent.putExtra("album", album);
        startActivity(intent);

    }

    public void renameAlbum(View v)
    {
        album.setAlbumName(photoList.toString());
        photos.clear();
        photos.addAll(album.getPhotoList());
        rename.getText().clear();

    }

    public void deletePhoto(View v)
    {
        Photo delete = (Photo) photoList.getSelectedItem();
        album.delPhoto(delete);
        photos.clear();
        photos.addAll(album.getPhotoList());
    }


}
