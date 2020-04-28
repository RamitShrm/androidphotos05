package com.example.androidphotos05.ui.add;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.androidphotos05.Photo;
import com.example.androidphotos05.PhotoActivity;
import com.example.androidphotos05.R;

import java.util.Objects;

public class AddFragment extends Fragment implements View.OnClickListener {
    private static final int SELECT_PICTURE = 1;
    private ImageView img;
    String picturePath;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddViewModel addViewModel = ViewModelProviders.of(this).get(AddViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add, container, false);
        addViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        img = root.findViewById(R.id.selected_photo);
        Button addButton = root.findViewById(R.id.select_button);
        addButton.setOnClickListener(this);

        Button button = (Button) root.findViewById(R.id.add_photo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(picturePath != null) {
                    Intent intent = new Intent(getActivity(), PhotoActivity.class);
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
                picturePath = selectedImageUri.toString();
                img.setImageURI(selectedImageUri);
            }
        }
    }

}
