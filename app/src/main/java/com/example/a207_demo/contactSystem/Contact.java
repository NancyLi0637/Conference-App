package com.example.a207_demo.contactSystem;

/**
 * a Contact with name and imageId
 */
//Todo: can be replaced by User
public class Contact {

    private String name;
    private int imageId;

    /**
     * a Contact with name and imageId
     *
     * @param name    String
     * @param imageId int
     */
    public Contact(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    /**
     * getName
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * getImageId
     *
     * @return int
     */
    public int getImageId() {
        return this.imageId;
    }
}
