package com.example.androidphotos05;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {

    /*TODO
        Create onBack action that returns to the album of the specific photo.
     */
    Photo photo;
    EditText tagText;
    List<String> peopleList = new ArrayList<>();
    Spinner peopleSpinner;
    ArrayAdapter<String> spinnerArrayAdapter;
    TextView locText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ImageView img = findViewById(R.id.selected_photo);
        photo = (Photo) getIntent().getSerializableExtra("Photo");
        img.setImageURI(Uri.parse(photo.getImagePath()));
        locText = findViewById(R.id.locText);
        peopleSpinner = findViewById(R.id.peopleSpinner);
        peopleList.addAll(photo.getPeople());
        tagText = findViewById(R.id.tagText);
        spinnerArrayAdapter = new ArrayAdapter<String>(
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
            return;
        }
        if (mySpinner.getSelectedItem().toString().equalsIgnoreCase("person")){
            photo.addPerson(tagText.getText().toString());
            peopleList.clear();
            peopleList.addAll(photo.getPeople());
            spinnerArrayAdapter.notifyDataSetChanged();
            tagText.getText().clear();
        }
    }

    public void remTag(View v){
        if(peopleSpinner.getSelectedItem() != null) {
            photo.delPerson(peopleSpinner.getSelectedItem().toString());
            peopleList.clear();
            peopleList.addAll(photo.getPeople());
            spinnerArrayAdapter.notifyDataSetChanged();
        }
    }
}
