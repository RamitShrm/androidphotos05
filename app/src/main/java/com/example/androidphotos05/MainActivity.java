package com.example.androidphotos05;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    PhotoAdapter a;
    ListView albumList;
    EditText albumName;
    ArrayList<Album> albums = new ArrayList<Album>();
    Album album;

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

        albumName = findViewById(R.id.editText);
        albumList = (ListView) findViewById(R.id.listView);
        //  albumList.setAdapter(a);
    }

    public void createAlbum(View v)
    {
        if(!album.toString().equalsIgnoreCase(albumName.toString()))
        {
            Album newAlbum = new Album(albumName.toString());
            albums.add(newAlbum);


        }
    }

}
