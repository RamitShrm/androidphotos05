package com.example.androidphotos05.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidphotos05.Album;
import com.example.androidphotos05.AlbumActivity;
import com.example.androidphotos05.R;
import com.example.androidphotos05.RecylerViewAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<String> albumList = new ArrayList<>();
    private RecyclerView recyclerView;
    RecylerViewAdapter adapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Button button = root.findViewById(R.id.open_album);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(getActivity(), AlbumActivity.class);
                    //intent.putExtra("Album",Album Clicked);
                    startActivity(intent);

            }
        });
        final Button addButton = root.findViewById(R.id.add_button);
        final EditText albumText = root.findViewById(R.id.album_text);
        recyclerView = root.findViewById(R.id.album_list);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        adapter = new RecylerViewAdapter(root.getContext(), albumList);
        recyclerView.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!albumText.getText().toString().isEmpty()) {
                    Album newAlbum = new Album(albumText.getText().toString());
                    albumList.add(newAlbum.getAlbumName());
                    adapter.notifyDataSetChanged();
                    try {
                        newAlbum.writeAlbum(addButton.getContext());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return root;
    }
}
