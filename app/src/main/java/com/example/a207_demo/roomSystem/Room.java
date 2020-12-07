package com.example.a207_demo.roomSystem;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The entities.Room class, this is used to create room objects, it holds conference meeting objects held in the room.
 */
public class Room implements Serializable{

    private final String roomID;
    private final String roomNum;
    private final int capacity;

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
     * Get the capacity for this room
     *
     * @return capacity
     */
    public int getCapacity(){
        return this.capacity;
    }

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