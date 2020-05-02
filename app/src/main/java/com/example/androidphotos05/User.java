package com.example.androidphotos05;
/**
 * @author Thomas Hanna
 * @author Ramit Sharma
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User  implements Serializable {
    static final long serialVersionUID = 1L;
    public String username;
    public List<Album> albumList = new ArrayList<>();
    public List<String> tagTypes = new ArrayList<>();

    public static final String storePath = "data/users";

    /**
     * Writes info to user that belongs to their account
     * @param user user to write info to
     * @throws IOException
     */
    public static void writeUser(User user) throws IOException {
        FileOutputStream fout = new FileOutputStream(storePath + File.separator + user.getUsername() + ".dat");
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(user);
        fout.close();
    }

    /**
     * Brings in data from that user's specific page
     * @param username user tograb info from
     * @return user's info
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static User readUser(String username) throws IOException, ClassNotFoundException{
        File userFile = new File("data/users/" + username + ".dat");
        if(!userFile.exists()) return null;
        FileInputStream fin = new FileInputStream(storePath + File.separator + username + ".dat");
        ObjectInputStream ois = new ObjectInputStream(fin);
        User ret = (User)ois.readObject();
        fin.close();
        return ret;
    }

    public User(String username){
        this.username = username;
        this.tagTypes.add("Location");
        this.tagTypes.add("Person");
    }

    /**
     * Creates new tag type
     * @param type new type to make
     */
    public void addTagType(String type){
        tagTypes.add(type);
    }

    /**
     * Gets List of different tag types
     * @return the list of tag types
     */
    public List<String> getTagTypes(){
        return tagTypes;
    }

    /**
     * Gets username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets list of albums
     * @return album list
     */
    public List<Album> getAlbumList(){
        return albumList;
    }

    /**
     * Gets specific album name
     * @param albumName album name to find
     * @return album
     */
    public Album getAlbum(String albumName){
        for (Album name: albumList) {
            if (name.getAlbumName().equals(albumName)){
                return name;
            }
        }
        return null;
    }

    /**
     * Gets all album names
     * @return all album names
     */
    public List<String> getAlbumNames(){
        List<String> albumNames = new ArrayList<>();
        for (Album name: albumList) {
                albumNames.add(name.getAlbumName());
        }
        return albumNames;
    }

    /**
     * Gets tag
     * @param tagName Tag to find
     * @return the tag
     */
    public String getTag(String tagName){
        for (String tag: tagTypes) {
            if (tag.equals(tagName)){
                return tag;
            }
        }
        return null;
    }

    /**
     * Adds album to list
     * @param albumName album being added
     */
    public void addAlbum(String albumName) {
        albumList.add(new Album(albumName));
    }

    /**
     * Deletes album off list
     * @param albumName album being deleted
     */
    public void delAlbum(String albumName){
        albumList.remove(getAlbum(albumName));
    }
}
