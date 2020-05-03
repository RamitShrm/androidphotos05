package com.example.androidphotos05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhotoActivity extends AppCompatActivity {

    /*TODO
        Create onBack action that returns to the album of the specific photo.
     */

    Photo photo;
    EditText tagText;
    List<String> peopleList = new ArrayList<>();
    Spinner peopleSpinner;
    ArrayAdapter<String> spinnerArrayAdapter;

    private List<String> stringAlbums = new ArrayList<>();
    private Album selAlbum;
    private List<Album> albumList;
    private int albumIndex;

    TextView locText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        selAlbum = (Album) getIntent().getSerializableExtra("Album");
        photo = (Photo) getIntent().getSerializableExtra("Photo");
        albumList = readAlbums();
        for (Album album : albumList){
            stringAlbums.add(album.getAlbumName());
            if (album.getAlbumName().equals(selAlbum.getAlbumName())){
                albumIndex = albumList.indexOf(album);
            }
        }

        final Spinner albumSpinner = findViewById(R.id.albumSpinner);

        spinnerArrayAdapter =
                new ArrayAdapter<>(this, R.layout.spinner_item, stringAlbums);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        albumSpinner.setAdapter(spinnerArrayAdapter);

        ImageView img = findViewById(R.id.selected_photo);

        img.setImageURI(Uri.parse(albumList.get(albumIndex).getPhoto(photo).getImagePath()));
        locText = findViewById(R.id.locText);
        locText.setText(String.format("%s%s", "Location: ", albumList.get(albumIndex).getPhoto(photo).getLocation()));
        peopleSpinner = findViewById(R.id.peopleSpinner);
        peopleList.addAll(albumList.get(albumIndex).getPhoto(photo).getPeople());
        tagText = findViewById(R.id.tagText);
        spinnerArrayAdapter = new ArrayAdapter<>(
                this, R.layout.spinner_item, peopleList);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        peopleSpinner.setAdapter(spinnerArrayAdapter);
    }

    public void addTag(View v){
        Spinner mySpinner = findViewById(R.id.tagSpinner);
        if (mySpinner.getSelectedItem().toString().equalsIgnoreCase("location")){
            albumList.get(albumIndex).getPhoto(photo).setLocation(tagText.getText().toString());
            locText.setText(String.format("%s%s", "Location: ", albumList.get(albumIndex).getPhoto(photo).getLocation()));
            tagText.getText().clear();
            saveAlbums();
            return;
        }
        if (mySpinner.getSelectedItem().toString().equalsIgnoreCase("person")){
            albumList.get(albumIndex).getPhoto(photo).addPerson(tagText.getText().toString());
            if(!peopleList.isEmpty()) peopleList.clear();
            peopleList.addAll(albumList.get(albumIndex).getPhoto(photo).getPeople());
            spinnerArrayAdapter.notifyDataSetChanged();
            tagText.getText().clear();
            saveAlbums();
        }
    }

    public void remTag(View v){
        if(peopleSpinner.getSelectedItem() != null) {
            albumList.get(albumIndex).getPhoto(photo).delPerson(peopleSpinner.getSelectedItem().toString());
            peopleList.clear();
            peopleList.addAll(albumList.get(albumIndex).getPhoto(photo).getPeople());
            spinnerArrayAdapter.notifyDataSetChanged();
            saveAlbums();
        }
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
        String json = gson.toJson(albumList);
        Editor.putString("AlbumsList", json);
        Editor.apply();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, AlbumActivity.class);
        intent.putExtra("Album",albumList.get(albumIndex).getAlbumName());
        startActivity(intent);
    }

}
