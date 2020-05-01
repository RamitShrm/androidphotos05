package com.example.androidphotos05;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;


/**
 * Album activity to handle functionality in an album.
 */
public class AlbumActivity extends AppCompatActivity {
    private List<Photo> photos;
    private Album checkedAlbum;
    private ArrayList<Album> album;
    private String path;
    private int albumPosition = 0;


    /**
     * On create read the photos photos from the file and initialize the photo view.
     *
     * @param savedInstanceState as the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        path = this.getApplicationInfo().dataDir + "/data.dat";
        Intent intent = getIntent();
        album = (ArrayList<Album>) intent.getSerializableExtra("albums");
        albumPosition = intent.getIntExtra("albumPosition", 0);
        checkedAlbum = album.get(albumPosition);


    }


    /**
     * Open the selected photo
     *
     * @param view as view
     */
    public void openPhoto(View view) {


    }

    /**
     * Add the new photo by calling the Open document intent.
     *
     * @param view as view
     */
    public void addPhoto(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");

        startActivityForResult(intent, 1);

    }


    /**
     * Remove the selected photo after confirming from user.
     *
     * @param view as view
     */
    public void removePhoto(View view) {


    }

}

