package com.example.androidphotos05;
/*
 * @author Thomas Hanna
 * @author Ramit Sharma
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Photo implements Serializable {
    static final long serialVersionUID = 1L;
    private String imagePath;
    private List<String> people = new ArrayList<>();
    private String location = "No Location";

    public Photo(String photoPath){
        this.imagePath = photoPath;
    }

    /*
     * Set location
     * @return the location
     */
    public void setLocation(String location){
        this.location = location;
    }

    public void addPerson(String name){
        if(people.contains(name)) this.people.add(name);
    }

    /*
     * Gets image path
     * @return image path from photo object
     */
    public String getImagePath() {
        return imagePath;
    }

    /*
     * Deletes a tag
     * @param name tag type
     * @param word the actual tag string
     */
    public void delPerson(String name)
    {
        for(String person: people)
        {
            if(person.equalsIgnoreCase(name))
            {
                people.remove(name);
                return;
            }
        }
    }

    /*
     * Gets tags that are of tag type location
     * @return the location
     */
    public String getLocation(){
        return location;
    }

    public List<String> getPeople(){
        return people;
    }


}
