package com.example.androidphotos05.ui.home;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidphotos05.Album;
import com.example.androidphotos05.AlbumActivity;
import com.example.androidphotos05.R;
import com.example.androidphotos05.User;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment  {

    private HomeViewModel homeViewModel;
    ArrayAdapter<String> albumAdapter;
    ListView albumList;
    EditText albumName;
    List<String> albums = new ArrayList<>();
    User listOfAlbums = new User("Main");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        /*homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });*/
        albums.addAll(listOfAlbums.getAlbumNames());
        albumList = root.findViewById(R.id.listView);
        //albumAdapter = new ArrayAdapter<String>(this, R.layout.fragment_home, albums);
        Button addAlbum = root.findViewById(R.id.button4);
        addAlbum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                createAlbum(view);
            }});


        Button button = root.findViewById(R.id.open_album);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AlbumActivity.class);
                //intent.setType("album/*");
                //intent.putExtra("Album",Album Clicked);
                startActivity(intent);

            }
        });

        return root;
    }

    public void createAlbum(View v)
    {
        String name = albumName.getText().toString();
        for(int x = 0; x < listOfAlbums.getAlbumList().size(); x++)
        {
            if(listOfAlbums.getAlbumList().get(x).getAlbumName().equalsIgnoreCase(name))
            {
                listOfAlbums.addAlbum(name);
                albums.clear();
                albumAdapter.notifyDataSetChanged();
                return;
            }
        }
    }
}
