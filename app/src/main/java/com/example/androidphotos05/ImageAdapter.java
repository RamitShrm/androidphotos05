package com.example.androidphotos05;

import android.Manifest;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends BaseAdapter
{
    private Context mContext;
    private List<Photo> photoList;

    ImageAdapter(Context c, List<String> imageNames, List<Photo> photoList)
    {
        mContext = c;
        this.photoList = photoList;
    }

    @Override
    public int getCount()
    {
        return photoList.size();
    }
    @Override
    public Object getItem(int position)
    {
        return position;
    }
    @Override
    public long getItemId(int position)
    {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup
            parent)
    {
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null) {

            gridView = inflater.inflate(R.layout.grid_item, null);
        }
        else {
            gridView = convertView;
        }

        ImageView imageView = gridView.findViewById(R.id.grid_item_image);
        imageView.setImageURI(null);
        Photo photo = photoList.get(position);
        String imageUri = photo.getImagePath();
        Picasso.get().load(imageUri).into(imageView);

        return gridView;
    }
}