package com.example.androidphotos05;

/**
 * @author Thomas Hanna
 * @author Ramit Sharma
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Album implements Serializable {
    static final long serialVersionUID = 1L;
    public String albumName;
    public int numPhotos;
    public List<Photo> photoList = new ArrayList<>();
    private String thumbnail = "data/default.png";

    public Album(String albumName) {
        this.albumName = albumName;
    }

    /**
     * Sets album name to parameter
     * @param name album name gets set to this parameter
     */
    public void setAlbumName(String name){
        this.albumName = name;
    }

    /**
     * get the album name
     * @return the album name
     */
    public String getAlbumName() {
        return albumName;
    }

    /**
     * Searches photo that has same path as given
     * @param path the path of specific photo
     * @return the photo
     */
    public Photo getPhoto(String path){
        for (Photo name: photoList) {
            if (name.getImagePath().equals(path)){
                return name;
            }
        }
        return null;
    }

    /**
     * Grabs the list of photos
     * @return the lsit of photos
     */
    public List<Photo> getPhotoList() {
        return photoList;
    }

    /**
     * Grabs thumbnail
     * @return thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * Getns number of photos
     * @return the number
     */
    public int getNumPhotos(){
        return numPhotos;
    }

    /**
     * Adds new photo to the total count, and creates a new photo adds it to the list
     * @param photoPath path of new photo
     */
    public void addPhoto(String photoPath) {
        numPhotos++;
        photoList.add(new Photo(photoPath));
        thumbnail = photoList.get(0).getImagePath();
    }

    /**
     * adds new object photo to list and increases photo count
     * @param photo the photo object being added
     */
    public void addPhoto(Photo photo) {
        numPhotos++;
        photoList.add(photo);
        thumbnail = photoList.get(0).getImagePath();
    }

    /**
     * Deletes photo object, lowers total count and removes from list
     * @param photo the photo being removed
     */
    public void delPhoto(Photo photo){
        numPhotos--;
        photoList.remove(photo);
        if(photoList.isEmpty()) thumbnail = "data/default.png";
        if(!photoList.isEmpty()) thumbnail = photoList.get(0).getImagePath();
    }

}
