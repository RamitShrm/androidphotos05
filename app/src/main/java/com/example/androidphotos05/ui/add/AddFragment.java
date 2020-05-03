package com.example.androidphotos05.ui.add;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidphotos05.Album;
import com.example.androidphotos05.Photo;
import com.example.androidphotos05.PhotoActivity;
import com.example.androidphotos05.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class AddFragment extends Fragment implements View.OnClickListener {
    private static final int SELECT_PICTURE = 1;
    private ImageView img;
    private String picturePath;
    private List<Album> albumList;
    private List<String> stringAlbums = new ArrayList<>();
    private Album selAlbum;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_add, container, false);

        albumList = readAlbums();
        for (Album album : albumList){
            stringAlbums.add(album.getAlbumName());
        }

        final Spinner albumSpinner = root.findViewById(R.id.album_spinner);

        ArrayAdapter<String> spinnerArrayAdapter =
                new ArrayAdapter<>(root.getContext(), R.layout.spinner_item, stringAlbums);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        albumSpinner.setAdapter(spinnerArrayAdapter);

        img = root.findViewById(R.id.selected_photo);
        Button addButton = root.findViewById(R.id.select_button);
        addButton.setOnClickListener(this);
        Button button = root.findViewById(R.id.add_photo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(picturePath != null && albumSpinner.getSelectedItem() != null) {
                    selAlbum = getAlbumWithName(albumSpinner.getSelectedItem().toString());
                    //selAlbum.addPhoto(picturePath);
                    Intent intent = new Intent(getActivity(), PhotoActivity.class);
                    intent.putExtra("Album", selAlbum);
                    intent.putExtra("Photo", selAlbum.getPhoto(picturePath));
                    saveAlbums();
                    startActivity(intent);
                }
            }
        });
        return root;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.setFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                assert selectedImageUri != null;
                picturePath = selectedImageUri.toString();
                img.setImageURI(selectedImageUri);
            }
        }
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

    private void saveAlbums(){
        SharedPreferences sharedPreferences = Objects.requireNonNull(
                this.getActivity()).getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor Editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(albumList);
        Editor.putString("AlbumsList", json);
        Editor.apply();
    }

    private Album getAlbumWithName(String name){
        for (Album album: albumList) {
            if (album.getAlbumName().equals(name)){
                album.addPhoto(picturePath);
                return album;
            }
        }
        return null;
    }

}
