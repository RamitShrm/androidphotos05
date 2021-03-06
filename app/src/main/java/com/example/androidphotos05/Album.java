package com.example.androidphotos05;
//Ramit Sharma rks142
//Thomas Hanna trh80
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thomas Hanna
 * @author Ramit Sharma
 */

public class Album implements Serializable {
    static final long serialVersionUID = 1L;
    private String albumName;
    private List<Photo> photoList = new ArrayList<>();

    public Album(String albumName) {
        this.albumName = albumName;
    }

    /**
     * Sets album name to parameter
     * @param name album name gets set to this parameter
     */
    void setAlbumName(String name){
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

    Photo getPhoto(Photo photo){
        for (Photo name: photoList) {
            if (name.getImagePath().equals(photo.getImagePath())){
                return name;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object other){
        boolean result;
        if((other == null) || (getClass() != other.getClass())){
            result = false;
        }
        else{
            Album album = (Album)other;
            result = albumName.equals(album.getAlbumName()) &&  photoList == album.getPhotoList();
        }

        return result;
    }

    /**
     * Grabs the list of photos
     * @return the lsit of photos
     */
    public List<Photo> getPhotoList() {
        return photoList;
    }

    /**
     * Adds new photo to the total count, and creates a new photo adds it to the list
     * @param photoPath path of new photo
     */
    public void addPhoto(String photoPath) {
        if (!photoList.contains(getPhoto(photoPath))) photoList.add(new Photo(photoPath));
    }

    /**
     * adds new object photo to list and increaes photo count
     * @param photo the photo object being added
     */
    public void addPhoto(Photo photo) {
        photoList.add(photo);
    }

    /**
     * Deletes photo object, lowers total count and removes from list
     * @param photo the photo being removed
     */
    void delPhoto(Photo photo){
        photoList.remove(photo);
    }

    List<String> getImageNames(){
        List<String> imageNames = new ArrayList<>();
        for (Photo name: photoList) {
            imageNames.add(name.getImagePath());
        }
        return imageNames;
    }
}
