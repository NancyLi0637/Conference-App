package com.example.a207_demo.contactSystem;

//Todo: can be replaced by User
public class Contact {

    private String name;
    private int imageId;

    public Contact(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName(){
        return this.name;
    }

    public int getImageId(){
        return this.imageId;
    }
}
