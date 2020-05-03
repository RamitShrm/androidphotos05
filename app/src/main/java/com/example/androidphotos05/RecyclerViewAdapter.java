package com.example.androidphotos05;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<String> albumNames;
    private LayoutInflater Inflater;

    // data is passed into the constructor
    public RecyclerViewAdapter(Context context, List<String> albumNames) {
        this.Inflater = LayoutInflater.from(context);
        this.albumNames = albumNames;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = Inflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String album = albumNames.get(position);
        holder.myTextView.setText(album);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return albumNames.size();
    }


    // stores and recycles views as they are scrolled off screen
    static class ViewHolder extends RecyclerView.ViewHolder  {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.albumName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, AlbumActivity.class);
                    intent.putExtra("Album", myTextView.getText().toString());
                    context.startActivity(intent);
                }
            });
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return albumNames.get(id);
    }

}