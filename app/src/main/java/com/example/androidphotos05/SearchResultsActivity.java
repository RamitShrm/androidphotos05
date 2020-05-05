//Ramit Sharma rks142
//Thomas Hanna trh80
package com.example.androidphotos05;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        List<Photo> photoList = (List<Photo>) getIntent().getSerializableExtra("Search Results");
        ImageAdapter adapter = new ImageAdapter(this, null, photoList);
        GridView grid = findViewById(R.id.grid_view);
        grid.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("fragment", 2);
        startActivity(i);
    }
}
