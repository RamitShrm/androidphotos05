package com.example.androidphotos05;
//Ramit Sharma rks142
//Thomas Hanna trh80

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



public class MainActivity extends AppCompatActivity {
    BottomNavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_add, R.id.navigation_search)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        if (getIntent().getExtras() != null){
            if (getIntent().getExtras().getInt("fragment") == 1) {
                navView.setSelectedItemId(R.id.navigation_add);
            }
            if (getIntent().getExtras().getInt("fragment") == 2) {
                navView.setSelectedItemId(R.id.navigation_home);
            }
        }

    }

    @Override
    public void onBackPressed() {
        navView.setSelectedItemId(R.id.navigation_home);
    }
}
