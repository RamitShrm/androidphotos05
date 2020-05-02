package com.example.androidphotos05;

import androidx.appcompat.app.AppCompatActivity;

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

    TextView locText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        albumList = readAlbums();
        for (Album album : albumList){
            stringAlbums.add(album.getAlbumName());
        }

        final Spinner albumSpinner = findViewById(R.id.albumSpinner);

        ArrayAdapter<String> spinnerArrayAdapter =
                new ArrayAdapter<>(this, R.layout.spinner_item, stringAlbums);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        albumSpinner.setAdapter(spinnerArrayAdapter);

        ImageView img = findViewById(R.id.selected_photo);
        selAlbum = (Album) getIntent().getSerializableExtra("Album");
        photo = (Photo) getIntent().getSerializableExtra("Photo");
        img.setImageURI(Uri.parse(photo.getImagePath()));
        locText = findViewById(R.id.locText);
        peopleSpinner = findViewById(R.id.peopleSpinner);
        peopleList.addAll(photo.getPeople());
        tagText = findViewById(R.id.tagText);
        spinnerArrayAdapter = new ArrayAdapter<>(
                this, R.layout.spinner_item, peopleList);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        peopleSpinner.setAdapter(spinnerArrayAdapter);
    }

    public void addTag(View v){
        Spinner mySpinner = findViewById(R.id.tagSpinner);
        if (mySpinner.getSelectedItem().toString().equalsIgnoreCase("location")){
            photo.setLocation(tagText.getText().toString());
            locText.setText(String.format("%s%s", "Location: ", photo.getLocation()));
            tagText.getText().clear();
            saveAlbums();
            return;
        }
        if (mySpinner.getSelectedItem().toString().equalsIgnoreCase("person")){
            photo.addPerson(tagText.getText().toString());
            peopleList.clear();
            peopleList.addAll(photo.getPeople());
            spinnerArrayAdapter.notifyDataSetChanged();
            tagText.getText().clear();
            saveAlbums();
        }
    }

    public void remTag(View v){
        if(peopleSpinner.getSelectedItem() != null) {
            photo.delPerson(peopleSpinner.getSelectedItem().toString());
            peopleList.clear();
            peopleList.addAll(photo.getPeople());
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
}
