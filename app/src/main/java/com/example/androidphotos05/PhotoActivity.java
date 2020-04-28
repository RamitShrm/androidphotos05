package com.example.androidphotos05;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;
import java.util.Objects;

public class PhotoActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        img = findViewById(R.id.selected_photo);
        Photo photo = (Photo) getIntent().getSerializableExtra("Photo");
        //File imgFile = new File(photo.getImagePath());
        //if(imgFile.exists())
        //{
            img.setImageURI(Uri.parse(photo.getImagePath()));//Uri.fromFile(imgFile));
        //}
    }
}
