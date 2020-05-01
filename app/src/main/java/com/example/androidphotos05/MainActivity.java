package com.example.androidphotos05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Album> albums = new ArrayList<Album>();
    Album album;
    private TextView selAlbum = findViewById(R.id.selAlbum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_add, R.id.navigation_search)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    /**
     * removes the album
     *
     * @param view current view
     */
    public void removeAlbum(View view) {

    }

    /**
     * Adds the new album to the list view
     *
     * @param view current view
     */
    public void addAlbum(View view) {

    }

    /**
     * renames the album
     *
     * @param view current view
     */
    public void renameAlbum(View view) {

    }

    /**
     * Opens the selected album by creating a new Intent
     *
     * @param view current view
     */
    public void openAlbum(View view) {
      
    }

}
