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

import static android.content.Context.MODE_PRIVATE;

public class AddFragment extends Fragment implements View.OnClickListener {
    private static final int SELECT_PICTURE = 1;
    private ImageView img;
    private String picturePath;

    private List<String> stringAlbums = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_add, container, false);

        List<Album> albumList = readAlbums();
        for (Album album : albumList){
            stringAlbums.add(album.getAlbumName());
        }

        Spinner albumSpinner = root.findViewById(R.id.album_spinner);

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
                if(picturePath != null) {
                    Intent intent = new Intent(getActivity(), PhotoActivity.class);
                    //intent.putExtra("Album",albumSpinner.getSelectedItem().toString());
                    intent.putExtra("Photo",new Photo(picturePath));
                    startActivity(intent);
                }
            }
        });
        return root;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
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

    public List<Album> readAlbums(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences",MODE_PRIVATE);
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
