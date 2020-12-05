package com.example.a207_demo.roomSystem;

import java.io.Serializable;
import java.util.UUID;

/**
 * The entities.Room class, this is used to create room objects, it holds conference meeting objects held in the room.
 */
public class Room implements Serializable{

    private final String roomID;
    private int capacity;
    private int currentNum = 0;
    private final String roomNum;

    /**
     * Constructor No.1 for Room
     *
     * @param roomNum unique identification roomNum of the room
     */
    public Room(String roomNum, int capacity) {
        this.roomNum = roomNum;
        this.roomID = UUID.randomUUID().toString().split("-")[0];
        this.capacity = capacity;
    }

    /**
     * Constructor No.2 for Room
     *
     * @param roomNum unique identification roomNum of the room
     * @param roomID unique identification roomID of the room
     */
    public Room(String roomNum, String roomID, int capacity) {
        this.roomNum = roomNum;
        this.roomID = roomID;
        this.capacity = capacity;
    }

    /**
     * Get the room number for this room
     *
     * @return the room Number in String format
     */
    public String getRoomNum(){
        return roomNum;
    }

    /**
     * Get the value of roomID
     *
     * @return the value of ID
     */
    public String getRoomID() {
        return roomID;
    }

    /**
     * Get current number of people in this room
     *
     * @return current number
     */
    public int getCurrentNum(){
        return this.currentNum;
    }

    /**
     * Get the capacity for this room
     *
     * @return capacity
     */
    public int getCapacity(){
        return this.capacity;
    }



    public boolean setCapacity(int capacity){
        if (capacity >= this.currentNum) {
            this.capacity = capacity;
            return true;
        }
        return false;
    }
    /**
     * Increase the number of people by 1 in this room
     */
    public void increaseCurrentNum(){
        if (this.currentNum < this.capacity){
            this.currentNum +=1;}
    }

    /**
     * Decrease the number of people by 1 in this room
     */
    public void decreaseCurrentNum(){
        if (this.currentNum > 0){
            this.currentNum -=1;}
    }

//    public void resetTheCurrentNum(){
//        this.currentNum = 0;
//    }

    /**
     * Returns a formatted string of this room with roomNum (and roomID)
     *
     * @return a formatted string
     */
    @Override
    public String toString() {
        return "entities.Room Number " + roomNum + " ( room ID: " + roomID + ")";
    }
}